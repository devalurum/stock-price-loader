package org.dvigal.lesson.spring_1;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

@Accessors(chain = true)
@Data
public class StockPriceModel {
    private Long id;
    private BigDecimal low;
    private BigDecimal high;
    private BigDecimal open;
    private BigDecimal close;
    private LocalDate date;
    private String ticker;
}
