package org.devalurum.stockprice.api;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

@Accessors(chain = true)
@Getter
@Setter
public class StockPriceDto {
    private LocalDate date;
    private String ticker;
    private BigDecimal open;
    private BigDecimal close;
    private BigDecimal low;
    private BigDecimal high;
}
