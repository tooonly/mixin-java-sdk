package mixin.java.sdk.entity;

public class Keystore {

    private String pin;

    private String client_id;

    private String session_id;

    private String pin_token;

    private String private_key;

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getPin_token() {
        return pin_token;
    }

    public void setPin_token(String pin_token) {
        this.pin_token = pin_token;
    }

    public String getPrivate_key() {
        return private_key;
    }

    public void setPrivate_key(String private_key) {
        this.private_key = private_key;
    }

}
