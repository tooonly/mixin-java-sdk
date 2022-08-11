package mixin.java.sdk.api.client;

import mixin.java.sdk.entity.Keystore;

import java.util.List;

public interface GroupInfo<T> {

    List<T> getGroups();

    <T> T getGroup(long id);

    Keystore getKeyStore(long groupId);
}
