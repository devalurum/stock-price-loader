package org.dvigal.lesson.spring_2.controller;

import org.dvigal.lesson.spring_2.domain.ExchangeModel;
import org.dvigal.lesson.spring_2.repository.StockPriceRepository;
import org.dvigal.lesson.spring_2.service.ExchangeService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ExchangeController.class)
class ExchangeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StockPriceRepository stockPriceRepository;
    @MockBean
    private ExchangeService exchangerService;

    @Test
    public void shouldReturn404() throws Exception {
        final var exchangeId = 10L;
        when(exchangerService.findById(exchangeId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/exchanges/{id}", exchangeId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturn200() throws Exception {
        final var exchangeId = 10L;
        when(exchangerService.findById(exchangeId)).thenReturn(
                Optional.of(new ExchangeModel()
                        .setPrices(Collections.emptyList())
                        .setId(10L).setName("MOEX")));

        mockMvc.perform(get("/exchanges/{id}", exchangeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is("MOEX")));
    }

}