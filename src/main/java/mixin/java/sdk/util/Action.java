package mixin.java.sdk.util;

import com.google.gson.JsonObject;

public enum Action {

    CREATE_MESSAGE
    ,MESSAGE_RECALL
    ,LIST_PENDING_MESSAGES
    ,ACKNOWLEDGE_MESSAGE_RECEIPT
    ,NOT_IMPLEMENTED_BY_THIS_SDK_YET;

    Action(){}

    public static Action parseFrom(JsonObject obj) {
        return parseFrom(obj.get("action").getAsString());
    }

    public static Action parseFrom(String value) {
        if (value == null) {
            throw new IllegalArgumentException("the value to parse cannot be null");
        } else if (value.length() == 0) {
            return null;
        } else {
            try {
                return valueOf(value);
            } catch (Exception var2) {
                return NOT_IMPLEMENTED_BY_THIS_SDK_YET;
            }
        }
    }
}
