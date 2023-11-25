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

	@Setter(AccessLevel.PACKAGE)
	private String name;

	@Setter(AccessLevel.PACKAGE)
	private BigDecimal price;

	@Setter(AccessLevel.PACKAGE)
	private LocalDate purchasedAt;

	@Setter(AccessLevel.PACKAGE)
	private LocalDate bestBefore;

	@Setter(AccessLevel.PACKAGE)
	private boolean useUp;

	@Setter(AccessLevel.PACKAGE)
	private String memo;

	@Override
	public String toString() {
		return this.id.toString();
	}

}
