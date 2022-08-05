package mixin.java.sdk.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import mixin.java.sdk.util.MixinHttpUtil;

import java.io.IOException;

public class ApiUtils {

    public static JsonObject invokeUserMethod(String access_token,MixinURI mixinURI,String uriParam){
        try {
            String uri = mixinURI.getUri();
            uri = String.format(uri,uriParam);
            String result = MixinHttpUtil.getToken(uri,access_token);
            JsonParser parser = new JsonParser();
            JsonElement jsonTree = parser.parse(result);
            System.out.println(jsonTree.getAsJsonObject());
            return jsonTree.getAsJsonObject().get("data").getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JsonObject invokeSystemMethod(long groupId,MixinURI mixinURI,String uriParam,String... args){
        try {
            String uri = mixinURI.getUri();
            uri = String.format(uri, uriParam);
            String method = mixinURI.getMethod();
            String result;
            if(method.equals("get")){
                result = MixinHttpUtil.get(groupId, uri, "");
            }else{
                String body = mixinURI.getBodyJson();
                body = String.format(body, args);
                result = MixinHttpUtil.post(groupId, uri, body);
            }
            JsonParser parser = new JsonParser();
            JsonElement jsonTree = parser.parse(result);
            return jsonTree.getAsJsonObject().get("data").getAsJsonObject();
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
