package com.wzliulan.mall.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 设置扫描的包到基础包路径下，以便加载blog-common模块定义的公共组件
@SpringBootApplication(scanBasePackages = {"com.wzliulan.mall.cloud"})
//@SpringBootApplication
public class MallArticleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallArticleApplication.class, args);
    }

}
