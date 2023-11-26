package com.example.stockedfoodmanagement.test;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestConstructor;

import java.lang.annotation.*;

/**
 * @author kiyota
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@DataJpaTest
@DirtiesContext
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public @interface RepositoryIntegrationTest {

	@AliasFor(annotation = DataJpaTest.class, attribute = "showSql")
	boolean showSql() default true;

}
