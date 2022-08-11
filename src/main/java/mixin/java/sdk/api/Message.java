package mixin.java.sdk.api;

import com.google.gson.*;
import mixin.java.sdk.algorithm.JWToken;
import mixin.java.sdk.api.client.impl.TestGroupInfoImpl;
import mixin.java.sdk.entity.Msg;
import mixin.java.sdk.util.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

public class Message {

    public static boolean send(WebSocket webSocket, Action action, String params) {
        JsonObject jsObj = new JsonObject();
        Gson gson = new Gson();
        JsonElement jsXp = (JsonElement)gson.fromJson(params, JsonElement.class);
        jsObj.addProperty("id", UUID.randomUUID().toString());
        jsObj.addProperty("action", action.toString());
        jsObj.add("params", jsXp);
        return webSocket.send(JsonUtil.jsonStrToByteString(jsObj.toString()));
    }

    public static void sendText(WebSocket webSocket,Msg msg){
        JsonObject params = new JsonObject();
        params.addProperty("conversation_id",msg.getConversation_id());
        if(StringUtils.isNotBlank(msg.getRecipient_id())){
            params.addProperty("recipient_id",msg.getRecipient_id());
        }
        params.addProperty("message_id",msg.getMessage_id());
        params.addProperty("category",msg.getCategory());
        if(StringUtils.isNotBlank(msg.getRepresentative_id())){
            params.addProperty("representative_id",msg.getRepresentative_id());
        }
        if(StringUtils.isNotBlank(msg.getQuote_message_id())){
            params.addProperty("quote_message_id",msg.getQuote_message_id());
        }
        params.addProperty("data",toBase64(msg.getData()));
        send(webSocket, Action.CREATE_MESSAGE, params.toString());
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
        try {
            JsonArray jsonArray = new JsonArray();
            for(Msg msg:msgs){
                JsonObject params = new JsonObject();
                params.addProperty("conversation_id",msg.getConversation_id());
                params.addProperty("recipient_id",msg.getRecipient_id());
                params.addProperty("message_id",msg.getMessage_id());
                params.addProperty("category",msg.getCategory());
                if(StringUtils.isNotBlank(msg.getRepresentative_id())){
                    params.addProperty("representative_id",msg.getRepresentative_id());
                }
                if(StringUtils.isNotBlank(msg.getQuote_message_id())){
                    params.addProperty("quote_message_id",msg.getQuote_message_id());
                }
                params.addProperty("data",toBase64(msg.getData()));
                jsonArray.add(params);
            }
            String result = MixinHttpUtil.post(groupId,Constant.sendMessages, jsonArray.toString());
            JsonParser parser = new JsonParser();
            JsonObject jsonTree = (JsonObject) parser.parse(result);
            return jsonTree;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
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
        /**Msg msg = new Msg("58c200f9-4b29-357d-b605-8e664bccda59", "2d8ef69d-4132-46d7-bfd8-36fe8db4ddb3",UUID.randomUUID().toString(),MessageCategory.PLAIN_TEXT.toString(),"机器人代发消息测试");
        msg.setRepresentative_id("631b3606-26e2-4440-b6e9-365af1d20b83");
        msg.setQuote_message_id("57b914d9-85fd-43b8-a74a-016e0cce236a");
        //userId:2d8ef69d-4132-46d7-bfd8-36fe8db4ddb3 631b3606-26e2-4440-b6e9-365af1d20b83
        //conversationId:58c200f9-4b29-357d-b605-8e664bccda59 242d5912-eb2a-32e5-b0a9-d762fabe3960
        JsonObject jsonObject = sendMessage(msg);
        System.out.println(jsonObject);*/
        //System.out.println(new String(Base64.getUrlDecoder().decode("eyJtZXNzYWdlX2lkIjoiZDRhYTIwYjYtYTk4Ny00MjQ0LThkOWMtZmI1ZjNmM2Y3MTEyIn0=")));
        Msg callbackMsg = new Msg();
        callbackMsg.setMessage_id(UUID.randomUUID().toString());
        callbackMsg.setConversation_id("0f4e2f99-fadf-3d62-90e9-51c74f2c08c1");
        callbackMsg.setCategory(Category.PLAIN_TEXT.toString());
        callbackMsg.setData("系统消息!!!");
        callbackMsg.setRecipient_id("631b3606-26e2-4440-b6e9-365af1d20b83");
        Message.sendMessage(1,callbackMsg);
    }
}
