package com.example.stockedfoodmanagement.stockedFood.web;

import com.example.stockedfoodmanagement.stockedFood.ResourceNotFoundException;
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
			.orElseThrow(ResourceNotFoundException::new);
	}

	@PostMapping
	public ResponseEntity<StockedFood> createStockedFood(@RequestBody @Valid CreateStockedFood command) {
		var createdStockedFood = this.stockedFoods.save(StockedFood.create(command));
		return ResponseEntity //
			.created(URI.create("/stocked_foods/" + createdStockedFood.getId())) //
			.body(createdStockedFood);
	}

	@PutMapping("/{id}")
	public ResponseEntity<StockedFood> updateStockedFood(@PathVariable UUID id,
			@RequestBody @Valid UpdateStockedFood command) {
		var stockedFood = this.stockedFoods.findById(id).orElseThrow(ResourceNotFoundException::new);
		var updateStockedFood = StockedFood.update(stockedFood, command);
		return ResponseEntity.ok(this.stockedFoods.save(updateStockedFood));
	}

	// TODO: 備蓄食の消費

	// TODO: 履歴(別の controller かな)

}
