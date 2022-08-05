package mixin.java.sdk.util;

import com.google.gson.JsonObject;
import org.omg.CORBA.UNKNOWN;

public enum Category{

    MESSAGE_RECALL
    ,PLAIN_TEXT
    ,PLAIN_AUDIO
    ,PLAIN_VIDEO
    ,PLAIN_IMAGE
    ,PLAIN_STICKER
    ,PLAIN_CONTACT
    ,APP_CARD
    ,PLAIN_DATA
    ,PLAIN_LIVE
    ,PLAIN_LOCATION
    ,PLAIN_POST
    ,APP_BUTTON_GROUP
    ,SYSTEM_ACCOUNT_SNAPSHOT
    ,UNKNOWN;

    Category(){}

    public static Category parseFrom(JsonObject obj) {
        return parseFrom(obj.get("category").getAsString());
    }

    public static Category parseFrom(String value) {
        if (value == null) {
            throw new IllegalArgumentException("the value to parse cannot be null");
        } else if (value.length() == 0) {
            return null;
        } else {
            try {
                return valueOf(value);
            } catch (Exception var2) {
                return UNKNOWN;
            }
        }
    }
}
