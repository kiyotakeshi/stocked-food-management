package com.example.stockedfoodmanagement.stockedFood.web;

import com.example.stockedfoodmanagement.stockedFood.CreateStockedFood;
import com.example.stockedfoodmanagement.stockedFood.StockFoodTestUtils;
import com.example.stockedfoodmanagement.test.ControllerIntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author kiyota
 * @see StockedFoodController
 */
@ControllerIntegrationTest
@RequiredArgsConstructor
class StockedFoodControllerIntegrationTests {

	private final UUID CUP_RAMEN_UUID = UUID.randomUUID();

	private final UUID RICE_UUID = UUID.randomUUID();

	private final UUID CANNED_MACKEREL_UUID = UUID.randomUUID();

	final TestEntityManager entityManager;

	final MockMvc mvc;

	final ObjectMapper mapper;

	@BeforeEach
	void setUp() {
		this.entityManager.persist(StockFoodTestUtils.create(this.CUP_RAMEN_UUID, "カップラーメン", BigDecimal.valueOf(150),
				LocalDate.of(2023, 12, 23), LocalDate.of(2025, 12, 1), true, ""));

		this.entityManager.persist(StockFoodTestUtils.create(this.RICE_UUID, "お米", BigDecimal.valueOf(5_000),
				LocalDate.of(2023, 12, 13), LocalDate.of(2024, 7, 1), false, "10kg"));
		this.entityManager.persist(StockFoodTestUtils.create(this.CANNED_MACKEREL_UUID, "鯖缶", BigDecimal.valueOf(250),
				LocalDate.of(2023, 12, 20), LocalDate.of(2025, 9, 1), false, "ドラックストアで初めて見つけた"));
	}

	@Test
	void すべての備蓄食を取得する() throws Exception {
		this.mvc.perform(get("/stocked_foods").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			// .andDo(MockMvcResultHandlers.print())
			.andDo(document("get-all-stocked-foods", responseFields(fieldWithPath("[].id").description("ID"), //
					fieldWithPath("[].name").description("名前"), //
					fieldWithPath("[].price").description("価格"), //
					fieldWithPath("[].purchasedAt").description("購入日"), //
					fieldWithPath("[].bestBefore").description("賞味期限"), //
					fieldWithPath("[].useUp").description("備蓄食が一回で使い切り、食べ切りのものかを表すフラグ(使い切りだと true)"), //
					fieldWithPath("[].memo").description("備考"))));
	}

	@Test
	void 備蓄食を登録する() throws Exception {
		var command = new CreateStockedFood("パスタ", null, LocalDate.of(2023, 11, 25), LocalDate.of(2025, 3, 1), false,
				"1.5mm, 500g");

		this.mvc
			.perform(post("/stocked_foods").contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(command)))
			.andExpect(status().isCreated())
			.andExpect(header().exists("Location"))
			.andDo(document("create-stocked-food"));
	}

}
