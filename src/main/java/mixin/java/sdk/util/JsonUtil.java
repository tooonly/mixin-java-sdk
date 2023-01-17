package mixin.java.sdk.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import okio.ByteString;
import okio.GzipSink;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public abstract class JsonUtil<T> {

    public static String bytesToJsonStr(ByteString bytes) {
        return new String(Gzip.gzipUncompress(bytes.toByteArray()));
    }

    public static ByteString jsonStrToByteString(String json) {
        byte[] bytes = Gzip.gzipCompress(json.getBytes());
        return ByteString.of(bytes, 0, bytes.length);
    }

    public static String toString(Object object){
        JsonElement jsonElement = toJSON(object);
        return jsonElement.getAsString();
    }

    public static JsonElement toJSON(Object object){
        Gson gson = new Gson();
        String json = gson.toJson(object);
        JsonParser jsonParser = new JsonParser();
        return jsonParser.parse(json);
    }

    private static class Gzip {
        private Gzip() {}
        static byte[] gzipCompress(byte[] uncompressedData) {
            byte[] result = new byte[0];
            try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream(uncompressedData.length);
                Throwable var3 = null;
                try {
                    GZIPOutputStream gzipOS = new GZIPOutputStream(bos);
                    Throwable var5 = null;
                    try {
                        gzipOS.write(uncompressedData);
                        gzipOS.close();
                        result = bos.toByteArray();
                    } catch (Throwable var30) {
                        var5 = var30;
                        throw var30;
                    } finally {
                        if (gzipOS != null) {
                            if (var5 != null) {
                                try {
                                    gzipOS.close();
                                } catch (Throwable var29) {
                                    var5.addSuppressed(var29);
                                }
                            } else {
                                gzipOS.close();
                            }
                        }
                    }
                } catch (Throwable var32) {
                    var3 = var32;
                    throw var32;
                } finally {
                    if (bos != null) {
                        if (var3 != null) {
                            try {
                                bos.close();
                            } catch (Throwable var28) {
                                var3.addSuppressed(var28);
                            }
                        } else {
                            bos.close();
                        }
                    }
                }
            } catch (IOException var34) {
                var34.printStackTrace();
            }
            return result;
        }

        static byte[] gzipUncompress(byte[] compressedData) {
            byte[] result = new byte[0];
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(compressedData);
                Throwable var3 = null;
                try {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    Throwable var5 = null;
                    try {
                        GZIPInputStream gzipIS = new GZIPInputStream(bis);
                        Throwable var7 = null;
                        try {
                            byte[] buffer = new byte[1024];
                            int len;
                            while((len = gzipIS.read(buffer)) != -1) {
                                bos.write(buffer, 0, len);
                            }
                            result = bos.toByteArray();
                        } catch (Throwable var55) {
                            var7 = var55;
                            throw var55;
                        } finally {
                            if (gzipIS != null) {
                                if (var7 != null) {
                                    try {
                                        gzipIS.close();
                                    } catch (Throwable var54) {
                                        var7.addSuppressed(var54);
                                    }
                                } else {
                                    gzipIS.close();
                                }
                            }
                        }
                    } catch (Throwable var57) {
                        var5 = var57;
                        throw var57;
                    } finally {
                        if (bos != null) {
                            if (var5 != null) {
                                try {
                                    bos.close();
                                } catch (Throwable var53) {
                                    var5.addSuppressed(var53);
                                }
                            } else {
                                bos.close();
                            }
                        }
                    }
                } catch (Throwable var59) {
                    var3 = var59;
                    throw var59;
                } finally {
                    if (bis != null) {
                        if (var3 != null) {
                            try {
                                bis.close();
                            } catch (Throwable var52) {
                                var3.addSuppressed(var52);
                            }
                        } else {
                            bis.close();
                        }
                    }
                }
            } catch (IOException var61) {
                var61.printStackTrace();
            }
            return result;
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(new String(Base64.getEncoder().encode("17de49d0f65f5a86898beb827de4cd8b316d3548".getBytes())));
        System.out.println(new String(Base64.getDecoder().decode("wjkQwl8XRtPxrZxkkXmGClK3kJBclF16H09OL5iqvKM")));
    }
}
