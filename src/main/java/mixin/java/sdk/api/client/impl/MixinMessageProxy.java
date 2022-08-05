package mixin.java.sdk.api.client.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import mixin.java.sdk.api.client.MessageProxy;
import mixin.java.sdk.api.client.MixinBot;
import mixin.java.sdk.entity.Msg;
import mixin.java.sdk.util.Action;
import mixin.java.sdk.util.Category;
import okhttp3.WebSocket;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Component
public class MixinMessageProxy implements MessageProxy {

    @Resource
    private MixinBot mixinBot;

    private static Logger logger = Logger.getLogger(MixinMessageProxy.class);
    @Override
    public void receiveMsg(WebSocket webSocket,JsonObject obj, long groupId) throws UnsupportedEncodingException {
        Action action = Action.parseFrom(obj);
        switch (action){
            case LIST_PENDING_MESSAGES:
                logger.info("LIST_PENDING_MESSAGES：");
                break;
            case CREATE_MESSAGE:
                logger.info("CREATE_MESSAGE："+obj);
                if(!obj.has("data") || obj.get("data") == null){
                    logger.info("CREATE_MESSAGE:data is empty");
                    break;
                }
                JsonObject data = obj.get("data").getAsJsonObject();
                Category category = Category.parseFrom(data);
                Gson gson = new Gson();
                Msg msg = gson.fromJson(data,Msg.class);
                String dataContent = data.get("data").getAsString();
                if(StringUtils.isBlank(dataContent)){
                    break;
                }
                String dataStr = new String(Base64.getDecoder().decode(dataContent), "UTF-8");
                msg.setData(dataStr);
                logger.info(msg);
                switch (category){
                    case MESSAGE_RECALL:
                        /**
                         * 需要将消息及所有转发的消息都进行撤回
                         */
                        mixinBot.recallMessage(msg);
                        break;
                    case UNKNOWN:
                        logger.warn("category unknown!!!"+category);
                        break;
                    default:
                        /**
                        //List<Msg> msgs = userService.getMsgs();
                        Msg callbackMsg = new Msg();
                        callbackMsg.setMessage_id(UUID.randomUUID().toString());
                        callbackMsg.setConversation_id(msg.getConversation_id());
                        callbackMsg.setCategory(Category.PLAIN_TEXT.toString());
                        callbackMsg.setData("你的消息已收到!!!");
                        callbackMsg.setRecipient_id(msg.getUser_id());
                        Message.sendMessage(groupId,callbackMsg);
                        Message.sendMessageAck(webSocket,data.get("message_id").getAsString());
                        break;
                     */
                        mixinBot.receiveMessage(msg);

                }
            case ACKNOWLEDGE_MESSAGE_RECEIPT:
                logger.info("ACKNOWLEDGE_MESSAGE_RECEIPT：");
                break;
            case NOT_IMPLEMENTED_BY_THIS_SDK_YET:
                logger.info("NOT_IMPLEMENTED_BY_THIS_SDK_YET：");
                break;
        }
    }

    public static void main(String[] args) {
        System.out.println(new String(Base64.getDecoder().decode("eyJtZXNzYWdlX2lkIjoiZDVhMGYzZDItNTE3ZC00NjM5LWFkYzYtZDhmN2JlZjg0ZWFlIn0=")));
    }
}
