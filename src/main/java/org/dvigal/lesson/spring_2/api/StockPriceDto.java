package org.dvigal.lesson.spring_2.api;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

@Accessors(chain = true)
@Data
public class StockPriceDto {
    private LocalDate date;
    private String ticker;
    private BigDecimal open;
    private BigDecimal close;
    private BigDecimal low;
    private BigDecimal high;
}
