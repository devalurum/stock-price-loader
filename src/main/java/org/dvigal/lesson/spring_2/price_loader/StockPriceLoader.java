package org.dvigal.lesson.spring_2.price_loader;


import org.dvigal.lesson.spring_2.domain.StockPriceModel;

import java.time.LocalDate;
import java.util.stream.Stream;

public interface StockPriceLoader {

    Stream<StockPriceModel> load(LocalDate date);

}
