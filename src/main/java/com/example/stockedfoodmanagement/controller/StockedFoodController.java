package com.example.stockedfoodmanagement.controller;

import com.example.stockedfoodmanagement.StockedFoods;
import com.example.stockedfoodmanagement.model.StockedFood;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author kiyota
 */
@RestController
@RequiredArgsConstructor
public class StockedFoodController {

	private final StockedFoods stockedFoods;

	@GetMapping("/stocked_foods")
	public ResponseEntity<List<StockedFood>> getStockedFoods() {
		return ResponseEntity.ok(this.stockedFoods.findAll());
	}

}
