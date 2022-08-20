package mixin.java.sdk.api;

import com.google.gson.JsonObject;
import mixin.java.sdk.algorithm.JWToken;
import mixin.java.sdk.api.client.impl.TestGroupInfoImpl;

public class UserService {

    public static JsonObject me(String access_token){
        JsonObject result = ApiUtils.invokeUserMethod(access_token,MixinURI.me,"");
        if(result == null){
            return null;
        }
        return result.get("data").getAsJsonObject();
    }

    public static JsonObject readUser(long groupId,String user_id){
        JsonObject result = ApiUtils.invokeSystemMethod(groupId,MixinURI.readUser,user_id);
        return result.get("data").getAsJsonObject();
    }

    public static JsonObject addUser(long groupId,String user_id,String full_name) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id",user_id);
        jsonObject.addProperty("full_name",full_name);
        jsonObject.addProperty("action","ADD");
        JsonObject result = ApiUtils.invokeSystemMethod(groupId,MixinURI.relationships,"",jsonObject.toString());
        return result.get("data").getAsJsonObject();
    }

    public static JsonObject clientFavorite(long groupId,String clientId){
        JsonObject result = ApiUtils.invokeSystemMethod(groupId,MixinURI.clientFavorite,clientId);
        return result.get("data").getAsJsonObject();
    }

    public static void main(String[] args) {
        JWToken.register(new TestGroupInfoImpl());
        //System.out.println(addUser(1,"16793499-37e9-4547-8211-f2d3dbe4cd2f","HL"));
        //System.out.println(clientFavorite(1,"1ab1f241-b809-4790-bcfd-a1779bb1d313"));
        System.out.println(clientFavorite(1,"16793499-37e9-4547-8211-f2d3dbe4cd2f"));
    }
}
