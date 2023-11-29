package com.example.stockedfoodmanagement.consumptionRecord.web;

import com.example.stockedfoodmanagement.consumptionRecord.ConsumptionRecord;
import com.example.stockedfoodmanagement.consumptionRecord.ConsumptionRecords;
import com.example.stockedfoodmanagement.stockedFood.ResourceNotFoundException;
import com.example.stockedfoodmanagement.stockedFood.StockedFood;
import com.example.stockedfoodmanagement.stockedFood.StockedFoods;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author kiyota
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/consumption_records")
public class ConsumptionRecordController {

	private final StockedFoods stockedFoods;

	private final ConsumptionRecords consumptionRecords;

	@PostMapping
	public ResponseEntity<String> createConsumptionRecord(@RequestBody @Valid CreateConsumptionRecord command) {
		var stockedFood = this.stockedFoods.findById(command.stockedFoodId())
			.orElseThrow(ResourceNotFoundException::new);
		if (stockedFood.isConsumptionFlag()) {
			return ResponseEntity.ok("already the food is consumed");
		}
		this.consumptionRecords.save(ConsumptionRecord.create(stockedFood, command.consumedAt()));
		if (command.useUp()) {
			this.stockedFoods.save(StockedFood.finish(stockedFood));
		}
		return ResponseEntity.ok("consumption is recorded");
	}

	// TODO: StockedFood をたどって取得する
	// @GetMapping("/{id}")
	// public ResponseEntity<ConsumptionRecord> getConsumptionRecord(@PathVariable UUID
	// id) {
	// return this.consumptionRecords.findById(id) //
	// .map(ResponseEntity::ok) //
	// .orElseThrow(ResourceNotFoundException::new);
	// }
	//
	// @GetMapping
	// public ResponseEntity<List<ConsumptionRecord>> getConsumptionRecords() {
	// return ResponseEntity.ok(this.consumptionRecords.findAll());
	// }

}
