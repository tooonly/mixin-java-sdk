package mixin.java.sdk.api.client;

import mixin.java.sdk.entity.Msg;
import okhttp3.WebSocket;

/**
 * Mixin机器人服务，主要用于机器人交互、群聊消息的处理
 */
public interface MixinBot {

    void receiveMessage(WebSocket webSocket,Msg msg,long groupId);

    void receivePinMessage(WebSocket webSocket,Msg msg,long groupId);

    void recallMessage(WebSocket webSocket,Msg msg,long groupId);
}
