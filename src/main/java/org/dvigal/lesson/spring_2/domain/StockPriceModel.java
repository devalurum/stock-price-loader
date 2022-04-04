package org.dvigal.lesson.spring_2.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;


@Accessors(chain = true)
@Getter
@Setter
@Entity
public class StockPriceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal low;
    private BigDecimal high;
    private BigDecimal open;
    private BigDecimal close;
    private LocalDate date;
    private String ticker;
}
