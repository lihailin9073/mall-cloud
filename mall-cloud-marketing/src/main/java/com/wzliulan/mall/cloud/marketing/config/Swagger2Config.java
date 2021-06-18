package com.wzliulan.mall.cloud.marketing.config;

import com.spring4all.swagger.EnableSwagger2Doc;
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
                .groupName("开放接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wzliulan.mall.cloud.marketing.controller.open"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.getApiInfo1());
    }
    private ApiInfo getApiInfo1() {
        return new ApiInfoBuilder()
                .title("[营销工具微服务]接口文档")
                .version("2021.0.1")
                .description("营销工具微服务-品类、品牌、营销工具、属性等服务接口")
                .contact(new Contact("浏览电子商务有限公司","http://www.wzliulan.com/contact","767679879@qq.com"))
                .build();
    }

    @Bean
    Docket docket2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Feign接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wzliulan.mall.cloud.marketing.controller.feign"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.getApiInfo2());
    }
    private ApiInfo getApiInfo2() {
        return new ApiInfoBuilder()
                .title("[系统微服务/Feign-API]接口文档")
                .version("2021.0.1")
                .description("营销工具微服务-品类、品牌、营销工具、属性等服务接口")
                .contact(new Contact("浏览电子商务有限公司","http://www.wzliulan.com/contact","767679879@qq.com"))
                .build();
    }

    @Bean
    Docket docket3() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("管理接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wzliulan.mall.cloud.marketing.controller.security"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.getApiInfo3());
    }
    private ApiInfo getApiInfo3() {
        return new ApiInfoBuilder()
                .title("[管理类]接口文档")
                .version("2021.0.1")
                .description("营销工具微服务-品类、品牌、营销工具、属性等服务接口")
                .contact(new Contact("浏览电子商务有限公司","http://www.wzliulan.com/contact","767679879@qq.com"))
                .build();
    }
}
