package com.example.stockedfoodmanagement.consumptionRecord.web;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author kiyota
 */
public record CreateConsumptionRecord( //
		@NotNull UUID stockedFoodId, //
		@NotNull LocalDateTime consumedAt, //

		// 使い切ったか
		@NotNull boolean useUp) {
}
