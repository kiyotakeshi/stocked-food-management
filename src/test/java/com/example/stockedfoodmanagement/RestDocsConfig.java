package com.example.stockedfoodmanagement;

import org.springframework.boot.test.autoconfigure.restdocs.RestDocsMockMvcConfigurationCustomizer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

/**
 * @author kiyota
 */
@TestConfiguration
public class RestDocsConfig {

	@Bean
	public RestDocsMockMvcConfigurationCustomizer customizer() {
		return configurer -> configurer.operationPreprocessors()
			.withRequestDefaults(prettyPrint())
			.withResponseDefaults(prettyPrint());
	}

}
