package mixin.java.sdk.api.client;

import mixin.java.sdk.entity.Keystore;

import java.util.List;

/**
 * 对于单群应用可以直接实现一个GroupInfo，返回当前组的配置信息即可
 * 对于多群Saas应用需要从数据库中查询所有群的配置，然后进行组装数据返回
 * @param <T>
 */
public interface GroupInfo<T> {

    List<T> getGroups();

    <T> T getGroup(long id);

    Keystore getKeyStore(long groupId);
}
