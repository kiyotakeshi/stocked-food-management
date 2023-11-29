package com.example.stockedfoodmanagement.stockedFood;

import com.example.stockedfoodmanagement.stockedFood.web.CreateStockedFood;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author kiyota
 */
class StockedFoodTests {

	@Test
	void priceがnullの場合0で初期化する() {
		var command = new CreateStockedFood("パスタ", null, LocalDate.of(2023, 11, 25), LocalDate.of(2025, 3, 1), false,
				"1.5mm, 500g");
		StockedFood actual = StockedFood.create(command);

		assertThat(actual.getId()).isNotNull();
		assertThat(actual.getId()).isInstanceOf(UUID.class);
		assertThat(actual.getPrice()).isEqualTo(BigDecimal.ZERO);
	}

	@Test
	void consumptionFlagはfalseで初期化する() {
		var command = new CreateStockedFood("パスタ", null, LocalDate.of(2023, 11, 25), LocalDate.of(2025, 3, 1), false,
				"1.5mm, 500g");
		StockedFood actual = StockedFood.create(command);
		assertThat(actual.isConsumptionFlag()).isFalse();
	}

}
