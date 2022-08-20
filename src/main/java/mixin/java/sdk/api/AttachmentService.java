package mixin.java.sdk.api;

import com.google.gson.JsonObject;
import mixin.java.sdk.util.MixinHttpUtil;

import java.io.File;
import java.io.IOException;

public class AttachmentService {

    public static JsonObject attachments(long groupId) {
        JsonObject result = ApiUtils.invokeSystemMethod(groupId,MixinURI.attachments);
        return result.get("data").getAsJsonObject();
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
        JsonObject result = ApiUtils.invokeSystemMethod(groupId,MixinURI.download);
        return result.get("data").getAsJsonObject();
    }

    public static void main(String[] args) throws IOException {
        JsonObject jsonObject = getAttachments(1,"39300118-ca93-4df7-ae4c-559a00fd6081");
        System.out.println(jsonObject);
    }
}
