package com.example.demo.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title:
 * @Package
 * @Description:
 * @Author gyf
 * @Date 2020/5/29 15:47
 */
@Configuration
//@EnableSwagger2只需要在配置类上添加即可 不需要在启动类上也添加
//重复添加 此处配置@conditionProperty置为失效 但不会影响到DemoApplication上的Swagger生效
@EnableSwagger2
//@Profile("uat")
//生产环境不显示swagger
/**
 * 1） 使用注解 @ConditionalOnProperty swagger.enable 值与 havingValue 值匹配 则满足条件
 * 2） 使用注解@Value     @Value("${swagger.enable}")
 *     private boolean enable = false;
 * 3) 使用@Profile("uat") 指定只有UAT环境才生效
 */
@ConditionalOnProperty(prefix = "swagger",value = "enable",havingValue = "true")
public class Swagger2 {
//    @Value("${swagger.enable}")
//    private boolean enable = false;
    @Bean
    public Docket createRestApi() {
        System.out.println("=============SWAGGER注册中=============");
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("token").description("令牌")
                .modelRef(new ModelRef("string")).parameterType("query").required(false).build();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
//                .enable(enable)
                .apiInfo(apiInfo())
                .select()
                //根据类注解扫描
//                .apis(RequestHandlerSelectors.withClassAnnotation(ApiOperation.class))
                //根据方法头注解扫描
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //根据controller包位置扫描
//                .apis(RequestHandlerSelectors.basePackage("com.test.manager.controller"))
//                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("研发工具服务API文档详情")
                .description("swagger-bootstrap-ui")
                .termsOfServiceUrl("")
                .contact(new Contact("developer", "", "915320256@qq.com"))
                .version("1.0")
                .build();
    }

}
