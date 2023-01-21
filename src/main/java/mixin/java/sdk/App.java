package mixin.java.sdk;

import mixin.java.sdk.websocket.MixinWebsocketListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class App {
    /**
     * 这个类只是一个单例Demo，需要运行多机器人时，需要自己重写一个类，
     * MixinWebsocketListener每次都获取一个新的bean，类似于从BeanUtils.getBean(Class<T> clazz)获取一个新的bean
     */

    @Autowired
    private MixinWebsocketListener mixinWebsocketListener;

    public void run(long groupId){
        mixinWebsocketListener.connectToRemoteMixin(groupId);
    }

}
