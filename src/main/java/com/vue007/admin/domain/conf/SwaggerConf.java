package com.vue007.admin.domain.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * Swagger API 配置
 *
 * @author Xiangyang
 * @date 2017年10月19日
 */
@Configuration
@EnableSwagger2
public class SwaggerConf extends WebMvcConfigurerAdapter {

    @Bean
    public Docket customDocket() {
        ApiInfo apiInfo = apiInfo();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zed.bbs.admin.controller"))
                .apis((input -> input != null && !"exception-controller".equalsIgnoreCase(input.groupName())))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class,
                        Date.class)
                .useDefaultResponseMessages(false);
        if (!Objects.isNull(apiInfo)) {
            docket.apiInfo(apiInfo);
        }
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("ZED 3D Admin API")
                .description("Zed 3D 社区运营后台 API")
                .version("1.0")
                .build();
    }
}
