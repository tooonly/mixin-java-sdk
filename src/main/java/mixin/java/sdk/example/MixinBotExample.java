package mixin.java.sdk.example;

import mixin.java.sdk.api.MessageService;
import mixin.java.sdk.api.client.MixinBot;
import mixin.java.sdk.entity.Msg;
import mixin.java.sdk.util.Category;
import okhttp3.WebSocket;

import java.util.UUID;

public class MixinBotExample implements MixinBot {
    @Override
    public void receiveMessage(WebSocket webSocket, Msg msg,long groupId) {
        System.out.println(msg);
        Msg callbackMsg = new Msg();
        callbackMsg.setMessage_id(UUID.randomUUID().toString());
        callbackMsg.setConversation_id(msg.getConversation_id());
        callbackMsg.setCategory(Category.PLAIN_TEXT.toString());
        callbackMsg.setData("你的消息已收到!!!");
        callbackMsg.setRecipient_id(msg.getUser_id());
        MessageService.sendText(webSocket,callbackMsg);
        MessageService.sendMessageAck(webSocket,msg.getMessage_id());
    }

    @Override
    public void receivePinMessage(WebSocket webSocket, Msg msg, long groupId) {
        System.out.println(msg);
        Msg callbackMsg = new Msg();
        callbackMsg.setMessage_id(UUID.randomUUID().toString());
        callbackMsg.setConversation_id(msg.getConversation_id());
        callbackMsg.setCategory(Category.PLAIN_TEXT.toString());
        callbackMsg.setData("你置顶了一条消息!!!");
        callbackMsg.setRecipient_id(msg.getUser_id());
        MessageService.sendText(webSocket,callbackMsg);
        MessageService.sendMessageAck(webSocket,msg.getMessage_id());
    }

    @Override
    public void recallMessage(WebSocket webSocket,Msg msg,long groupId) {
        System.out.println(msg);
        Msg callbackMsg = new Msg();
        callbackMsg.setMessage_id(UUID.randomUUID().toString());
        callbackMsg.setConversation_id(msg.getConversation_id());
        callbackMsg.setCategory(Category.PLAIN_TEXT.toString());
        callbackMsg.setData("你撤回了一条消息!!!");
        callbackMsg.setRecipient_id(msg.getUser_id());
        MessageService.sendText(webSocket,callbackMsg);
        MessageService.sendMessageAck(webSocket,msg.getMessage_id());
    }
}
