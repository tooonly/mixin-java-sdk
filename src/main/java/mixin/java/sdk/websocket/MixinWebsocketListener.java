package mixin.java.sdk.websocket;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import mixin.java.sdk.algorithm.JWToken;
import mixin.java.sdk.api.Constant;
import mixin.java.sdk.api.MessageService;
import mixin.java.sdk.api.client.MessageProxy;
import mixin.java.sdk.util.JsonUtil;
import okhttp3.*;
import okio.ByteString;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.EOFException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

@Component
@Scope("prototype")
public class MixinWebsocketListener extends WebSocketListener {

    @Resource
    private MessageProxy mixinMessageProxy;

    private static Logger logger = Logger.getLogger(MixinWebsocketListener.class);

    private long groupId;

    private boolean isConnect = false;

    private int connectNum = 0;

    private final static int MAX_NUM = 10;

    private final static int MILLIS = 5000;     // 重连间隔时间，毫秒

    private static OkHttpClient client;

    static {
        client = new OkHttpClient()
                .newBuilder()
                .connectionPool(
                        new ConnectionPool(100,1, TimeUnit.SECONDS)
                ).build();
    }

    public WebSocket connectToRemoteMixin(long groupId) {
        this.groupId = groupId;
        String token = JWToken.getToken(this.groupId,"/", "");
        Request request = (new okhttp3.Request.Builder()).addHeader("Sec-WebSocket-Protocol", "MixinBot-Blaze-1").addHeader("Authorization", "Bearer " + token).url(Constant.Websocket_URL_GLOBAL).build();
        return client.newWebSocket(request, this);
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        System.out.println("[onOpen !!!]");
        System.out.println("request header:" + response.request().headers());
        System.out.println("response header:" + response.headers());
        System.out.println("response:" + response);
        this.isConnect = response.code() == 101;
        // Request unread messages
        MessageService.sendListPendingMessages(webSocket);
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        System.out.println("[onMessage !!!]");
        System.out.println("text: " + text);
    }
    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        String msgIn = JsonUtil.bytesToJsonStr(bytes);
        JsonObject obj = new JsonParser().parse(msgIn).getAsJsonObject();
        logger.info(obj);
        try {
            mixinMessageProxy.receiveMsg(webSocket,obj,groupId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
    }


    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        System.out.println("[onClosing !!!]");
        System.out.println("code: " + code);
        System.out.println("reason: " + reason);
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        System.out.println("[onClosed !!!]");
        System.out.println("code: " + code);
        System.out.println("reason: " + reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        System.out.println("[onFailure !!!]");
        System.out.println("throwable: " + t);
        System.out.println("response: " + response);
        isConnect = false;
        webSocket.cancel();
        this.reconnect();
    }

    /**
     * 是否连接
     */
    public boolean isConnect() {
        return isConnect;
    }

    /**
     * 连接
     */
    public WebSocket connect() {
        if (isConnect()) {
            logger.info("WebSocket 已经连接！");
            return null;
        }
        return this.connectToRemoteMixin(this.groupId);
    }

    /**
     * 重连
     */
    public void reconnect() {
        System.out.println("[reconnect!!!]");
        if (connectNum <= MAX_NUM) {
            try {
                Thread.sleep(MILLIS);
                this.connect();
                connectNum++;
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.connectNum = 0;
        } else {
            logger.info( "reconnect over " + MAX_NUM + ",please check url or network");
        }
    }
}
