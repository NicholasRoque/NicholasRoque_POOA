package com.world.beauty.CadastroCliente.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket swagger(){
        return new Docket(DocumentationType.SWAGGER_2)
        .useDefaultResponseMessages(false)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.world.beauty.CadastroCliente.controllers"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo());
    }

    private Contact contact(){
        return new Contact("Nicholas Gabriel dos Santos Roque",
            "https://github.com/NicholasRoque",
            null
        );
    }
    
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
            .title("Cliente API")
            .description("API criada para o projeto de POO do 3º Semestre de ADS da FATEC São José dos Campos - Prof. Jessen Vidal")
            .version("1.0")
            .contact(contact())
            .build();
    }

   
}
