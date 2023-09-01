package com.bingo.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.feign.UserFeign;
import com.bingo.kafka.KafkaProducer;
import com.bingo.mapper.BingoPostMapper;
import com.bingo.pojo.common.PageParam;
import com.bingo.constant.ESConstant;
import com.bingo.constant.MQConstant;
import com.bingo.pojo.dto.SearchDTO;
import com.bingo.pojo.dto.community.LikeDTO;
import com.bingo.pojo.dto.community.PostDTO;
import com.bingo.pojo.po.community.BingoPost;
import com.bingo.pojo.common.response.FeignResponse;
import com.bingo.pojo.vo.community.PostPageVO;
import com.bingo.pojo.vo.community.PostVO;
import com.bingo.pojo.vo.user.UserVO;
import com.bingo.service.BingoPostService;
import com.bingo.store.BingoPostStore;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-05-13
 */
@Service
public class BingoPostServiceImpl extends ServiceImpl<BingoPostMapper, BingoPost> implements BingoPostService {
    @Autowired
    private BingoPostStore postStore;
    @Autowired
    private KafkaProducer kafkaProducer;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    private UserFeign userFeign;


    /**
     * 发布帖子
     */
    @Override
    public Boolean savePost(PostDTO postDTO) {
        // 保存 DB
        BingoPost bingoPost = new BingoPost();
        BeanUtils.copyProperties(postDTO, bingoPost);
        postStore.savePost(bingoPost);

        // 消息发送MQ，同步ES
        kafkaProducer.sendMessage(MQConstant.COMMUNITY_POST_TOPIC, JSON.toJSONString(bingoPost));

        return true;
    }

    /**
     * 点赞帖子
     * 注意：Redis里面没有 Long 类型
     */
    @Override
    public Boolean likePost(LikeDTO likeDTO) {
        Long postId = likeDTO.getPostId();
        String likeUserId = likeDTO.getLikeUid();

        // 生成RedisKey（点赞记录、点赞次数）
        String likeRecordKey = postId + ":" + likeUserId;
        String likeCountKey = postId + ":count";

        // 帖子被用户点赞过，删除点赞记录，点赞总次数 - 1
        if (redisTemplate.hasKey(likeRecordKey)) {
            redisTemplate.delete(likeRecordKey);
            redisTemplate.opsForValue().decrement(likeCountKey, 1);
        }

        // 帖子没被用户点赞，保存点赞记录，点赞次数 + 1（需要判断是否是首赞）
        else {
            // 不是首赞用户，点赞总次数 + 1
            if (redisTemplate.hasKey(likeCountKey)) {
                redisTemplate.opsForValue().increment(likeCountKey, 1);
            } else {
                // 首赞用户，生成点赞总次数（默认为1）
                redisTemplate.opsForValue().set(likeCountKey, 1);
            }
            // 保存点赞记录
            redisTemplate.opsForValue().set(likeRecordKey, "YES");
        }

        // 消息发送给MQ，同步MySQL
//        kafkaProducer.sendMessage(MQConstant.POST_LIKE, JSON.toJSONString());

        return null;
    }

    /**
     * 根据关键字 postFont，全文搜索帖子
     */
    @Override
    public List<PostVO> searchPost(SearchDTO searchDTO) throws IOException {
        List<Long> idList = new ArrayList<>();
        List<PostVO> resultList = new ArrayList<>();
        String content = searchDTO.getContent();
        Integer current = searchDTO.getCurrent();
        Integer limit = searchDTO.getLimit();

        // 构建查询请求，指定查询索引
        SearchRequest request = new SearchRequest(ESConstant.POST_INDEX);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("postFont", content));
        builder.from(current);
        builder.size(limit);
        request.source(builder);

        // 发送请求，分页查询帖子信息
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        SearchHit[] hits = response.getHits().getHits();

        // 处理查询结果
        for (SearchHit hit : hits) {
            String postString = hit.getSourceAsString();
            PostVO postVO = JSON.parseObject(postString, PostVO.class);
            resultList.add(postVO);
            idList.add(postVO.getId());
        }

        // TODO 将帖子点赞、评论相关数量信息封装到帖子中

        return resultList;
    }

    /**
     * 展示用户最新的帖子（分页10条）
     */
    @Override
    public PostPageVO pagePost(PageParam pageParam) {
        PostPageVO postPageVO = new PostPageVO();

        //查询帖子表（分页10条）
        Page<BingoPost> bingoPost = postStore.pagePost(pageParam);
        List<BingoPost> records = bingoPost.getRecords();

        //封装账号ID
        List<Long> ids = new ArrayList<>();
        for (BingoPost post : records) {
            ids.add(post.getId());
        }

        //查询对应的帖子的点赞数，转发数，评论数
        //List<BingoPostStatistics> postStatistics = PostStatisticsStore.findPost(ids);

        //Feign取得对应用户相关信息
        FeignResponse<List<UserVO>> feignResponse = userFeign.getUserByIds(ids);
        List<UserVO> userVOList = feignResponse.getData();
        BeanUtils.copyProperties(bingoPost, postPageVO);
//        BeanUtils.copyProperties(postStatistics, postPageVO);
        BeanUtils.copyProperties(userVOList, postPageVO);
        return postPageVO;
    }
}
