package org.devalurum.stockprice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@EnableConfigurationProperties
@ConfigurationPropertiesScan
@EnableFeignClients
@SpringBootApplication
public class StockPriceLoaderService {

    public static void main(String[] args) {
        SpringApplication.run(StockPriceLoaderService.class, args);
    }

}
