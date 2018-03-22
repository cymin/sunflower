package com.tsingyun.model;

/**
 * Created by chen on 17/11/29.
 */
public class  WechatConstant {
    private WechatConstant() {

    }
    public static final String token = "1qaz@WSX";
    public static final String appID = "wxc113aace52dcb69b";
    public static final String appsecret = "ac779b3302e1f09d6bd4b7600b68734d";

    public static final String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token";
    public static final String ticketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";

    // 普通消息模版
    private String CUSTOM_MESSAGE_TEMPLATE = "{\"touser\":\"%1$s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%2$s\"}}";

    // 欢迎消息模版
    private String WELCOME_MESSAGE_TEMPLATE = "{ \"touser\": \"%1$s\",\"msgtype\": \"news\", \"news\": { \"articles\":[ { \"title\": \"欢迎" +
            "关注XXX~点击查看XXX介绍！\", \"description\": \"欢迎关注XXX~点击查看XXX介绍！\", \"url\": \"http: //www.baidu.com\",\"picurl\": \"http: //www.baidu.com/icon.png\"}]}}";

    // 获取 OpenID api
    private final String getOpenIdUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";

    // 获取 用户基础信息 api
    private final String getUserInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info";

    // 获取 用户详细信息 api
    private final String getFullUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?lang=zh_CN";

    // 发送客服消息给用户
    private final String sendMessage = "https://api.weixin.qq.com/cgi-bin/message/custom/send";


}
