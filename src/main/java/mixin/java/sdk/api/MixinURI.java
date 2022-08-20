package mixin.java.sdk.api;

public enum MixinURI {

    /**
     * Conversations
     */
    read_Conversations("/conversations/%s","get")
    ,add_conversations("/conversations","post")
    ,update_group_conversations("/conversations/%s","post")
    /**
     * messages
     */
    ,acknowledgements("/acknowledgements","post")
    ,sendMessages("/messages","post")
    ,encrypted_messages("/encrypted_messages","post")
    /**
     * attachments
     */
    ,attachments("/attachments","post")
    ,download("/attachments/%s","get")
    /**
     * user
     */
    ,me("/me")
    ,readUsers("/users/fetch","post")
    ,readUser("/users/%s","get")
    ,sessionFetch("/sessions/fetch","post")
    ,search("/search/%s","get")
    ,friends("/friends","get")
    ,relationships("/relationships","post")
    ,createNetUser("/users","post")
    /**
     * Favorite
     */
    ,clientFavorite("/apps/%s/favorite","post")
    ,userFavorite("/users/%s/apps/favorite","post")
    ,clientUnFavorite("/apps/%s/unfavorite","post")
    /**
     * Pin
     */
    ,pinUpdate("/pin/update","post")
    ,pinVerify("/pin/verify","post")
    ,pinErrLog("/logs?limit=%s&offset=%s&category=%s")
    /**
     * Assets
     */
    ,assets("/assets","get")
    ,assetsInfo("/assets/%s")
    ,assetsFee("/assets/%s/fee","get")
    /**
     * transfer
     */
    ,transfers("/transfers","post")
    ,transactions("/transactions","post")
    ,getSnapshots("/snapshots?limit=%s&offset=%s","get")
    ,getSnapshot("/snapshots/%s","post")
    ,payments("/payments","post")
    /**
     * session
     */
    ,sessionSecret("/session/secret","post")
    /**
     * withdraw
     */
    ,getAddresses("/assets/%s/addresses","get")
    ,getAddress("/addresses/%s","get")
    ,addAddress("/addresses","post")
    ,delAddress("/addresses/:addr_id/delete","post")
    ,withdrawals("/withdrawals","post")
    /**
     * network
     */
    ,readChains("/network/chains","get")
    ,readAsserts("/network/assets/%s","get")
    ,readAssertsTop("/network/assets/top","get")
    ,assertsSearch("/network/assets/search/%s","get")
    ,assertsMultisig("/network/assets/multisig","get")
    ,networkSnapshots("/network/snapshots/%s","get")
    ,networkSnapshot("/network/snapshots/%s","get")
    ,networkTicket("/network/ticker?asset=%s&offset=%s","get")
    ,pendingDeposits("/external/transactions?offset=%s&limit=%s&asset=%s&destination=%s&tag=%s","get")
    /**
     * external
     */
    ,externalFiats("/external/fiats","get")
    ,externalAddressCheck("/external/addresses/check","get")
    /**
     * multisigs
     */
    ,multisigsRequests("/multisigs/requests","post")
    ,multiSignature("/multisigs/requests/%s/%s","post")
    ,multiOutputs("/multisigs/outputs?members=%s&threshold=%s&state=%s&offset=%s&limit=%s&order=created","get")
    /**
     * outputs
     */
    ,ghostKey("/outputs","post")
    ,readOutputs("/outputs?state=%s&offset=%s&limit=%s&members=%s&threshold=%s")
    /**
     * NFT
     */
    ,collectiblesRequests("/collectibles/requests","post")
    ,collectiblesAction("/collectibles/requests/%s/%s","post")
    ,collectiblesOutputs("/outputs?state=%s&offset=%s&limit=%s&members=%s&threshold=%s","get")
    ,collectiblesToken("/collectibles/tokens/UUID","get")
    /**
     * codes
     */
    ,getCodes("/codes/%s","get")
    /**
     * albums
     */
    ,albums("/albums","post")
    /**
     * secret
     */
    ,rsaToEd25519("/session/secret","post")
    /**
     * circles
     */
    ,getCircles("/circles","get")
    ,getCircleById("/circles/%s","get")
    ,createCircle("/circles","post")
    ,updateCircle("/circles/%s","post")
    ,delCircle("/circles/%s/delete","post")
    ,addOrRemoveCircleUser("/users/%s/circles","post")
    ,addOrRemoveCircleGroup("/conversations/%s/circles","post")
    ,getCircleUsers("/circles/%s/conversations","get")
    /**
     * Schema
     */
    ,share("mixin://send?category=%s&conversation=%s&data=%s")
    ,pay("mixin://pay?recipient=%s&asset=%s&amount=%s&memo=%s&trace=%s")
    ,transfer("mixin://transfer/%s")
    ,snapshots("mixin://snapshots/%s")
    ,address("mixin://address?asset=%s&label=%s&destination=%s&tag=%s")
    ,deleteAddress("mixin://address?asset=%s&action=delete&address=%s")
    ,withdraw("mixin://withdrawal?address=&asset=&amount=&memo=&trace=")
    ,userProfile("mixin://users/%s")
    ,botProfile("mixin://apps/%s?action=open&%s")
    ,openConversation("mixin://conversations/%s?user=%s")
    ;

    private String uri;

    private String method;

    MixinURI(String uri){
        this.uri = uri;
    }

    MixinURI(String uri,String method){
        this.uri = uri;
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public String getMethod() {
        return method;
    }

}
