package mixin.java.sdk.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import mixin.java.sdk.util.MixinHttpUtil;

import java.io.File;
import java.io.IOException;

public class Attachment {

    public static JsonObject attachments(long groupId) {
        try {
            String result = MixinHttpUtil.post(groupId,Constant.attachments,"");
            JsonParser parser = new JsonParser();
            JsonElement jsonTree = parser.parse(result);
            System.out.println(jsonTree.getAsJsonObject());
            return jsonTree.getAsJsonObject().get("data").getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String upload(String url,File file){
        try {
            String result = MixinHttpUtil.put(url,file);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JsonObject getAttachments(long groupId,String attachmentId){
        try {
            String uri = Constant.getAttachments;
            uri = String.format(uri,attachmentId);
            String result = MixinHttpUtil.get(groupId,uri,"");
            JsonParser parser = new JsonParser();
            JsonElement jsonTree = parser.parse(result);
            System.out.println(jsonTree.getAsJsonObject());
            return jsonTree.getAsJsonObject().get("data").getAsJsonObject();
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        /**JsonObject jsonObject = attachments();
        String upload_url = jsonObject.get("upload_url").getAsString();
        File file = new File("/Users/tulingnengliang/Documents/学习/沟通/沟通的方法-脱不花 by Unknown (z-lib.org).epub");
        System.out.println(upload(upload_url,file));*/
        JsonObject jsonObject = getAttachments(1,"39300118-ca93-4df7-ae4c-559a00fd6081");
        System.out.println(jsonObject);
    }
}
