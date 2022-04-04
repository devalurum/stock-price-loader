package org.dvigal.lesson.spring_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@ConfigurationPropertiesScan
@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(
        type = FilterType.REGEX,
        pattern="org\\.dvigal\\.lesson\\.spring_1\\..*"
))
public class StockPriceLoaderService {

    public static void main(String[] args) {
        SpringApplication.run(StockPriceLoaderService.class, args);
    }

}
