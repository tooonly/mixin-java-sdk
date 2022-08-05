package mixin.java.sdk.api.client;

import com.google.gson.JsonObject;
import okhttp3.WebSocket;

import java.io.UnsupportedEncodingException;

public interface MessageProxy {

    void receiveMsg(WebSocket webSocket, JsonObject obj, long groupId) throws UnsupportedEncodingException;
}
