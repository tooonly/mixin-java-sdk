package mixin.java.sdk;

import mixin.java.sdk.algorithm.JWToken;
import mixin.java.sdk.api.client.GroupInfo;
import mixin.java.sdk.api.client.impl.TestGroupInfoImpl;
import mixin.java.sdk.websocket.MixinWebsocketListener;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
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
