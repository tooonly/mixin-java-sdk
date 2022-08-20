package mixin.java.sdk.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import mixin.java.sdk.entity.Keystore;
import mixin.java.sdk.util.MixinHttpUtil;

import java.io.IOException;

public class AuthService {

    /**
     * 用户授权成功后，跳转至回调地址，回调地址拿着code调用该接口可以获取access_token等信息
     * @param keystore
     * @param code
     * @return
     */
    public static JsonObject auth(Keystore keystore,String code){
        try {
            String result = MixinHttpUtil.post(keystore,code);
            JsonParser parser = new JsonParser();
            JsonElement jsonTree = parser.parse(result);
            JsonObject jsonObject = jsonTree.getAsJsonObject();
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
