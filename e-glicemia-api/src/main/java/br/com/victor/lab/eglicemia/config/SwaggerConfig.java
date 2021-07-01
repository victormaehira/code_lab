package br.com.victor.lab.eglicemia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
//@EnableWebMvc
//public class SwaggerConfig implements WebMvcConfigurer {
public class SwaggerConfig{
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "e-Glicemia backend API",
                "e-Glicemia api by Victor Maehira",
                "v0.5.0",
                "Terms of service",
                new Contact("Victor", null,  null),
                "License of API",
                "https://www.victormaehira.com.br",
                Collections.emptyList());
    }

    /*
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // Allow anyone and anything access. Probably ok for Swagger spec
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        source.registerCorsConfiguration("/v2/api-docs", config);
        return new CorsFilter(source);
    }*/

    /*
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        System.out.println("*** corsConfigurer called");
        return new WebMvcConfigurerAdapter() {
            @Override public void addCorsMappings(CorsRegistry registry) {
                System.out.println("*** addCorsMappings called");
                registry.addMapping("/v2/api-docs");
            }
        };
    }*/

    /*
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");

    }*/
}

