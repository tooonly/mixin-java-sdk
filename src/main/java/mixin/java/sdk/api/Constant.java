package mixin.java.sdk.api;

public interface Constant {

    String HTTP_URL = "https://api.mixin.one";

    String HTTP_URL_GLOBAL = "https://mixin-api.zeromesh.net";

    String Websocket_URL = "wss://blaze.mixin.one/";

    String Websocket_URL_GLOBAL = "wss://mixin-blaze.zeromesh.net";

    String conversations = "/conversations";

    String readConversations = "/conversations/%s";

    String sendMessages = "/messages";

    String acknowledgements = "/acknowledgements";

    String userRelation = "/relationships";

    String attachments = "/attachments";

    String getAttachments = "/attachments/%s";

    String authToken = "/oauth/token";

    String me = "/me";

    String readUser = "/users/%s";

    String clientFavorite = "/apps/%s/favorite";

    String userFavorite = "/users/%s/apps/favorite";

}
