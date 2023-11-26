package com.example.stockedfoodmanagement.test;

import jakarta.transaction.Transactional;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestConstructor;

import java.lang.annotation.*;

/**
 * @author kiyota
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootTest(properties = { //
		"spring.flyway.enabled=false", //
		"spring.jpa.properties.hibernate.format_sql=true", //
		"spring.jpa.properties.hibernate.show_sql=true" })
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriPort = 8888)
@AutoConfigureTestDatabase
@Transactional
@Import(RestDocsConfig.class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public @interface ControllerIntegrationTest {

}
