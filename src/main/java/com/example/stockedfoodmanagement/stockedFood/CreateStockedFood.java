package com.example.stockedfoodmanagement.stockedFood;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Web からの入力に対応するクラスのため immutable である record を採用
 *
 * @author kiyota
 */
public record CreateStockedFood( //
		@NotEmpty String name, //
		BigDecimal price, //
		@NotNull LocalDate purchasedAt, //
		@NotNull LocalDate bestBefore, //
		@NotNull boolean useUp, //
		String memo) {
}
