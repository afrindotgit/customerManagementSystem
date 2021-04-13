package com.customermanagementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfiguration - Give2Hand Application implements a set of REST
 * endpoints to manage products we will create a Docket bean in a Spring Boot
 * configuration to configure Swagger 2 for the application. A Springfox Docket
 * instance provides the primary API configuration with sensible defaults and
 * convenience methods for configuration
 */

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     * we will create a Docket bean in a Spring Boot configuration to configure
     * Swagger 2 for the application
     *
     * @return docket object
     */

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build().apiInfo(metaData());
    }

    private ApiInfo metaData() {
        Contact contact = new Contact("Afrin", "https://github.com/afrindotgit/customerManagementSystem", "afrinafi88077@gmail.com");
        ApiInfo apiInfo = new ApiInfo(
                "Customer Management System",
                "Spring Boot REST API for Customer Management",
                "1.0",
                "Terms of service",
                contact.toString(),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }
}
