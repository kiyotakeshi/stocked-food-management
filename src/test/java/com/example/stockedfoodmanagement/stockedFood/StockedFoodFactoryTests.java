package com.example.stockedfoodmanagement.stockedFood;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author kiyota
 */
class StockedFoodFactoryTests {

	@Test
	void testCreateStockedFoodWithNullPrice() {
		var command = new CreateStockedFood("パスタ", null, LocalDate.of(2023, 11, 25), LocalDate.of(2025, 3, 1), false,
				"1.5mm, 500g");
		StockedFood actual = StockedFoodFactory.create(command);

		assertThat(actual.getId()).isNotNull();
		assertThat(actual.getId()).isInstanceOf(UUID.class);
		assertThat(actual.getPrice()).isEqualTo(BigDecimal.ZERO);
	}

}
