package mixin.java.sdk.api;

import com.google.gson.*;
import mixin.java.sdk.algorithm.JWToken;
import mixin.java.sdk.api.client.impl.TestGroupInfoImpl;

import java.util.UUID;

public class ConversationService {

    public static String UniqueConversationId(String userId0,String userId1){
        String[] userIds = new String[]{userId0,userId1};
        byte i = 0;
        if(userId0.compareTo(userId1) > 0){
            i = 1;
        }
        String conversation = userIds[0^i] + userIds[1^i];
        return UUID.nameUUIDFromBytes(conversation.getBytes()).toString();
    }

    public static JsonObject contact(long groupId,String name,String... userIds){
        JsonObject jsonObject = new JsonObject();
        String conversationId;
        if(userIds.length > 1){
            jsonObject.addProperty("category","GROUP");
            conversationId = UUID.randomUUID().toString();
        }else {
            jsonObject.addProperty("category","CONTACT");
            String client_id = JWToken.getGroupInfo().getKeyStore(groupId).getClient_id();
            conversationId = UniqueConversationId(client_id,userIds[0]);
        }
        jsonObject.addProperty("conversation_id", conversationId);
        jsonObject.addProperty("name",name);
        JsonArray jsonArray = new JsonArray();
        for(String userId:userIds){
            JsonObject user = new JsonObject();
            user.addProperty("user_id",userId);
            jsonArray.add(user);
        }
        jsonObject.add("participants",jsonArray);
        JsonObject result = ApiUtils.invokeSystemMethod(groupId,MixinURI.add_conversations,"",jsonObject.toString());
        return result.get("data").getAsJsonObject();
    }

    public static JsonObject readConversation(long groupId,String conversationId) {
        JsonObject result = ApiUtils.invokeSystemMethod(groupId,MixinURI.read_Conversations,conversationId);
        return result.get("data").getAsJsonObject();
    }

    public static JsonObject updateGroup(long groupId,String conversationId,JsonObject jsonObject){
        JsonObject result = ApiUtils.invokeSystemMethod(groupId,MixinURI.update_group_conversations,conversationId);
        return result.get("data").getAsJsonObject();
    }

    public static void main(String[] args) {
        JWToken.register(new TestGroupInfoImpl());
        //System.out.println(UniqueConversationId("631b3606-26e2-4440-b6e9-365af1d20b83",Config.keystore.getClient_id()));
        //System.out.println(contact(1,"GROUP","GROUP","测试","631b3606-26e2-4440-b6e9-365af1d20b83","9356ba67-2a2b-4833-812c-1a23f388521b"));
        //System.out.println(readConversation("631b3606-26e2-4440-b6e9-365af1d20b83"));
        /**JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name","测试");
        jsonObject.addProperty("announcement","群聊功能即将上线，敬请期待!");
        System.out.println(updateGroup(1,"e598bc50-56f9-42e1-af66-cc5d9f3c85e1",jsonObject));*/
        //System.out.println(contact(1,"","9356ba67-2a2b-4833-812c-1a23f388521b"));
        System.out.println(UniqueConversationId("631b3606-26e2-4440-b6e9-365af1d20b83","16793499-37e9-4547-8211-f2d3dbe4cd2f"));
    }
}
