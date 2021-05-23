package com.wzliulan.mall.cloud.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 设置扫描的包到基础包路径下，以便加载blog-common模块定义的公共组件
@SpringBootApplication(scanBasePackages = {"com.wzliulan.mall.cloud"})
public class MallGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallGoodsApplication.class, args);
    }

}
