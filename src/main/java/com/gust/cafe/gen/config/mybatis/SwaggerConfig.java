package com.gust.cafe.gen.config.mybatis;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableKnife4j
@EnableSwagger2WebMvc
public class SwaggerConfig {

    @Bean
    public Docket defaultGroup() {
        // Docket 是 Swagger 的配置类,如果有多个分组,则需要配置多个 Docket,每个 Docket 可以指定不同的扫描路径
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("默认分组")
                .select()
                // .apis(RequestHandlerSelectors.any()) // 扫描所有API
                .apis(RequestHandlerSelectors.basePackage("com.gust.cafe.gen.controller"))// 扫描指定包中的API
                .paths(PathSelectors.any())          // 选定所有路径
                .build()
                .apiInfo(apiInfo()) // 设置API文档信息
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .contact(new Contact("多罗罗丶", "https://stackoverflow.com/", "123456789@qq.com"))
                .version("1.0.0")
                .title("GEN-API文档")
                .description(StrUtil.format("GEN-API文档 @ {}", DateUtil.now()))
                .termsOfServiceUrl("https://stackoverflow.com/")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .extensions(null)
                .build();
    }
}
