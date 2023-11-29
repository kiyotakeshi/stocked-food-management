package com.example.stockedfoodmanagement.consumptionRecord;

import com.example.stockedfoodmanagement.stockedFood.StockedFood;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author kiyota
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = { "id" })
public class ConsumptionRecord {

	@Id
	private UUID id;

	@ManyToOne
	private StockedFood stockedFood;

	private LocalDateTime consumedAt;

	public static ConsumptionRecord create(StockedFood stockedFood, LocalDateTime consumedAt) {
		return new ConsumptionRecord(UUID.randomUUID(), stockedFood, consumedAt);
	}

}
