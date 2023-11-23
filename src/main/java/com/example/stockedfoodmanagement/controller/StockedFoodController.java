package com.example.stockedfoodmanagement.controller;

import com.example.stockedfoodmanagement.model.StockedFood;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author kiyota
 */
@RestController
public class StockedFoodController {

    private static final String CUP_RAMEN_UUID = "47e3f700-188a-4570-9030-963960484141";
    private static final String RICE_UUID = "736b16a2-199b-4369-8377-809663482772";
    private static final String CANNED_MACKEREL_UUID = "d52f252f-201c-4798-8929-261197663547";

    private final List<StockedFood> stockedFoods = List.of(
            new StockedFood(
                    CUP_RAMEN_UUID,
                    "カップラーメン",
                    BigDecimal.valueOf(150),
                    LocalDate.of(2023, 12, 23),
                    LocalDate.of(2025, 12, 1),
                    true,
                    ""),
            new StockedFood(
                    RICE_UUID,
                    "お米",
                    BigDecimal.valueOf(5_000),
                    LocalDate.of(2023, 12, 13),
                    LocalDate.of(2024, 7, 1),
                    false,
                    "10kg"),
            new StockedFood(
                    CANNED_MACKEREL_UUID,
                    "鯖缶",
                    BigDecimal.valueOf(250),
                    LocalDate.of(2023, 12, 20),
                    LocalDate.of(2025, 9, 1),
                    false,
                    "ドラックストアで初めて見つけた")
    );

    @GetMapping("/stocked_foods")
    public ResponseEntity<List<StockedFood>> getStockedFoods() {
        return ResponseEntity.ok(stockedFoods);
    }
}
