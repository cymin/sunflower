package com.tsingyun.model;

/**
 * 消息类型
 * @author cymin
 */
public enum MessageType {
    /**
     * 消息类型：文本
     */
    text("text"),
    /**
     * 消息类型：图文
     */
    news("news"),
    /**
     * 消息类型：音乐
     */
    music("music"),
    /**
     * 消息类型：图片
     */
    image("image"),
    /**
     * 消息类型：链接
     */
    link("link"),
    /**
     * 消息类型：地理位置
     */
    location("location"),
    /**
     * 消息类型： 上报地理位置
     */
    LOCATION("LOCATION"),
    /**
     * 消息类型：音频
     */
    voice("voice"),
    /**
     * 消息类型：视频
     */
    video("video"),
    /**
     * 消息类型：推送
     */
    event("event"),
    /**
     * 事件类型：关注事件subscribe, (扫描二维码事件,用户未关注触发subscribe事件)
     */
    subscribe("subscribe"),
    /**
     * 事件类型：取消关注unsubscribe
     */
    unsubscribe("unsubscribe"),
    /**
     * 事件类型：CLICK(自定义菜单点击事件)
     */
    CLICK("CLICK"),
    /**
     * 事件类型：CLICK(自定义菜单,点击菜单跳转链接时的事件)
     */
    VIEW("VIEW"),
    /**
     * 扫描二维码事件 、用户已关注SCAN
     */
    SCAN("SCAN"),
    /**
     * 进入会话事件
     */
    ENTER("ENTER"),
    /**
     * 进入小程序widget事件
     */
    xa_widget_data("xa_widget_data");

    String value;

    MessageType(String val) {
        this.value = val;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
