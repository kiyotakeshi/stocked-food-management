package com.example.stockedfoodmanagement.stockedFood;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * サンプルで一つ書いてみたが `jakarta.validation` の挙動をテストしていることになるので、 独自に validator
 * を定義した場合などのみ記載して、このタイプのテストは基本的には書かない
 *
 * @author kiyota
 */
class CreateStockedFoodTests {

	@Test
	void バリデーションエラーが発生する場合() {
		CreateStockedFood command = new CreateStockedFood("", null, null, null, false, null);

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<CreateStockedFood>> violations = validator.validate(command);

		violations.forEach(v -> {
			System.out.println(v.getPropertyPath() + " " + v.getMessage());
			assertThat(v.getMessage()).isNotEmpty();
		});
	}

}
