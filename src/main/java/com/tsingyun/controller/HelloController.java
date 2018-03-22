package com.tsingyun.controller;

import com.tsingyun.model.WeChatParam;
import com.tsingyun.utils.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by chen on 17/11/27.
 */
@RestController
@RequestMapping("/api/v1/")
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);


    @GetMapping({"/", "index"})
    public String hello(String name, WeChatParam param) {
        logger.info(">>> " + name);
        logger.info(">>> " + param.toString());
        return param.getEchostr();
    }

    //  验证微信服务器
   /* @PostMapping(value = "/wechat")
    public String wechatService(String body) {
        *//*if (CheckUtil.checkSignature(param.getSignature(), param.getTimestamp(), param.getNonce())) {

        }*//*
        logger.info(body);
        return body;
    }*/

    @PostMapping(value = "hello")
    public void wechatService(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, String> map = MessageUtil.parseXml(request);
            logger.info(map.get("Content"));
        } catch (Exception e) {
            logger.error("接受消息出错", e);
        }
    }
}
