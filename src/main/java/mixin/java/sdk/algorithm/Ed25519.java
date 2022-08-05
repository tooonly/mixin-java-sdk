package mixin.java.sdk.algorithm;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import net.i2p.crypto.eddsa.EdDSAEngine;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.spec.EdDSANamedCurveTable;
import net.i2p.crypto.eddsa.spec.EdDSAParameterSpec;
import net.i2p.crypto.eddsa.spec.EdDSAPrivateKeySpec;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;


public class Ed25519 extends Algorithm {

    private Signature signature = null;

    private PrivateKey privateKey = null;

    private String hashAlgorithm = "";

    Ed25519(String key) {
        super("EdDSA", "Ed25519");
        byte[] seed = Base64.getUrlDecoder().decode(key);
        // 取前32位字节私钥
        byte[] privSeed = Arrays.copyOfRange(seed, 0, 32);
        // ed25519 签名
        EdDSAParameterSpec spec = EdDSANamedCurveTable.getByName(EdDSANamedCurveTable.ED_25519);
        EdDSAPrivateKeySpec privKey = new EdDSAPrivateKeySpec(privSeed, spec);
        privateKey = new EdDSAPrivateKey(privKey);
        hashAlgorithm = spec.getHashAlgorithm();
    }

    @Override
    public void verify(DecodedJWT jwt) {
        Boolean isSuccess = false;
        byte[] contentBytes = String.format("%s.%s", jwt.getHeader(), jwt.getPayload()).getBytes(StandardCharsets.UTF_8);
        byte[] signatureBytes = org.apache.commons.codec.binary.Base64.decodeBase64(jwt.getSignature());
        try {
            signature.update(contentBytes);
            isSuccess = signature.verify(signatureBytes);
        } catch (SignatureException e) {
            e.printStackTrace();
            isSuccess = false;
        } finally {
            if (!isSuccess) {
                throw new SignatureVerificationException(this);
            }
        }
    }

    @Override
    public byte[] sign(byte[] contentBytes) {
        try {
            signature = new EdDSAEngine(MessageDigest.getInstance(hashAlgorithm));
            signature.initSign(privateKey);
            signature.update(contentBytes);
            byte[] enData = signature.sign();
            return enData;
        } catch (SignatureException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

}
