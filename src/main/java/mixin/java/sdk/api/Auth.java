package mixin.java.sdk.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import mixin.java.sdk.util.MixinHttpUtil;

import java.io.IOException;

public class Auth {

    public static JsonObject auth(String code){
        try {
            String result = MixinHttpUtil.post(code);
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
