package org.devalurum.stockprice.price_loader;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.devalurum.stockprice.api.response.History;
import org.devalurum.stockprice.client.MoscowExchangeClient;
import org.devalurum.stockprice.domain.StockPriceModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@Component
@Slf4j
@RequiredArgsConstructor
public class MoexExchangeStockPriceLoader implements StockPriceLoader {

    private final MoscowExchangeClient moexClient;

    @Override
    public Stream<StockPriceModel> load(final LocalDate from) {
        var currentDate = LocalDate.now();
        return Stream.iterate(
                        from,
                        localDate -> localDate.isBefore(currentDate) || localDate.isEqual(currentDate),
                        localDate -> localDate.plusDays(1))
                .map(date -> moexClient.getStockPrices(date, 100))
                .filter(data -> data != null && data.getHistory() != null && data.getHistory().getData() != null)
                .flatMap(data -> map(data.getHistory()));
    }

    private Stream<StockPriceModel> map(History history) {
        var columnToIndex = IntStream.range(0, history.getColumns().size())
                .boxed()
                .collect(Collectors.toMap(history.getColumns()::get, Function.identity()));

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

}
