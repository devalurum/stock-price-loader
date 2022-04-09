package org.dvigal.lesson.spring_2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class StockPriceLoaderServiceTest {

    @Autowired
    private StockPriceLoaderService service;

    @Test
    void smokeTest() {
        Assertions.assertNotNull(service);
    }
}