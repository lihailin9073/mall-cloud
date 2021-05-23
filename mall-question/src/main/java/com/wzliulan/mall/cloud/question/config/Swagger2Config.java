package com.wzliulan.mall.cloud.question.config;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger配置文件
 */
@EnableSwagger2Doc
//@EnableSwagger2
@Configuration
public class Swagger2Config {
    @Bean
    Docket docket1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("开放API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wzliulan.mall.cloud.question.controller.open"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.getApiInfo1());
    }
    private ApiInfo getApiInfo1() {
        return new ApiInfoBuilder()
                .title("[问答微服务]接口文档")
                .version("2021.0.1")
                .description("-问题、回复等服务接口")
                .contact(new Contact("浏览电子商务有限公司","http://www.wzliulan.com/contact","767679879@qq.com"))
                .build();
    }

    @Bean
    Docket docket2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Feign-API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wzliulan.mall.cloud.question.controller.feign"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.getApiInfo2());
    }
    private ApiInfo getApiInfo2() {
        return new ApiInfoBuilder()
                .title("[问答微服务/Feign-API]接口文档")
                .version("2021.0.1")
                .description("-问题、回复等服务接口")
                .contact(new Contact("浏览电子商务有限公司","http://www.wzliulan.com/contact","767679879@qq.com"))
                .build();
    }
}
