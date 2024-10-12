package com.rabbiter.market;

import com.rabbiter.market.common.util.PathUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SupermarketApplication {

    @Value("${server.port}")
    private Long serverPort;

    public static void main(String[] args) {
        System.out.println("Project Path: " + PathUtils.getClassLoadRootPath());
        // 打印端口
        ConfigurableApplicationContext context = SpringApplication.run(SupermarketApplication.class, args);
        SupermarketApplication application = context.getBean(SupermarketApplication.class);
        // 打印端口
        System.out.println("http://localhost:" + application.serverPort);
    }

}
