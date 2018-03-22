package com.tsingyun.model;

/**
 * Created by chen on 17/11/27.
 */
public class WeChatParam {

    private String timestamp; // 时间戳
    private String nonce; // 随机数
    private String echostr; // 随机字符串
    private String signature; // 微信加密签名, 结合了开发者填写的token参数和请求中的timestamp参数、nonce参数

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getEchostr() {
        return echostr;
    }

    public void setEchostr(String echostr) {
        this.echostr = echostr;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "WeChatParam{" +
                "timestamp='" + timestamp + '\'' +
                ", nonce='" + nonce + '\'' +
                ", echostr='" + echostr + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}
