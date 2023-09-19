package com.bingo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bingo.pojo.LoginUser;
import com.bingo.pojo.po.user.BingoUser;
import com.bingo.store.BingoUserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author 徐志斌
 * @Date: 2023/9/18 22:25
 * @Version 1.0
 * @Description: 自定义UserDetailsService实现类
 * -----------------------------------------------------------
 * UserDetailsService需要重新自定义：官方原版本是从缓存中查询，要改造成从DB中查询
 * UserDetails需要自定义实现类进行实现
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private BingoUserStore userStore;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // 查询用户信息
        QueryWrapper<BingoUser> wrapper = new QueryWrapper<>();
        wrapper.eq("account_id", userName);
        BingoUser userInfo = userStore.getOne(wrapper);
        if (Objects.isNull(userInfo)) {
            throw new RuntimeException("用户名、密码错误");
        }

        // TODO 查询用户的权限信息


        // TODO 数据封装成UserDetails返回
        return new LoginUser(userInfo);
    }
}
