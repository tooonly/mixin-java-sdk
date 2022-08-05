package mixin.java.sdk.api.client;

import mixin.java.sdk.entity.Msg;

public interface MixinBot {

    void receiveMessage(Msg msg);

    void recallMessage(Msg msg);
}
