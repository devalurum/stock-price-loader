package org.devalurum.stockprice.price_loader;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.devalurum.stockprice.domain.StockPriceModel;
import org.devalurum.stockprice.settings.MoexSettings;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Stream;


@Component
@Slf4j
@RequiredArgsConstructor
public class MoexExchangeStockPriceLoader implements StockPriceLoader {
    private final RestTemplate restTemplate;
    private final MoexSettings moexSettings;

    @Override
    public Stream<StockPriceModel> load(final LocalDate from) {
        var currentDate = LocalDate.now();
        var url = String.format("%s?date={date}&start={start}", moexSettings.getStockPriceUrl().toString());
        log.info("Moex url {}", url);
        return Stream.iterate(
                from,
                localDate -> localDate.isBefore(currentDate) || localDate.isEqual(currentDate),
                localDate -> localDate.plusDays(1))
                .map(date -> restTemplate.getForObject(url, Root.class, date.toString(), 100))
                .filter(data -> data != null && data.getHistory() != null && data.getHistory().getData() != null)
                .flatMap(data -> map(data.getHistory()));
    }

    private Stream<StockPriceModel> map(History history) {
        var columnToIndex = new HashMap<String, Integer>();
        for (int index = 0; index < history.getColumns().size(); index++) {
            columnToIndex.put(history.getColumns().get(index), index);
        }

        var localDateMapper = new Function<Object, LocalDate>() {
            @Override
            public LocalDate apply(Object value) {
                return value != null ? LocalDate.parse(value.toString()) : null;
            }
        };
        var priceMapper = new Function<Object, BigDecimal>() {
            @Override
            public BigDecimal apply(Object value) {
                return value != null ? new BigDecimal(value.toString()) : null;
            }
        };

        return history
                .getData()
                .stream()
                .flatMap(row ->
                    row.stream().map(item -> new StockPriceModel()
                            .setTicker(row.get(columnToIndex.get("SECID")).toString())
                            .setDate(localDateMapper.apply(row.get(columnToIndex.get("TRADEDATE"))))
                            .setOpen(priceMapper.apply(row.get(columnToIndex.get("OPEN"))))
                            .setClose(priceMapper.apply(row.get(columnToIndex.get("CLOSE"))))
                            .setHigh(priceMapper.apply(row.get(columnToIndex.get("HIGH"))))
                            .setLow(priceMapper.apply(row.get(columnToIndex.get("LOW"))))));
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    private static class History {
        public ArrayList<String> columns;
        public ArrayList<ArrayList<Object>> data;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Root {
        public History history;
    }


}
