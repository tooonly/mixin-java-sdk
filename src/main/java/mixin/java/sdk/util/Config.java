package mixin.java.sdk.util;

import com.google.gson.Gson;
import mixin.java.sdk.entity.Keystore;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Config只用作单群应用，编辑好配置文件keystore.json即可，
 * 需要多群应用请实现GroupInfo接口
 */
public class Config {

    public static Keystore keystore = readJsonConfig("keystore.json",Keystore.class);

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
