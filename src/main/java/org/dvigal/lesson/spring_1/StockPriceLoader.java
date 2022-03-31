package org.dvigal.lesson.spring_1;

import java.time.LocalDate;
import java.util.stream.Stream;

public interface StockPriceLoader {

    Stream<StockPriceModel> load(LocalDate date);

}
