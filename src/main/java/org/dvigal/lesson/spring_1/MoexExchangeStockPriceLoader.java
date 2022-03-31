package org.dvigal.lesson.spring_1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class MoexExchangeStockPriceLoader implements StockPriceLoader {
    private static final String url = "https://iss.moex.com/iss/history/engines/stock/markets/shares/securities.json?date={date}&start={start}";
    private final RestTemplate restTemplate;

    @Override
    public Stream<StockPriceModel> load(final LocalDate date) {
        // restTemplate.getForObject(url, Object.class, date.toString(), 200);
        return Stream.empty();
    }

}
