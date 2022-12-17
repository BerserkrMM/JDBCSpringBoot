package com.example.jdbcspringbootapp.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

import static springfox.documentation.builders.PathSelectors.regex;

@Profile("dev")
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	//students
	@Bean
	public Docket cardsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(cardsAPIInfo())
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();//.securitySchemes(Arrays.asList(basicAuth()));
	}

	private Predicate<String> cardsPaths() {
		return Predicates.or(
				regex("/api/card.*"),
				regex("/api/currency.*"));
	}

	private ApiInfo cardsAPIInfo() {
		return new ApiInfoBuilder().title("Cards API")
				.description("The super heroic Cards API ")
				.license("Apache License Version 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
				.version("1.0").build();
	}

	//private SecurityScheme basicAuth() {return new BasicAuth("Basic Authentication");}
}
