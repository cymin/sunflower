package com.tsingyun.server;

import com.alibaba.fastjson.JSONObject;
import com.tsingyun.model.GlobalProperty;
import com.tsingyun.model.WechatConstant;
import com.tsingyun.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: WeChatTask
 *
 * @author cymin
 * @Description: 两小时定时任务体
 */
@EnableScheduling
public class WeChatTask {

    private static final Logger logger = LoggerFactory.getLogger(WeChatTask.class);

    @Scheduled(cron = "0 */2 * * *")
    public static void setToken_getTicket() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        //获取token执行体
        params.put("grant_type", "client_credential");
        params.put("appid", WechatConstant.appID);
        params.put("secret", WechatConstant.appsecret);
        String jstoken = HttpUtils.sendGet(WechatConstant.tokenUrl, params);
        // 获取到token并赋值保存
//		String access_token = JSONObject.fromObject(jstoken).getString();
        String access_token = JSONObject.parseObject(jstoken).getString("access_token");
        GlobalProperty.accessToken = access_token;

        //获取jsticket的执行体
        params.clear();
        params.put("access_token", access_token);
        params.put("type", "jsapi");
        String jsticket = HttpUtils.sendGet(WechatConstant.ticketUrl, params);
        // 获取到js-SDK的ticket并赋值保存
        String jsapi_ticket = JSONObject.parseObject(jsticket).getString("ticket");
//		String jsapi_ticket = JSONObject.fromObject(jsticket).getString("ticket");
        GlobalProperty.jsapi_ticket = jsapi_ticket;

        logger.info("jsapi_ticket:" + jsapi_ticket);
        logger.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "更新了access_token为: " + access_token);
    }
}
