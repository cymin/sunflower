package com.tsingyun.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Description: 项目启动初始化
 */
@Component
public class Init implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        WeChatTask.setToken_getTicket();
    }
}
