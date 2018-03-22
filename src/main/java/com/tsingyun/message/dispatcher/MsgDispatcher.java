package com.tsingyun.message.dispatcher;

import com.tsingyun.message.resp.TextMessage;
import com.tsingyun.model.MessageType;
import com.tsingyun.utils.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author chen
 * @Description: 消息业务处理分发器
 * @date 2018-3-22
 */
public class MsgDispatcher {
    public static final String UNSUPPORTED_MESSAGE = "【收到不支持的消息类型，暂无法显示】";
    private static Logger logger = LoggerFactory.getLogger(MsgDispatcher.class);

    public static String processMessage(Map<String, String> map) throws UnsupportedEncodingException {
        // 发送方帐号（用户OpenId）
        String openid = map.get("FromUserName");
        // 开发者微信号（公众号原始ID）
        String mpid = map.get("ToUserName");
        final String msgType = map.get("MsgType");

        MessageType messageType = null;
        try {
            messageType = MessageType.valueOf(msgType);
        } catch (Exception e) {
            logger.error("文本消息处理---> 无法识别消息类型：{}, 请增加该类型", msgType);
            e.printStackTrace();
        }
        if (messageType == null) {
            String textMessage = MessageUtil.createTextMessage(openid, mpid, "服务器异常，请稍后重试");
            return textMessage;
        }

        switch (messageType) {
            case text:
                // 文本消息
                logger.info("==============这是文本消息！");
                break;
            case image:
                // 图片消息
                logger.info("==============这是图片消息！");
                break;
            case link:
                logger.info("==============这是链接消息！");
                break;
            case location:
                logger.info("==============这是位置消息！");
                break;
            case video:
                logger.info("==============这是视频消息！");
                break;
            case voice:
                logger.info("==============这是语音消息！");
                break;
            case ENTER:
                logger.info("==============这是进入会话！");
                break;
            case xa_widget_data:
                logger.info("==============这是小程序widget事件！");
                break;
            default:
                logger.info("其他文本消息类型：{}", msgType);
        }
        return null;
    }
}