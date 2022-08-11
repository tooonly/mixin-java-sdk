package mixin.java.sdk;

import mixin.java.sdk.websocket.MixinWebsocketListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class App {

    @Autowired
    private MixinWebsocketListener mixinWebsocketListener;

    public void run(long groupId){
        mixinWebsocketListener.connectToRemoteMixin(groupId);
    }

}
