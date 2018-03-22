package com.tsingyun.message.dispatcher;

import com.tsingyun.message.resp.TextMessage;
import com.tsingyun.model.MessageType;
import com.tsingyun.utils.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * ClassName: EventDispatcher
 *
 * @author cymin
 * @Description: 事件消息业务分发器
 */
public class EventDispatcher {
    private static Logger logger = LoggerFactory.getLogger(EventDispatcher.class);

    public static String processEvent(Map<String, String> map) {
        //用户 openid
        String openid = map.get("FromUserName");
        //公众号原始 ID
        String mpid = map.get("ToUserName");

        String eventType = map.get("Event");
        MessageType messageType = null;
        try {
            messageType = MessageType.valueOf(eventType);
        } catch (Exception e) {
            logger.error("事件消息处理---> 无法识别消息类型：{}, 请增加该类型", messageType);
            e.printStackTrace();
        }
        if (messageType == null) {
            String textMessage = MessageUtil.createTextMessage(openid, mpid, "服务器异常，请稍后重试");
            return textMessage;
        }
        switch (messageType) {
            case text:
                // 文本消息
                logger.info("EVENT==============这是文本消息！");
                break;
            case image:
                // 图片消息
                logger.info("EVENT==============这是图片消息！");
                break;
            case link:
                // 链接消息
                logger.info("EVENT==============这是链接消息！");
                break;
            case location:
                // 地理位置
                logger.info("EVENT==============这是地理位置消息！");
                break;
            case video:
                // 视频消息
                logger.info("EVENT==============这是视频消息！");
                break;
            case voice:
                // 语音消息
                logger.info("EVENT==============这是语音消息！");
                break;
            case subscribe:
                // 添加关注
                logger.info("EVENT==============这是关注！");
                break;
            case unsubscribe:
                // 取消关注
                logger.info("EVENT==============这是取消关注！");
                break;
            case ENTER:
                // 进入会话
                logger.info("EVENT==============这是进入会话！");
                break;
            case xa_widget_data:
                //小程序widget事件
                logger.info("EVENT==============这是小程序widget事件！");
                break;
            default:
                logger.info("其他事件类型:", eventType);
        }
        return null;
    }
}