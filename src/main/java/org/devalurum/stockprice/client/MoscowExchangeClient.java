package org.devalurum.stockprice.client;

import org.devalurum.stockprice.api.response.StockPriceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@FeignClient(name = "moexClient", url = "${app-config.moex.stock-price-url}")
public interface MoscowExchangeClient {

    @GetMapping(value = "/securities.json",
            produces = MediaType.APPLICATION_JSON_VALUE,
            params = {"date, start"})
    StockPriceResponse getStockPrices(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                      @RequestParam int start);

}
