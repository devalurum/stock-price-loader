package org.dvigal.lesson.spring_2.repository;

import org.dvigal.lesson.spring_2.domain.StockPriceModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.LocalDate;

@AutoConfigureJson
@DataJpaTest
class StockPriceRepositoryTest {

    @Autowired
    private StockPriceRepository stockPriceRepository;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Sql(statements = "INSERT INTO stock_price_model (ticker) values ('moex');")
    @Test
    void testFindAll() {
        var prices = stockPriceRepository.findAll();
        Assertions.assertEquals(1, prices.size());
    }

    @Test
    void testSave() {
        // given
        var model = new StockPriceModel()
                .setTicker("MOEX")
                .setOpen(BigDecimal.ONE)
                .setClose(BigDecimal.ONE)
                .setHigh(BigDecimal.ONE)
                .setLow(BigDecimal.ONE)
                .setDate(LocalDate.parse("2022-01-01"));

        // when
        var savedModel = stockPriceRepository.save(model);

        // then
        Assertions.assertNotNull(savedModel.getId());
        Assertions.assertNotNull(stockPriceRepository.getById(model.getId()));
    }
}