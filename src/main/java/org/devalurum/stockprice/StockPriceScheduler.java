package org.devalurum.stockprice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.devalurum.stockprice.service.StockPriceService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class StockPriceScheduler {
    private final StockPriceService stockPriceService;


    @Scheduled(fixedRateString = "${app-config.scheduler.stock-price.rate:1000}")
    public void afterPropertiesSet() {
        log.info("Run stock price loading {}", stockPriceService);
        stockPriceService.loadAndSave();
    }
}
