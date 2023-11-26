package com.example.stockedfoodmanagement.stockedFood.web;

import com.example.stockedfoodmanagement.stockedFood.StockedFood;
import com.example.stockedfoodmanagement.stockedFood.StockedFoods;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * @author kiyota
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/near_expiration_foods")
public class NearExpirationFoodController {

	private final StockedFoods stockedFoods;

	@GetMapping
	public ResponseEntity<List<StockedFood>> getNearExpirationFoods(
			@RequestParam(name = "month", required = false, defaultValue = "1") int month) {
		LocalDate today = LocalDate.now();
		LocalDate expirationDate = today.plusMonths(month);

		List<StockedFood> nearExpirationFoods = this.stockedFoods.findAllByBestBeforeBetween(today, expirationDate);
		return ResponseEntity.ok(nearExpirationFoods);
	}

}
