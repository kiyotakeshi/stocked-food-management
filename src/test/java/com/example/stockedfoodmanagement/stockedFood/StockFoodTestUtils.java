package com.example.stockedfoodmanagement.stockedFood;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * @author kiyota
 */
@UtilityClass
public class StockFoodTestUtils {

	// DB にテストデータを用意する際に ID が必要なため all args constructor を呼び出す
	public static StockedFood create(UUID id, String name, BigDecimal price, LocalDate purchasedAt,
			LocalDate bestBefore, boolean consumptionFlag, boolean useUp, String memo) {
		return new StockedFood(id, name, price, purchasedAt, bestBefore, consumptionFlag, useUp, memo);
	}

}
