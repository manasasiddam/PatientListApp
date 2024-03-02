package com.Methods.Configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class SwaggerConfig {
@Bean

public GroupedOpenApi controllerApi() {
	return GroupedOpenApi.builder()
			.group("patient-api")
			.packagesToScan("com.Methods.controller")
			.build();

    }
}