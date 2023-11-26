package com.example.stockedfoodmanagement.stockedFood.web;

import com.example.stockedfoodmanagement.stockedFood.StockFoodTestUtils;
import com.example.stockedfoodmanagement.stockedFood.StockedFoods;
import com.example.stockedfoodmanagement.test.ControllerIntegrationTest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author kiyota
 */
@ControllerIntegrationTest
@RequiredArgsConstructor
class NearExpirationFoodControllerTests {

	final StockedFoods stockedFoods;

	final MockMvc mvc;

	@BeforeEach
	void setUp() {
		this.stockedFoods.saveAll(List.of(
				StockFoodTestUtils.create(UUID.randomUUID(), "カップラーメン", BigDecimal.valueOf(150), LocalDate.now(),
						LocalDate.now().plusMonths(1).minusDays(1), true, ""),
				StockFoodTestUtils.create(UUID.randomUUID(), "お米", BigDecimal.valueOf(5_000), LocalDate.now(),
						LocalDate.now().plusMonths(1), false, "10kg"),
				StockFoodTestUtils.create(UUID.randomUUID(), "鯖缶", BigDecimal.valueOf(250), LocalDate.now(),
						LocalDate.now().plusMonths(2), false, "ドラックストアで初めて見つけた")));
	}

	@AfterEach
	void after() {
		this.stockedFoods.deleteAll();
	}

	@Test
	void 賞味期限が1ヶ月前の備蓄食を取得する() throws Exception {
		this.mvc.perform(get("/near_expiration_foods") //
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()) //
			.andExpect(jsonPath("$").isArray()) //
			.andExpect(jsonPath("$.size()").value(2)) //
			.andDo(document("near-expiration-foods"));

	}

	@Test
	void 賞味期限が2ヶ月前の備蓄食を取得する() throws Exception {
		this.mvc.perform(get("/near_expiration_foods") //
			.queryParam("month", "2")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()) //
			.andExpect(jsonPath("$").isArray()) //
			.andExpect(jsonPath("$.size()").value(3)) //
			.andDo(document("near-expiration-foods-with-query-param", //
					queryParameters(parameterWithName("month").optional() //
						.description(
								"指定した数字(X)のXヶ月後に賞味期限切れになる備蓄食を確認する。`2`を指定すると現在から2ヶ月以内に賞味期限切れになる備蓄食を表示する。デフォルト(=query param を未指定)だと1ヶ月。"))));

	}

}
