package org.dvigal.lesson.spring_1;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;


@RequiredArgsConstructor
public class StockPriceScheduler implements DisposableBean, InitializingBean {
    private final ExecutorService executorService;
    private final StockPriceLoader loader;

    @Override
    public void afterPropertiesSet() throws Exception {
        executorService.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                var time = LocalDateTime.now();
                try {
                    System.out.println("Load stock price");
                    loader.load(time.toLocalDate());
                    var nextTick = time.plusDays(1);
                    System.out.println("Next loading time " + nextTick);
                    Thread.sleep(TimeUnit.DAYS.toMillis(1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Stopped StockPriceScheduler");
        executorService.shutdownNow();
    }
}
