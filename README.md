<p align="center">
    <a href="" target="_blank">
      <img src="" width="300" />
    </a>
</p>

<h1 align="center">Bingo 社交</h1>
<p align="center"><strong>好玩的年轻人社交平台！<em>持续更新中 ing～</em></strong></p>

<div align="center">
    <a href="#"><img src="https://img.shields.io/badge/博客-徐志斌-blue.svg?style=plasticr"></a>
    <a href="https://github.com/zongzibinbin/MallChat"><img src="https://img.shields.io/badge/github-个人主页-yellow.svg?style=plasticr"></a>
    <a href="https://gitee.com/zhongzhibinbin/MallChat"><img src="https://img.shields.io/badge/后端-项目地址-orange.svg?style=plasticr"></a>
    <a href="https://github.com/Evansy/MallChatWeb"><img src="https://img.shields.io/badge/前端-项目地址-blueviolet.svg?style=plasticr"></a>
    <a href="https://github.com/zongzibinbin/MallChat/stargazers" target="_blank">
        <img alt="License" src="https://img.shields.io/github/stars/zongzibinbin/MallChat.svg?style=social">
    </a>
</div>

---

### 1、项目介绍

欢迎访问Bingo社交开源项目，这是一款功能丰富、新颖、针对年轻人交友的平台系统。 项目整体采用微服务架构，技术栈全面、主流、前沿。
感谢您的Star，期待您的加入我们，一起Fork！

### 2、模块、功能介绍

#### 公共模块【bingo-common】

用于封装项目通用所需的相关实体类、工具类、配置类、远程调用、注解、枚举、拦截器等.....

#### 用户管理【bingo-user】

该模块用于用户基本信息，例如：用户管理、礼物相关、会员权益、账户充值等.....

#### 社区广场【bingo-community】

关于动态、话题、视频、真人Show、搜索推荐等
用户也可以发布、点赞、评论这些玩法。用户发布的作品有机会得到打赏。

#### 聊天通讯【bingo-im】

用户之间进行实时畅聊、语音、视频、斗图等功能，实时高效的IM通讯功能模块。
支持好友之间申请、建群、打赏、聊天、视频。

#### 狂欢派对【bingo-party】

实现多人房间维度的聊天、交流、抢麦、直播、游戏等功能.....
![img.png](bingo-common/src/main/resources/img/img.png)

### 3、技术选型

#### 后端

- MySQL
- Redis
- MyBatis-Plus
- SpringBoot
- SpringCloud
    - Nacos
    - Ribbon
    - Gateway
    - Feign
    - Sentinel
    - Seata
- Netty(WebSocket + WebRTC)
- ElasticSearch(Kibana + LogStash)
- MinIO
- Kafka
- XXL-JOB
- Redisson
- ShardingSphere
- JWT
- ip2region
- 高德地图SDK

#### 前端

- HTML
- CSS
- JavaScript
- JQuery
- Vue.js
- ElementUI
- Axios
- node.js
- npm
- js-cookie
- js-audio-recorder

#### 运维部署

- Linux
- Shell
- Nginx(LVS + KeepAlive)
- 域名 + SSL证书
- Maven
- Jenkins
- Docker(Dockerfile + DockerCompose)
- SonarQube
- Harbor
- Prometheus
- Grafana

#### 大数据

待定......

### 4、加入我们

1. 本地环境项目启动流程：
2. 待办需求池功能概览：
3. Fork贡献流程：

### 5、TODO

1. 模块进一步拆分
2. 技术栈整体版本升级
3. IM语音功能开发
4. 参数校验抽取DTO
5. Store层查询优雅优化
