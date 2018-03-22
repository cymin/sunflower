package com.tsingyun.message.dispatcher;

import com.tsingyun.message.resp.*;
import com.tsingyun.model.MessageType;
import com.tsingyun.utils.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
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
                String content = map.get("Content");
                logger.debug("==============这是文本消息, 收到消息：{}", content);
                if (content.equals(UNSUPPORTED_MESSAGE)) {
                    // 对于收藏的表情包，不作回复，回复""或success，注意仅单词或空字符，不需要封装成xml消息
                    return "";
                } else {
                    // 获取接收到的消息内容，用户发送什么内容，就回复给用户什么
                    String textMessage = MessageUtil.createTextMessage(openid, mpid, content);
                    return textMessage;
                }
            case image:
                // 图片消息
                logger.debug("==============这是图片消息！");
                NewsMessage newsMessage = new NewsMessage();
                newsMessage.setToUserName(openid);
                newsMessage.setFromUserName(mpid);
                newsMessage.setCreateTime(System.currentTimeMillis());
                newsMessage.setMsgType(MessageType.news.getValue());

                List<Article> list = new ArrayList<>();
                Article article = new Article();
                article.setDescription("Sunflower 1"); //图文消息的描述
                article.setPicUrl("http://img.aiyidu.com/forum/201508/13/093646akn2stmkdz4krsrs.jpg"); //图文消息图片地址
                article.setTitle("Sunflower 1 \nI will keep on windowsill a sunflower, because it is like sunshine to my warm.");  //图文消息标题
                article.setUrl("http://dimg09.c-ctrip.com/images/tg/618/622/083/5c3c79c3702a4019a6eaf3892eed6637.jpg");  //图文 url 链接
                list.add(article);

                // 如果需要发送多图文则在这里 list 中加入多个 Article 即可
                article = new Article();
                article.setDescription("Sunflower 2"); //图文消息的描述
                article.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1521716985326&di=9ce2215e9157016c042923d827aa6221&imgtype=0&src=http%3A%2F%2Fwww.diypin.com%2Fdo%2FResource%2FS%2F61.jpg"); //图文消息图片地址
                article.setTitle("Sunflower 2");  //图文消息标题
                article.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1522311768&di=c48e1944ae030a3c7dd67286b0239332&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.020bom.com%2Fimages%2FTB1KJ0EJpXXXXbYXpXXXXXXXXXX_%21%210-item_pic.jpg_400x400.jpg");  //图文 url 链接
                list.add(article);

                newsMessage.setArticleCount(list.size());
                newsMessage.setArticles(list);
                return MessageUtil.newsMessageToXml(newsMessage);
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