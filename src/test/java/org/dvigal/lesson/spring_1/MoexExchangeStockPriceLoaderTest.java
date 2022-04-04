package org.dvigal.lesson.spring_1;

import org.dvigal.lesson.spring_2.price_loader.MoexExchangeStockPriceLoader;
import org.dvigal.lesson.spring_2.price_loader.StockPriceLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RestTemplate.class, MoexExchangeStockPriceLoader.class})
class MoexExchangeStockPriceLoaderTest {

    @Autowired
    private StockPriceLoader stockPriceLoader;

    @Test
    public void loadPricesShouldBeNotEmpty() {
        // given
        var date = LocalDate.parse("2022-03-28");

        // when
        var loadedPrices = stockPriceLoader.load(date).limit(10).collect(Collectors.toList());

        // then
        Assertions.assertNotNull(loadedPrices);
        Assertions.assertFalse(loadedPrices.isEmpty());
    }

}