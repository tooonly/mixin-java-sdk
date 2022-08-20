package mixin.java.sdk.api.client;

import com.google.gson.JsonObject;
import okhttp3.WebSocket;

import java.io.UnsupportedEncodingException;

/**
 * 消息代理，将所有消息转发到该接口进行统一处理
 */
public interface MessageProxy {

    void receiveMsg(WebSocket webSocket, JsonObject obj, long groupId) throws UnsupportedEncodingException;
}
