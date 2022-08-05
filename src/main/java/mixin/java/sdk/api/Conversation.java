package mixin.java.sdk.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import mixin.java.sdk.algorithm.MD5;
import mixin.java.sdk.util.Config;
import mixin.java.sdk.util.MixinHttpUtil;

import java.io.IOException;
import java.util.UUID;

public class Conversation {

    public static String UniqueConversationId(String userId0,String userId1){
        String minId = userId0,maxId = userId1;
        if(userId0.compareTo(userId1) > 0){
            maxId = userId1;
            minId = userId0;
        }
        String conversation = minId + maxId;
        conversation = MD5.getMD5Str(conversation);
        byte[] bytes = conversation.getBytes();
        bytes[6] = (byte) ((bytes[6] & 0x0f) | 0x30);
        bytes[8] = (byte) ((bytes[8] & 0x3f) | 0x80);
        return UUID.nameUUIDFromBytes(bytes).toString();
    }

    public static JsonObject contact(long groupId,String category,String name,String... userIds) {
        try{
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("category",category);
            String conversationId;
            if(userIds.length == 1){
                conversationId = UniqueConversationId(userIds[0],Config.keystore.getClient_id());
            }else{
                conversationId = UUID.randomUUID().toString();
            }
            jsonObject.addProperty("conversation_id",conversationId);
            jsonObject.addProperty("name",name);
            jsonObject.addProperty("creator_id","631b3606-26e2-4440-b6e9-365af1d20b83");
            JsonArray jsonArray = new JsonArray();
            JsonObject user = new JsonObject();
            for(String userId:userIds){
                user.addProperty("user_id",userId);
            }
            jsonArray.add(user);
            jsonObject.add("participants",jsonArray);
            String result = MixinHttpUtil.post(groupId,Constant.conversations,jsonObject.toString());
            JsonParser parser = new JsonParser();
            JsonElement jsonTree = parser.parse(result);
            System.out.println(jsonTree.getAsJsonObject());
            return jsonTree.getAsJsonObject().get("data").getAsJsonObject();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static JsonObject readConversation(long groupId,String conversationId) {
        try {
            String uri = Constant.readConversations;
            uri = String.format(uri, conversationId);
            String result = MixinHttpUtil.get(groupId,uri, "");
            JsonParser parser = new JsonParser();
            JsonElement jsonTree = parser.parse(result);
            System.out.println(jsonTree.getAsJsonObject());
            return jsonTree.getAsJsonObject().get("data").getAsJsonObject();
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        //System.out.println(UniqueConversationId("631b3606-26e2-4440-b6e9-365af1d20b83",Config.keystore.getClient_id()));
        System.out.println(contact(1,"GROUP","Mixin Java SDK","2d8ef69d-4132-46d7-bfd8-36fe8db4ddb3","631b3606-26e2-4440-b6e9-365af1d20b83"));
        //System.out.println(readConversation("631b3606-26e2-4440-b6e9-365af1d20b83"));
    }
}
