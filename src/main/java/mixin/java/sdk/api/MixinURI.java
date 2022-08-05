package mixin.java.sdk.api;

import com.google.gson.JsonObject;

public enum MixinURI {

    //Conversations
    read_Conversations("/conversations/%s")
    ,add_conversations("/conversations","post","{'category':'%s','conversation_id':'%s','name':'%s','participants':'%s'}")
    ,update_group_conversations("/conversations/%s","post","{'name':'%s','announcement':'%s'}")
    //messages

    ;

    private String uri;

    private String method;

    private String bodyJson;

    MixinURI(String uri){
        this.uri = uri;
    }

    MixinURI(String uri,String method,String bodyJson){
        this.uri = uri;
        this.method = method;
        this.bodyJson = bodyJson;
    }

    public String getUri() {
        return uri;
    }

    public String getMethod() {
        return method;
    }

    public String getBodyJson() {
        return bodyJson;
    }
}
