package mixin.java.sdk.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import mixin.java.sdk.algorithm.JWToken;
import mixin.java.sdk.api.client.impl.TestGroupInfoImpl;
import mixin.java.sdk.util.MixinHttpUtil;

import java.io.IOException;

public class User {

    public static JsonObject me(String access_token){
        try {
            String result = MixinHttpUtil.getToken(Constant.me,access_token);
            JsonParser parser = new JsonParser();
            JsonElement jsonTree = parser.parse(result);
            System.out.println(jsonTree.getAsJsonObject());
            return jsonTree.getAsJsonObject().get("data").getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JsonObject readUser(long groupId,String user_id){
        try {
            String uri = Constant.readUser;
            uri = String.format(uri, user_id);
            String result = MixinHttpUtil.get(groupId, uri, "");
            JsonParser parser = new JsonParser();
            JsonElement jsonTree = parser.parse(result);
            return jsonTree.getAsJsonObject().get("data").getAsJsonObject();
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static JsonObject addUser(long groupId,String user_id,String full_name) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("user_id",user_id);
            jsonObject.addProperty("full_name",full_name);
            jsonObject.addProperty("action","ADD");
            String result = MixinHttpUtil.post(groupId,Constant.userRelation, jsonObject.toString());
            JsonParser parser = new JsonParser();
            JsonElement jsonTree = parser.parse(result);
            System.out.println(jsonTree.getAsJsonObject());
            return jsonTree.getAsJsonObject().get("data").getAsJsonObject();
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static JsonObject clientFavorite(long groupId,String clientId){
        try {
            String uri = Constant.clientFavorite;
            uri = String.format(uri,clientId);
            String result = MixinHttpUtil.post(groupId,uri,"");
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
        JWToken.register(new TestGroupInfoImpl());
        //System.out.println(addUser(1,"16793499-37e9-4547-8211-f2d3dbe4cd2f","HL"));
        //System.out.println(clientFavorite(1,"1ab1f241-b809-4790-bcfd-a1779bb1d313"));
        System.out.println(readUser(1,"16793499-37e9-4547-8211-f2d3dbe4cd2f"));
    }
}
