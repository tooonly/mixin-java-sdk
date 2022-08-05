package mixin.java.sdk.api.client.impl;

import mixin.java.sdk.api.client.MixinBot;
import mixin.java.sdk.entity.Msg;
import org.springframework.stereotype.Service;

@Service
public class MixinBotTest implements MixinBot {
    @Override
    public void receiveMessage(Msg msg) {

    }

    @Override
    public void recallMessage(Msg msg) {

    }
}
