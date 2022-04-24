package org.devalurum.stockprice.price_loader;


import org.devalurum.stockprice.domain.StockPriceModel;

import java.time.LocalDate;
import java.util.stream.Stream;

public interface StockPriceLoader {

    Stream<StockPriceModel> load(LocalDate date);

}
