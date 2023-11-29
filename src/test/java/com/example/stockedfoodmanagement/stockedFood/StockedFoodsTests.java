package com.example.stockedfoodmanagement.stockedFood;

import com.example.stockedfoodmanagement.test.RepositoryIntegrationTest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

/**
 * @author kiyota
 */
@RepositoryIntegrationTest
@RequiredArgsConstructor
class StockedFoodsTests {

	final StockedFoods stockedFoods;

	@BeforeEach
	void setUp() {
		this.stockedFoods.saveAll(List.of(
				StockFoodTestUtils.create(UUID.randomUUID(), "カップラーメン", BigDecimal.valueOf(150),
						LocalDate.of(2023, 11, 25), LocalDate.of(2025, 12, 1), false, true, ""),
				StockFoodTestUtils.create(UUID.randomUUID(), "お米", BigDecimal.valueOf(5_000),
						LocalDate.of(2023, 11, 25), LocalDate.of(2024, 7, 1), false, false, "10kg"),
				StockFoodTestUtils.create(UUID.randomUUID(), "鯖缶", BigDecimal.valueOf(250), LocalDate.of(2023, 11, 25),
						LocalDate.of(2025, 9, 1), false, true, "ドラックストアで初めて見つけた")));
	}

	@AfterEach
	void after() {
		this.stockedFoods.deleteAll();
	}

	@Test
	void findAllByBestBeforeBetween() {
		assertThat(this.stockedFoods.findAllByBestBeforeBetween(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 6, 30)) //
			.size()).isEqualTo(0);

		assertThat(this.stockedFoods.findAllByBestBeforeBetween(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 7, 1)) //
			.size()).isEqualTo(1);

		assertThat(this.stockedFoods.findAllByBestBeforeBetween(LocalDate.of(2024, 6, 30), LocalDate.of(2025, 9, 1)) //
			.size()).isEqualTo(2);

		assertThat(this.stockedFoods.findAllByBestBeforeBetween(LocalDate.of(2024, 7, 1), LocalDate.of(2025, 9, 1)) //
			.size()).isEqualTo(2);
	}

}
