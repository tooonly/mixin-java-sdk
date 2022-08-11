package mixin.java.sdk.entity;

public class Msg {

    //会话ID
    private String conversation_id = "";

    //接收人ID
    private String recipient_id = "";

    //消息ID
    private String message_id = "";

    //消息类型
    private String category = "";

    //代发消息人ID
    private String representative_id = "";

    //引用消息ID
    private String quote_message_id = "";

    //消息内容
    private String data = "";

    //用户ID
    private String user_id = "";

    //会话ID
    private String session_id = "";

    //Base64消息内容
    private String data_base64 = "";

    private String created_at = "";

    private String updated_at = "";

    //原始消息ID
    private String original_message_id = "";

    public Msg() {
    }

    public String getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(String conversation_id) {
        this.conversation_id = conversation_id;
    }

    public String getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(String recipient_id) {
        this.recipient_id = recipient_id;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRepresentative_id() {
        return representative_id;
    }

    public void setRepresentative_id(String representative_id) {
        this.representative_id = representative_id;
    }

    public String getQuote_message_id() {
        return quote_message_id;
    }

    public void setQuote_message_id(String quote_message_id) {
        this.quote_message_id = quote_message_id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getData_base64() {
        return data_base64;
    }

    public void setData_base64(String data_base64) {
        this.data_base64 = data_base64;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getOriginal_message_id() {
        return original_message_id;
    }

    public void setOriginal_message_id(String original_message_id) {
        this.original_message_id = original_message_id;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "conversation_id='" + conversation_id + '\'' +
                ", recipient_id='" + recipient_id + '\'' +
                ", message_id='" + message_id + '\'' +
                ", category='" + category + '\'' +
                ", representative_id='" + representative_id + '\'' +
                ", quote_message_id='" + quote_message_id + '\'' +
                ", data='" + data + '\'' +
                ", user_id='" + user_id + '\'' +
                ", session_id='" + session_id + '\'' +
                ", data_base64='" + data_base64 + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", original_message_id='" + original_message_id + '\'' +
                '}';
    }
}
