package org.dvigal.lesson.spring_2.controller;

import lombok.RequiredArgsConstructor;
import org.dvigal.lesson.spring_2.api.StockPriceDto;
import org.dvigal.lesson.spring_2.domain.StockPriceModel;
import org.dvigal.lesson.spring_2.service.StockPriceService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StockPriceFacade {
    private final StockPriceService stockPriceService;

    public List<StockPriceDto> getPrices() {
        return stockPriceService
                .getPrices()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private StockPriceDto toDto(StockPriceModel model) {
        return new StockPriceDto()
                .setDate(model.getDate())
                .setTicker(model.getTicker())
                .setClose(model.getClose())
                .setOpen(model.getOpen())
                .setHigh(model.getHigh())
                .setLow(model.getLow());
    }
}
