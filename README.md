# mixin-java-sdk
### 之前一直想开发一个Mixin机器人，奈何没有一个真正意义上的mixin-java-sdk。
### 找到一个非官方的sdk却根本无法实现群聊功能，而且加密算法并没有实现Ed25519。
### 所以我就想自己动手开发一个属于java语言的MixinSDK。
### 经过几周的努力，终于开发出了令人比较满意的Mixin-JAVA-SDK。
### 这才算的上是真正的sdk，一个sdk应该包含完整的基础功能，有调用Demo，凡是需要调用方进行引用的，皆有一个接口，并且实现了一个实现类样例。
### 目前已经实现大群功能，并且支持后期Saas扩展，可以完美适配多个群用一套代码。
### 此SDK提供的是接口方法，大群功能我也单独写了个SDK，大群SDK依赖此SDK，充分考虑了解耦性。
### 如果有需要用户授权、用户管理等功能的应用，也可以引入此SDK，SDK代码清晰明了，API文档正在编辑中，请稍等。
#### 请注意：SDK并非机器人本身，需要开发大群机器人需要引入maven，然后根据自己的大群逻辑进行开发即可。
### 目前已上传Maven仓库，引入以下pom即可
```
<dependency>
  <groupId>com.tooonly</groupId>
  <artifactId>mixin-java-sdk</artifactId>
  <version>1.1.1-SNAPSHOT</version>
</dependency>
```

#### 我相信，无论SDK多简单，依然会有很多小伙伴无法独立完成配置及开发，没关系，
#### MixinBot的Saas核心代码已经开发完毕，正在开发Saas管理系统，到时候直接一键配置大群，
#### 让你不用写一行代码也可以享用一个完整的大群机器人功能，请多多关注。

### 请遵照 LICENGSE 协议，本SDK仅用于个人学习，请勿用作商用，如有商用需求，请联系作者，取得作者明确同意后，可进行商业用途。
#### 有问题请提issues，我看到会第一时间解答，对你有帮助请给我Star，感谢！！！
#### 我的MixinId：39488252 ，可以添加我为Mixin好友，在代码使用过程中遇到问题，可以给我留言。
