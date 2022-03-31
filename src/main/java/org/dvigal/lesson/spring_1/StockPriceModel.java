package org.dvigal.lesson.spring_1;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class StockPriceModel {
    @ToString.Exclude
    private Object value;
}
