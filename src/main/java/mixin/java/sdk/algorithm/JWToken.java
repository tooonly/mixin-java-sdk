package mixin.java.sdk.algorithm;

import com.auth0.jwt.JWT;
import mixin.java.sdk.api.client.GroupInfo;
import mixin.java.sdk.entity.Keystore;
import mixin.java.sdk.util.Config;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.util.Date;
import java.util.UUID;

public class JWToken {

    private static GroupInfo groupInfo;

    public static void register(GroupInfo gInfo){
        groupInfo = gInfo;
    }

    public static String getToken(long groupId,String uri, String body) {
        return getToken(groupId,"GET", uri, body);
    }

    public static String getToken(long groupId,String method, String uri, String body) {
        return getToken(groupId,method, uri, body, UUID.randomUUID().toString());
    }

    public static String getToken(long groupId,String method, String uri, String body, String jti) {
        String sig = genSig(method, uri, body);
        long ts = System.currentTimeMillis();
        Keystore keystore = groupInfo.getKeyStore(groupId);
        try{
            String token = JWT.create().withClaim("uid", keystore.getClient_id()).withClaim("sid", keystore.getSession_id()).withIssuedAt(new Date(ts)).withExpiresAt(new Date(ts + 43200000L)).withClaim("sig", sig).withClaim("jti", jti).sign(new Ed25519(keystore.getPrivate_key()));
            return token;
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }

    private static String genSig(String method, String uri, String body) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return Hex.encodeHexString(md.digest((method + uri + body).getBytes()));
        } catch (Exception var4) {
            var4.printStackTrace();
            System.exit(1);
            return null;
        }
    }

}
