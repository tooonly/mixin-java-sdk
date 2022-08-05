package mixin.java.sdk.util;

import com.google.gson.Gson;
import mixin.java.sdk.entity.Keystore;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Config {

    public static Keystore keystore = readJsonConfig("keystore.json",Keystore.class);

    public static String client_secret = "705f56b78653c3e96136fbd68bec39c13d26e2e466c2c09e6fbb9ec6283754e6";

    public static <T> T readJsonConfig(String filePath,Class<T> classOfT){
        InputStream fis = Config.class.getClassLoader().getResourceAsStream(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        StringBuilder builder = new StringBuilder();
        String line;
        try{
            for(line = br.readLine(); line != null; line = br.readLine()) {
                builder.append(line);
            }
        }catch (Exception e){}
        line = null;
        Gson gson = new Gson();
        return gson.fromJson(builder.toString(), classOfT);
    }

}
