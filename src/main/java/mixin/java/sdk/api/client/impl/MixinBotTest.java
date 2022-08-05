package mixin.java.sdk.api.client.impl;

import mixin.java.sdk.api.client.MixinBot;
import mixin.java.sdk.entity.Msg;
import org.springframework.stereotype.Component;

@Component
public class MixinBotTest implements MixinBot {
    @Override
    public void receiveMessage(Msg msg) {
        System.out.println(msg);
    }

    @Override
    public void recallMessage(Msg msg) {
        System.out.println(msg);
    }
}
