package com.example.stockedfoodmanagement.consumptionRecord.web;

import com.example.stockedfoodmanagement.consumptionRecord.ConsumptionRecords;
import com.example.stockedfoodmanagement.stockedFood.StockFoodTestUtils;
import com.example.stockedfoodmanagement.stockedFood.StockedFoods;
import com.example.stockedfoodmanagement.test.ControllerIntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author kiyota
 * @see ConsumptionRecordController
 */
@ControllerIntegrationTest
@RequiredArgsConstructor
class ConsumptionRecordControllerTests {

	private final UUID RAMEN_UUID = UUID.randomUUID();

	private final UUID CANNED_TUNA_UUID = UUID.randomUUID();

	final StockedFoods stockedFoods;

	final ConsumptionRecords consumptionRecords;

	final MockMvc mvc;

	final ObjectMapper mapper;

	@BeforeEach
	void setUp() {
		this.stockedFoods.saveAll(List.of(
				StockFoodTestUtils.create(this.RAMEN_UUID, "カップラーメン", BigDecimal.valueOf(150), LocalDate.now(),
						LocalDate.now().plusMonths(1).minusDays(1), false, true, ""),
				// テストのために consumptionFlag は true
				StockFoodTestUtils.create(this.CANNED_TUNA_UUID, "ツナ缶", BigDecimal.valueOf(200), LocalDate.now(),
						LocalDate.now().plusMonths(1), true, false, "10kg")));
	}

	@AfterEach
	void after() {
		this.stockedFoods.deleteAll();
		this.consumptionRecords.deleteAll();
	}

	@Test
	void 備蓄食の消費履歴を登録する() throws Exception {
		var command = new CreateConsumptionRecord(this.RAMEN_UUID, LocalDateTime.of(2023, 11, 28, 2, 26), false);

		this.mvc.perform(post("/consumption_records") //
			.contentType(MediaType.APPLICATION_JSON) //
			.content(this.mapper.writeValueAsString(command))) //
			.andExpect(status().isOk()) //
			.andExpect(content().string("consumption is recorded")) //
			.andDo(document("create-consumption-record"));

		assertThat(this.consumptionRecords.findAll().size()).isEqualTo(1);
	}

	@Test
	void 備蓄食を使い切る際の消費履歴を登録する() throws Exception {
		var command = new CreateConsumptionRecord(this.RAMEN_UUID, LocalDateTime.of(2023, 11, 28, 2, 26), true);

		this.mvc.perform(post("/consumption_records") //
			.contentType(MediaType.APPLICATION_JSON) //
			.content(this.mapper.writeValueAsString(command))) //
			.andExpect(status().isOk()) //
			.andExpect(content().string("consumption is recorded"));

		var stockedFood = this.stockedFoods.findById(this.RAMEN_UUID).orElseThrow();
		assertThat(stockedFood.isConsumptionFlag()).isTrue();
		assertThat(this.consumptionRecords.findAll().size()).isEqualTo(1);
	}

	@Test
	void 使い切った備蓄食は消費履歴を登録しない() throws Exception {
		var command = new CreateConsumptionRecord(this.CANNED_TUNA_UUID, LocalDateTime.of(2023, 11, 28, 2, 26), true);

		this.mvc.perform(post("/consumption_records") //
			.contentType(MediaType.APPLICATION_JSON) //
			.content(this.mapper.writeValueAsString(command))) //
			.andExpect(status().isOk()) //
			.andExpect(content().string("already the food is consumed")) //
			.andDo(document("not-create-consumption-record"));

		assertThat(this.consumptionRecords.findAll().size()).isEqualTo(0);
	}

}
