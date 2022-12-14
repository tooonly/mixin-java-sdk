# mixin-java-sdk
### 可对接功能列表：
* 群聊
* 支付
* NFT
* 分享应用
* 转账
* 提现
* ......
### 亮点：
* 底层支持Saas，支持开发多群应用
* 实现JWToken支持Ed25519算法
* 屏蔽复杂细节，只需要关心个性化业务逻辑
* 上传Maven仓库，引用方便
* 
#### 之前一直想开发一个Mixin群聊机器人。
#### 所以我就自己动手先开发了一个属于java语言的Mixin SDK。
#### 经过几周的努力，终于开发出了令人比较满意的Mixin-JAVA-SDK。
#### 目前已经实现群聊功能，支持Saas多机器人，可以完美适配多个群用一套代码。
#### 此SDK提供的是接口方法，大群功能我也单独写了个SDK，大群SDK依赖此SDK，充分考虑了解耦性。
#### 如果有需要用户授权、用户管理等功能的应用，也可以引入此SDK，SDK代码清晰明了。
##### 请注意：SDK并非机器人本身，需要开发大群机器人需要引入Maven配置，然后根据自己的大群逻辑进行开发即可。
### 目前已上传Maven仓库，引入以下pom即可
```
<dependency>
  <groupId>com.tooonly</groupId>
  <artifactId>mixin-java-sdk</artifactId>
  <version>1.2.0-SNAPSHOT</version>
</dependency>
```

#### 我相信，无论SDK多简单，依然会有很多小伙伴无法独立完成配置及开发，没关系，
#### MixinBot的Saas核心代码已经开发完毕，正在开发Saas管理系统，到时候直接一键配置大群，
#### 让你不用写一行代码也可以享用一个完整的大群机器人功能，请多多关注。

#### 有问题请提issues，我看到会第一时间解答，对你有帮助请给我Star，感谢！！！
#### 我的MixinId：39488252 ，可以添加我为Mixin好友，在代码使用过程中遇到问题，可以给我留言。
#### 可以添加机器人 7000104276 ，第一时间体验新功能。
