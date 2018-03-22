package com.tsingyun.controller;

import com.tsingyun.message.dispatcher.EventDispatcher;
import com.tsingyun.message.dispatcher.MsgDispatcher;
import com.tsingyun.model.MessageType;
import com.tsingyun.utils.MessageUtil;
import com.tsingyun.utils.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author cymin
 */
@Controller
@RequestMapping("/api/v1/")
public class WechatSecurity {
    private static Logger logger = LoggerFactory.getLogger(WechatSecurity.class);

    /**
     * @Description: 用于接收 get 参数，返回验证参数
     */
    @RequestMapping(value = "security", method = RequestMethod.GET)
    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "signature", required = true) String signature,
            @RequestParam(value = "timestamp", required = true) String timestamp,
            @RequestParam(value = "nonce", required = true) String nonce,
            @RequestParam(value = "echostr", required = true) String echostr) {
        try {
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                PrintWriter out = response.getWriter();
                out.print(echostr);
                out.close();
            } else {
                logger.info("这里存在非法请求！");
            }
        } catch (Exception e) {
            logger.error("error: ", e);
        }
    }

    /**
     * @Description: 接收微信端消息处理并做分发
     */
    @PostMapping("security")
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        try {
            Map<String, String> map = MessageUtil.parseXml(request);
            String msgtype = map.get("MsgType");
            if (MessageType.event.getValue().equals(msgtype)) {
                // 进入事件处理
                String msgrsp = EventDispatcher.processEvent(map);
                PrintWriter out = response.getWriter();
                out.print(msgrsp);
                out.flush();
                out.close();
            } else {
                // 进入文本消息处理
                String s = MsgDispatcher.processMessage(map);
                PrintWriter out = response.getWriter();
                out.print(s);
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            logger.error("error: ", e);
        }
    }
}