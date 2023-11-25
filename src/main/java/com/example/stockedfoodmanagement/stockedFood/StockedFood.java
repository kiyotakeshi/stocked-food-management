package com.example.stockedfoodmanagement.stockedFood;

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
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = { "id" })
public class StockedFood {

	@Id
	private UUID id;

	private String name;

	private BigDecimal price;

	private LocalDate purchasedAt;

	private LocalDate bestBefore;

	private boolean useUp;

	private String memo;

	@Override
	public String toString() {
		return this.id.toString();
	}

}
