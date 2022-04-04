package org.dvigal.lesson.spring_2.controller;

import lombok.RequiredArgsConstructor;
import org.dvigal.lesson.spring_2.api.StockPriceDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockPriceController {
    private final StockPriceFacade stockPriceFacade;

    @GetMapping("/prices")
    public List<StockPriceDto> getPrices() {
        return stockPriceFacade.getPrices();
    }

}
