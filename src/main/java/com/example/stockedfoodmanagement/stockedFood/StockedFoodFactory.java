package com.example.stockedfoodmanagement.stockedFood;

import com.example.stockedfoodmanagement.stockedFood.web.CreateStockedFood;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author kiyota
 */
@UtilityClass
public final class StockedFoodFactory {

	public static StockedFood create(CreateStockedFood command) {
		return new StockedFood(UUID.randomUUID(), command.name(),
				(command.price() == null) ? BigDecimal.ZERO : command.price(), command.purchasedAt(),
				command.bestBefore(), command.useUp(), command.memo());
	}

	// if you don't want to use @UtilityClass
	// // @see
	// https://www.baeldung.com/java-sonar-hide-implicit-constructor#1-default-private-constructor
	// private StockedFoodFactory() {
	// throw new UnsupportedOperationException("This is a utility class and cannot be
	// instantiated"); //
	// }

}
