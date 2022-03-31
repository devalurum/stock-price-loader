package org.dvigal.lesson.spring_1;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@RequiredArgsConstructor
public class StockPriceScheduler implements DisposableBean, InitializingBean {
    private final ScheduledExecutorService executorService;
    private final StockPriceLoader loader;

    @Override
    public void afterPropertiesSet() throws Exception {
        executorService.scheduleAtFixedRate(() -> {
            System.out.println("Start loading");
            loader.load(LocalDate.now().minusDays(1)).forEach(price -> System.out.println(price));
            System.out.println("End loading");
        }, 0, 1, TimeUnit.DAYS);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Stopped StockPriceScheduler");
        executorService.shutdownNow();
    }
}
