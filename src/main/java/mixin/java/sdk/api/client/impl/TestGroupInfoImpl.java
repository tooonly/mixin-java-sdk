package mixin.java.sdk.api.client.impl;

import mixin.java.sdk.api.client.GroupInfo;
import mixin.java.sdk.entity.Keystore;
import mixin.java.sdk.util.Config;

/**
 * 获取当前群组的相关信息，比如Keystore等等，该实现类仅为Demo，在实际业务中需要自行实现该接口
 */
public class TestGroupInfoImpl implements GroupInfo {

    @Override
    public Keystore getKeyStore(long groupId) {
        return Config.keystore;
    }
}
