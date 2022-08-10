package mixin.java.sdk.api.client;

import mixin.java.sdk.entity.Msg;
import okhttp3.WebSocket;

public interface MixinBot {

    void receiveMessage(WebSocket webSocket,Msg msg,long groupId);

    void recallMessage(WebSocket webSocket,Msg msg,long groupId);
}
