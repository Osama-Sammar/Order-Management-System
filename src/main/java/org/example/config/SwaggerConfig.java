//package org.example.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.bind.annotation.RequestMethod;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//@EnableSwagger2
//@Configuration
//public class SwaggerConfig {
//
//    public static final String AUTHORIZATION_HEADER = "Authorization";
//
//    private ApiKey apiKey(){
//        return new ApiKey("Authorization", AUTHORIZATION_HEADER, "header");
//    }
//
//    private ApiInfo apiInfo(){
//        return new ApiInfo(
//                "Spring Boot Blog REST APIs",
//                "Spring Boot Blog REST API Documentation",
//                "1",
//                "Terms of service",
//                new Contact("Your name", "www.profile.info", "name@gmail.com"),
//                "License of API",
//                "API license URL",
//                Collections.emptyList()
//        );
//    }
//
//    /**
//     * In order to create Swagger docket
//     * @return
//     */
//    @Bean
//    public Docket api(){
//        return new Docket(DocumentationType.OAS_30)
//                .apiInfo(apiInfo())
//                .securityContexts(Arrays.asList(securityContext()))
//                .securitySchemes(Arrays.asList(apiKey())) /*Enable authorization for APIs in Swagger UI*/
//                .select()
//                .apis(RequestHandlerSelectors.any())/*get all APIs in the project, you can use .basePackage to scan only APIs in specific package like (RequestHandlerSelectors.basePackage("com.edu.controller.customer"))*/
//                .paths(PathSelectors.any())  // Expose all APIs, you can restrict like expose only  .paths(PathSelectors.ant("/posts/*"))
//                .build();
//    }
//
//    private SecurityContext securityContext(){
//        return SecurityContext.builder().securityReferences(defaultAuth()).build();
//    }
//
//    private List<SecurityReference> defaultAuth(){
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        /*
//        * For example, scope could be read, write, and specific API in OAuth2
//         * */
//
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
//    }
//}

package org.example.config;

        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import springfox.documentation.builders.ApiInfoBuilder;
        import springfox.documentation.builders.PathSelectors;
        import springfox.documentation.builders.RequestHandlerSelectors;
        import springfox.documentation.service.ApiInfo;
        import springfox.documentation.service.ApiKey;
        import springfox.documentation.service.AuthorizationScope;
        import springfox.documentation.service.Contact;
        import springfox.documentation.service.SecurityReference;
        import springfox.documentation.spi.DocumentationType;
        import springfox.documentation.spi.service.contexts.SecurityContext;
        import springfox.documentation.spring.web.plugins.Docket;
        import springfox.documentation.swagger2.annotations.EnableSwagger2;

        import java.util.Arrays;
        import java.util.Collections;
        import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String DEFAULT_INCLUDE_PATTERN = "/.*";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Collections.singletonList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.example.controllers"))
                .paths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot Blog REST APIs")
                .description("Spring Boot Blog REST API Documentation")
                .version("1.0")
                .contact(new Contact("Your name", "www.profile.info", "name@gmail.com"))
                .license("License of API")
                .licenseUrl("API license URL")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
        return Collections.singletonList(
                new SecurityReference("JWT", authorizationScopes)
        );
    }
}
