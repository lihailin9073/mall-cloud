package com.wzliulan.mall.cloud.question;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients({"com.wzliulan.mall.cloud.feign"})
@SpringBootApplication
public class MallQuestionApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallQuestionApplication.class, args);
    }

}
