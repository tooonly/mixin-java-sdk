package mixin.java.sdk.api.client.impl;

import mixin.java.sdk.api.MessageService;
import mixin.java.sdk.api.client.MixinBot;
import mixin.java.sdk.entity.Msg;
import okhttp3.WebSocket;
import org.springframework.stereotype.Component;

@Component
public class MixinBotTest implements MixinBot {
    @Override
    public void receiveMessage(WebSocket webSocket, Msg msg,long groupId) {
        System.out.println(msg);
        MessageService.sendMessageAck(webSocket,msg.getMessage_id());
    }

    @Override
    public void receivePinMessage(WebSocket webSocket, Msg msg, long groupId) {
        System.out.println(msg);
        MessageService.sendMessageAck(webSocket,msg.getMessage_id());
    }

    @Override
    public void recallMessage(WebSocket webSocket,Msg msg,long groupId) {
        System.out.println(msg);
        MessageService.sendMessageAck(webSocket,msg.getMessage_id());
    }
}
