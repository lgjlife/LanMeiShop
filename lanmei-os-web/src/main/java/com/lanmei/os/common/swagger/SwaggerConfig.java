package com.lanmei.os.common.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: com-lanmei-parent
 * @description: SwaggerConfig
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-09-06 22:38
 **/
@EnableWebMvc
@EnableSwagger2                // Swagger的开关，表示已经启用Swagger
@Configuration                 // 声明当前配置类
@ComponentScan(basePackages = {"com.lanmei.os.controller"})
public class SwaggerConfig {

    @Bean
    public Docket customDocket() {
        //
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());
    }

    /*private ApiInfo apiInfo() {
        Contact contact = new Contact("老王", "https://www.baidu.me", "baidu_666@icloud.com");
        return new ApiInfo("Blog前台API接口",//大标题 title
                "Swagger测试demo",//小标题
                "0.0.1",//版本
                "www.baidu.com",//termsOfServiceUrl
                "",//作者
                "Blog",//链接显示文字
                "https://www.baidu.me"//网站链接
        );
    }*/

  //  @Value("${swaggertitle}")
    private String  title = "lanmei";
 //   @Value("${swaggerversion}")
    private String  version  = "v2.0";
//    @Value("${swaggercontactName}")
    private String  contactName  = "lgj";
 //   @Value("${swaggercontactUrl}")
    private String  contactUrl  = "https://blog.csdn.net/u011676300";
 //   @Value("${swaggerdescription}")
    private String  description   = "lanmei";

 //   @Value("${swaggerbasePackage}")
    private String  basePackage  = "com.lanmei.os.controller";


   /* @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }*/
    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title(title)
                //创建人
                .contact(new Contact(contactName, contactUrl, ""))
                //版本号
                .version(version)
                //描述
                .description(description)
                .build()
                ;
    }

}

