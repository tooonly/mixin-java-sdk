package mixin.java.sdk.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import mixin.java.sdk.algorithm.JWToken;
import mixin.java.sdk.api.client.impl.TestGroupInfoImpl;
import mixin.java.sdk.util.MixinHttpUtil;

import java.io.IOException;

/**
 * 所有API的统一调用方法，不需要调用单独的API，只需要调用该接口就可以对接所有的Mixin API
 */
public class ApiUtils {

    /**
     * 用户侧接口，需要用到用户侧access_token
     * @param access_token
     * @param mixinURI
     * @param uriParam
     * @return
     */
    public static JsonObject invokeUserMethod(String access_token,MixinURI mixinURI,String uriParam){
        try {
            String uri = mixinURI.getUri();
            uri = String.format(uri,uriParam);
            String result = MixinHttpUtil.getToken(uri,access_token);
            JsonParser parser = new JsonParser();
            JsonElement jsonTree = parser.parse(result);
            System.out.println(jsonTree.getAsJsonObject());
            return jsonTree.getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JsonObject invokeSystemMethod(long groupId, MixinURI mixinURI){
        return invokeSystemMethod(groupId,mixinURI, "","");
    }

    public static JsonObject invokeSystemMethod(long groupId, MixinURI mixinURI, String uriParam){
        return invokeSystemMethod(groupId,mixinURI,uriParam,"");
    }

    /**
     * 服务端接口
     * @param groupId
     * @param mixinURI
     * @param uriParam
     * @param body
     * @return
     */
    public static JsonObject invokeSystemMethod(long groupId, MixinURI mixinURI, String uriParam, String body){
        try {
            String uri = mixinURI.getUri();
            uri = String.format(uri, uriParam);
            String method = mixinURI.getMethod();
            String result;
            if(method.equals("get")){
                result = MixinHttpUtil.get(groupId, uri, body);
            }else{
                result = MixinHttpUtil.post(groupId, uri, body);
            }
            JsonParser parser = new JsonParser();
            JsonElement jsonTree = parser.parse(result);
            return jsonTree.getAsJsonObject();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        JWToken.register(new TestGroupInfoImpl());
        System.out.println(invokeSystemMethod(1,MixinURI.sessionSecret,"",""));
    }
}
