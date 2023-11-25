package com.example.stockedfoodmanagement.stockedFood.web;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author kiyota
 */
public record UpdateStockedFood( //
		@NotEmpty String name, //
		BigDecimal price, //
		@NotNull LocalDate purchasedAt, //
		@NotNull LocalDate bestBefore, //
		@NotNull boolean useUp, //
		String memo) {
}
