package mixin.java.sdk.util;

import com.google.gson.JsonObject;
import mixin.java.sdk.algorithm.JWToken;
import mixin.java.sdk.api.Constant;
import mixin.java.sdk.entity.Keystore;
import okhttp3.*;
import okhttp3.Request.Builder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MixinHttpUtil {
    private static final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType OCTET_STREAM = MediaType.parse("application/octet-stream");

    public MixinHttpUtil() {
    }

    public static HashMap<String, String> makeHeaders(String token) {
        HashMap<String, String> headers = new HashMap();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + token);
        return headers;
    }

    public static HashMap<String, String> makeFileHeaders(File file){
        HashMap<String, String> headers = new HashMap();
        headers.put("x-amz-acl","public-read");
        headers.put("Content-Length",file.length()+"");
        return headers;
    }

    public static String getToken(String url,String token) throws IOException {
        String fullUrl = Constant.HTTP_URL_GLOBAL + url;
        Request request = (new Builder()).header("Authorization", "Bearer " + token).url(fullUrl).build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        } else {
            return response.body().string();
        }
    }

    public static String get(long groupId,String url,String body) throws IOException {
        String token = JWToken.getToken(groupId,"GET", url, body);
        return getToken(url,token);
    }

    public static String post(long groupId,String uri, String body) throws IOException {
        String token = JWToken.getToken(groupId,"POST", uri, body);
        String fullUrl = Constant.HTTP_URL_GLOBAL + uri;
        Builder builder = (new Builder()).url(fullUrl).post(RequestBody.create(JSON, body));
        Map<String,String> headers = makeHeaders(token);
        if (headers.size() > 0) {
            Iterator var4 = headers.entrySet().iterator();

            while(var4.hasNext()) {
                Entry<String, String> entry = (Entry)var4.next();
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }

        Request request = builder.build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        } else {
            return response.body().string();
        }
    }

    public static String post(Keystore keystore, String code) throws IOException {
        String fullUrl = Constant.HTTP_URL_GLOBAL + Constant.authToken;
        JsonObject body = new JsonObject();
        body.addProperty("client_id",keystore.getClient_id());
        body.addProperty("code",code);
        body.addProperty("client_secret",keystore.getClient_secret());
        Builder builder = (new Builder()).url(fullUrl).post(RequestBody.create(JSON, body.toString()));
        Request request = builder.build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        } else {
            return response.body().string();
        }
    }

    public static String put(String url, File file) throws IOException {
        Builder builder = (new Builder()).url(url).put(RequestBody.create(OCTET_STREAM,file));
        Map<String,String> headers = makeFileHeaders(file);
        if (headers.size() > 0) {
            Iterator var4 = headers.entrySet().iterator();

            while(var4.hasNext()) {
                Entry<String, String> entry = (Entry)var4.next();
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }

        Request request = builder.build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        } else {
            return response.body().string();
        }
    }

}
