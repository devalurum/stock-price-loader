package org.devalurum.stockprice.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Accessors(chain = true)
@Getter
@Setter
@Data
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

    @ManyToOne
    @JoinColumn(name = "exchange_id")
    private ExchangeModel exchange;
}