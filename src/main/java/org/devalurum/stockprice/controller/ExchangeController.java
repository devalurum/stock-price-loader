package org.devalurum.stockprice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.devalurum.stockprice.api.ExchangeDto;
import org.devalurum.stockprice.api.StockPriceDto;
import org.devalurum.stockprice.domain.ExchangeModel;
import org.devalurum.stockprice.domain.StockPriceModel;
import org.devalurum.stockprice.exception.NotFoundExchange;
import org.devalurum.stockprice.service.ExchangeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping("/exchanges")
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    @PostMapping
    public Long createExchange(@RequestBody ExchangeDto dto) {
        throw new RuntimeException("Not implemented yet");
    }

    @GetMapping("/{id}")
    public ExchangeDto get(@PathVariable("id") Long id) {
        return exchangeService
                .findById(id)
                .map(this::map)
                .orElseThrow(() -> new NotFoundExchange(id));
    }

    private ExchangeModel map(ExchangeDto dto) {
        return new ExchangeModel().setName(dto.getName())
                .setPrices(dto.getPrices() != null
                        ? dto.getPrices().stream().map(this::map).collect(Collectors.toList())
                        : Collections.emptyList());
    }

    private StockPriceModel map(StockPriceDto dto) {
        return new StockPriceModel()
                .setTicker(dto.getTicker())
                .setDate(dto.getDate());
    }

    private StockPriceDto map(StockPriceModel model) {
        return new StockPriceDto()
                .setTicker(model.getTicker())
                .setDate(model.getDate());
    }

    private ExchangeDto map(ExchangeModel model) {
        return new ExchangeDto()
                .setName(model.getName())
                .setPrices(model.getPrices().stream().map(this::map).collect(Collectors.toList()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public void handleException(RuntimeException ex) {
        log.error("An error exception occurred", ex);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Exchange not found by passed id")
    @ExceptionHandler(NotFoundExchange.class)
    public void handleException(NotFoundExchange ex) {
        log.error("An error exception occurred", ex);
    }
}
