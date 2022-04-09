package org.dvigal.lesson.spring_2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dvigal.lesson.spring_2.api.StockPriceDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockPriceController {
    private final StockPriceFacade stockPriceFacade;

    private final ObjectMapper objectMapper;

    @GetMapping("/prices")
    public List<StockPriceDto> getPrices() {
        return stockPriceFacade.getPrices();
    }

    @DeleteMapping("/prices")
    public void delete() {
        stockPriceFacade.deleteAll();
    }

    @PostMapping("/prices")
    public StockPriceDto createPrice(@RequestBody StockPriceDto model) {
        return stockPriceFacade.save(model);
    }

    @GetMapping("/prices/{id}")
    public ResponseEntity<Object> getPrice(@PathVariable("id") String intValue) {
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/prices/{id}")
    public void updateStockPriceLow(StockPriceDto model) {
        System.out.println(model);
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public void handleException(RuntimeException ex) {
        log.error("An error exception occurred", ex);
    }
}
