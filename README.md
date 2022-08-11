# mixin-java-sdk
### 之前一直想开发一个Mixin机器人，奈何没有一个真正意义上的mixin-java-sdk。
### 找到一个非官方的sdk却根本无法实现群聊功能，而且加密算法并没有实现Ed25519。
### 所以我就想自己动手开发一个属于java语言的MixinSDK。
### 经过几周的努力，终于开发出了令人比较满意的Mixin-JAVA-SDK。
### 这才算的上是真正的sdk，一个sdk应该包含完整的基础功能，有调用Demo，凡是需要调用方进行引用的，皆有一个接口，并且实现了一个实现类样例。
### 目前已经实现大群功能，并且支持后期Saas扩展，可以完美适配多个群用一套代码。
### 此SDK提供的是接口方法，大群功能我也单独写了个SDK，大群SDK依赖此SDK，充分考虑了解耦性。
### 如果有需要用户授权、用户管理等功能的应用，也可以引入此SDK，SDK代码清晰明了，API文档正在编辑中，请稍等。
### 目前已上传Maven仓库，pom引入 
```
<dependency>
  <groupId>com.tooonly</groupId>
  <artifactId>mixin-java-sdk</artifactId>
  <version>1.1.1-SNAPSHOT</version>
</dependency>
```
即可
#### 有问题请提issues，我看到会第一时间解答，对你有帮助请多多Star，感谢！！！
