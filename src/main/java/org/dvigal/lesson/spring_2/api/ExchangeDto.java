package org.dvigal.lesson.spring_2.api;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@Accessors(chain = true)
public class ExchangeDto {
    private String name;
    private List<StockPriceDto> prices;
}
