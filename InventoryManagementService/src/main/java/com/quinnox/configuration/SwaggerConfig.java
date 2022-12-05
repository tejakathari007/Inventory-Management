package com.quinnox.configuration;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private final ServletContext servletContext;

	@Autowired
	public SwaggerConfig(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Bean
	public Docket customImplementation() {
		return new Docket(DocumentationType.SWAGGER_2).pathProvider(new RelativePathProvider(servletContext) {
			@Override
			public String getApplicationBasePath() {
				return "/inventory/v1";
			}
		}).select().apis(RequestHandlerSelectors.basePackage("com.quinnox")).build().apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {

		return new ApiInfoBuilder().title("Inventory Management").description("CRUD for Inventory Management")
				.license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
				.termsOfServiceUrl("").version("1.0").contact(new Contact("Teja Kathari", "", "tejak@quinnox.com"))
				.build();
	}
}
