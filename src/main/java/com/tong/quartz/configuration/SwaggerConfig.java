package com.tong.quartz.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@EnableOpenApi
@Configuration
public class SwaggerConfig {

    @Value("${spring.swagger-enable:true}")
    private boolean swaggerEnable;

    @Bean
    public Docket api() {
        return new Docket(SWAGGER_2)
                .groupName("quartz-api")
                .enable(swaggerEnable)
                .select()
                .apis(basePackage("com.tong.quartz"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("quartz-api")
                .version("1.0")
                .build();
    }
}