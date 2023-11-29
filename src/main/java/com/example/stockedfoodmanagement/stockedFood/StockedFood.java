package com.example.stockedfoodmanagement.stockedFood;

import com.example.stockedfoodmanagement.stockedFood.web.CreateStockedFood;
import com.example.stockedfoodmanagement.stockedFood.web.UpdateStockedFood;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * @author kiyota
 */
@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = { "id" })
@ToString
public class StockedFood {

	@Id
	@Setter(AccessLevel.NONE)
	private UUID id;

	private String name;

	private BigDecimal price;

	private LocalDate purchasedAt;

	private LocalDate bestBefore;

	// 使い切ったか
	private boolean consumptionFlag;

	// 一回使い切りか
	private boolean useUp;

	private String memo;

	public static StockedFood create(CreateStockedFood command) {
		return new StockedFood(UUID.randomUUID(), command.name(),
				(command.price() == null) ? BigDecimal.ZERO : command.price(), command.purchasedAt(),
				command.bestBefore(), false, command.useUp(), command.memo());
	}

	public static StockedFood update(StockedFood stockedFood, UpdateStockedFood command) {
		stockedFood.setName(command.name());
		stockedFood.setPrice(command.price());
		stockedFood.setPurchasedAt(command.purchasedAt());
		stockedFood.setBestBefore(command.bestBefore());
		stockedFood.setUseUp(command.useUp());
		stockedFood.setMemo(command.memo());
		// System.out.println(stockedFood);
		return stockedFood;
	}

	public static StockedFood finish(StockedFood stockedFood) {
		stockedFood.consumptionFlag = true;
		return stockedFood;
	}

}
