package org.dvigal.lesson.spring_2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dvigal.lesson.spring_2.service.StockPriceService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class StockPriceScheduler {
    private final StockPriceService stockPriceService;


    @Scheduled(fixedRateString = "${app-config.scheduler.stock-price.rate:1000}")
    public void afterPropertiesSet() throws Exception {
        log.info("Run stock price loading {}", stockPriceService);
        stockPriceService.loadAndSave();
//        executorService.scheduleAtFixedRate(() -> {
//            System.out.println("Start loading");
//            loader.load(LocalDate.now().minusDays(1)).forEach(price -> System.out.println(price));
//            System.out.println("End loading");
//        }, 0, 1, TimeUnit.DAYS);
    }
//
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("Stopped StockPriceScheduler");
//        executorService.shutdownNow();
//    }
}
