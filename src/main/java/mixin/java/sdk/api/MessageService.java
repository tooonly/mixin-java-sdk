package mixin.java.sdk.api;

import com.google.gson.*;
import mixin.java.sdk.algorithm.JWToken;
import mixin.java.sdk.api.client.impl.TestGroupInfoImpl;
import mixin.java.sdk.entity.Msg;
import mixin.java.sdk.util.*;
import okhttp3.WebSocket;

import java.util.Base64;
import java.util.UUID;

public class MessageService {

    public static boolean send(WebSocket webSocket, Action action, String params) {
        JsonObject jsObj = new JsonObject();
        Gson gson = new Gson();
        JsonElement jsXp = gson.fromJson(params, JsonElement.class);
        jsObj.addProperty("id", UUID.randomUUID().toString());
        jsObj.addProperty("action", action.toString());
        jsObj.add("params", jsXp);
        return webSocket.send(JsonUtil.jsonStrToByteString(jsObj.toString()));
    }

    public static void sendText(WebSocket webSocket,Msg msg){
        msg.setData(toBase64(msg.getData()));
        send(webSocket, Action.CREATE_MESSAGE, JsonUtil.toString(msg));
    }

    public static boolean sendListPendingMessages(WebSocket webSocket) {
        return send(webSocket, Action.LIST_PENDING_MESSAGES, (String)null);
    }

    public static boolean sendMessageAck(WebSocket webSocket, String messageId) {
        JsonObject params = new JsonObject();
        params.addProperty("message_id", messageId);
        params.addProperty("status", "READ");
        return send(webSocket, Action.ACKNOWLEDGE_MESSAGE_RECEIPT, params.toString());
    }

    static String toBase64(String orig) {
        return new String(Base64.getEncoder().encode(orig.getBytes()));
    }

    public static JsonObject sendMessage(long groupId,Msg... msgs) {
        JsonArray jsonArray = new JsonArray();
        for(Msg msg:msgs){
            msg.setData(toBase64(msg.getData()));
            jsonArray.add(JsonUtil.toJSON(msg));
        }
        System.out.println(jsonArray);
        JsonObject result = ApiUtils.invokeSystemMethod(groupId,MixinURI.sendMessages,"",jsonArray.toString());
        return result;
    }

    private static JsonObject buildMessage(JsonElement params,Action action){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id",UUID.randomUUID().toString());
        jsonObject.addProperty("action",action.toString());
        jsonObject.add("params",params);
        return jsonObject;
    }

    public static void main(String[] args) {
        JWToken.register(new TestGroupInfoImpl());
        Msg msg = new Msg();
        msg.setMessage_id(UUID.randomUUID().toString());
        msg.setData("??????????????????");
        msg.setRecipient_id("9356ba67-2a2b-4833-812c-1a23f388521b");
        msg.setConversation_id("e598bc50-56f9-42e1-af66-cc5d9f3c85e1");
        msg.setRepresentative_id("");
        msg.setCategory(Category.PLAIN_TEXT.toString());
        JsonObject result = sendMessage(1,msg);
        System.out.println(result);
    }
}
