package org.devalurum.stockprice.controller;

import lombok.RequiredArgsConstructor;
import org.devalurum.stockprice.api.StockPriceDto;
import org.devalurum.stockprice.domain.StockPriceModel;
import org.devalurum.stockprice.service.StockPriceService;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
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

    public void deleteAll() {
        throw new RuntimeException();
    }

    public StockPriceDto save(StockPriceDto model) {
        return new StockPriceDto();
    }

    public StockPriceDto get(String invValue) {
        throw new EntityNotFoundException();
    }

    public Optional<StockPriceDto> findById(String intValue) {
        return Optional.empty();
    }
}
