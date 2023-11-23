package com.example.stockedfoodmanagement.controller;

import com.example.stockedfoodmanagement.RestDocsConfig;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @see StockedFoodController
 * @author kiyota
 */
@SpringBootTest(properties = {
        "spring.flyway.enabled=false",
        "spring.datasource.url=jdbc:h2:mem:testdb;"
})
@AutoConfigureMockMvc
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@AutoConfigureRestDocs
@RequiredArgsConstructor
@Import(RestDocsConfig.class)
class StockedFoodControllerTests {

    final MockMvc mvc;

    @Test
    void すべての備蓄食を取得する() throws Exception {
        mvc.perform(get("/stocked_foods")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                // .andDo(MockMvcResultHandlers.print())
                .andDo(document("get-all-stocked-foods",
                        responseFields(
                                fieldWithPath("[].id").description("ID"),
                                fieldWithPath("[].name").description("名前"),
                                fieldWithPath("[].price").description("価格"),
                                fieldWithPath("[].purchasedAt").description("購入日"),
                                fieldWithPath("[].bestBefore").description("賞味期限"),
                                fieldWithPath("[].useUp").description("備蓄食が一回で使い切り、食べ切りのものかを表すフラグ(使い切りだと true)"),
                                fieldWithPath("[].memo").description("備考")
                        )));
    }
}