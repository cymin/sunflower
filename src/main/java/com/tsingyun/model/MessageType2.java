package com.tsingyun.model;

/**
 * 消息类型
 * @author cymin
 */
public enum MessageType2 {
    /**
     * 返回消息类型：文本
     */
    RESP_MESSAGE_TYPE_TEXT("text"),
    /**
     * 返回消息类型：图文
     */
    RESP_MESSAGE_TYPE_NEWS("news"),
    /**
     * 返回消息类型：音乐
     */
    RESP_MESSAGE_TYPE_MUSIC("music"),
    /**
     * 请求消息类型：文本
     */
    REQ_MESSAGE_TYPE_TEXT("text"),
    /**
     * 请求消息类型：图片
     */
    REQ_MESSAGE_TYPE_IMAGE("image"),
    /**
     * 请求消息类型：链接
     */
    REQ_MESSAGE_TYPE_LINK("link"),
    /**
     * 请求消息类型：地理位置
     */
    REQ_MESSAGE_TYPE_LOCATION("location"),
    /**
     * 请求消息类型：音频
     */
    REQ_MESSAGE_TYPE_VOICE("voice"),
    /**
     * 请求消息类型：视频
     */
    REQ_MESSAGE_TYPE_VIDEO("video"),
    /**
     * 请求消息类型：推送
     */
    REQ_MESSAGE_TYPE_EVENT("event"),
    /**
     * 事件类型：关注事件subscribe, (扫描二维码事件,用户未关注触发subscribe事件)
     */
    EVENT_TYPE_SUBSCRIBE("subscribe"),
    /**
     * 事件类型：取消关注unsubscribe
     */
    EVENT_TYPE_UNSUBSCRIBE("unsubscribe"),
    /**
     * 事件类型：CLICK(自定义菜单点击事件)
     */
    EVENT_TYPE_CLICK("CLICK"),
    /**
     * 事件类型：CLICK(自定义菜单,点击菜单跳转链接时的事件)
     */
    EVENT_TYPE_VIEW("VIEW"),
    /**
     * 扫描二维码事件 、用户已关注SCAN
     */
    EVENT_TYPE_SCAN("SCAN"),
    /**
     * 上报地理位置事件
     */
    EVENT_TYPE_LOCATION("LOCATION"),
    /**
     * 进入会话事件
     */
    EVENT_TYPE_ENTER("ENTER"),
    /**
     * 进入小程序widget事件
     */
    EVENT_TYPE_WXA_WIDGET_DATA("xa_widget_data");

    String value;

    MessageType2(String val) {
        this.value = val;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
