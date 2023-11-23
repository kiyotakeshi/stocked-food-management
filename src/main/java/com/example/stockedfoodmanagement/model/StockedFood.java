package com.example.stockedfoodmanagement.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author kiyota
 */
@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id"})
public class StockedFood {
    private String id;
    private String name;
    private BigDecimal price;
    private LocalDate purchasedAt;
    private LocalDate bestBefore;
    private boolean useUp;
    private String memo;
}
