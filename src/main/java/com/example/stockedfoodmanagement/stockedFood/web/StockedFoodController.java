package com.example.stockedfoodmanagement.stockedFood.web;

import com.example.stockedfoodmanagement.stockedFood.StockedFoodFactory;
import com.example.stockedfoodmanagement.stockedFood.StockedFoods;
import com.example.stockedfoodmanagement.stockedFood.StockedFood;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

/**
 * @author kiyota
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/stocked_foods")
public class StockedFoodController {

	private final StockedFoods stockedFoods;

	@GetMapping
	public ResponseEntity<List<StockedFood>> getStockedFoods() {
		return ResponseEntity.ok(this.stockedFoods.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<StockedFood> getStockedFood(@PathVariable UUID id) {
		return this.stockedFoods.findById(id) //
			.map(ResponseEntity::ok) //
			.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<StockedFood> createStockedFood(@RequestBody @Valid CreateStockedFood command) {
		StockedFood stockedFood = this.stockedFoods.save(StockedFoodFactory.create(command));
		return ResponseEntity //
			.created(URI.create("/stocked_foods/" + stockedFood.getId())) //
			.body(stockedFood);
	}

}
