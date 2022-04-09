package org.dvigal.lesson.spring_2.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Accessors(chain = true)
@Getter
@Setter
@Data
@Entity
public class ExchangeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String externalId;
    private String name;
    @OneToMany(mappedBy = "exchange", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StockPriceModel> prices;
}
