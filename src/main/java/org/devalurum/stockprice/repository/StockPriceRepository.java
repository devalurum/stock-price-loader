package org.devalurum.stockprice.repository;

import org.devalurum.stockprice.domain.StockPriceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;


public interface StockPriceRepository extends JpaRepository<StockPriceModel, Long> {

    @Query("SELECT s FROM StockPriceModel s WHERE s.low > :price")
    List<StockPriceModel> findStock(@Param("price") BigDecimal price);


}
