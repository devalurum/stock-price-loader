package org.dvigal.lesson.spring_2.persistent;

import org.dvigal.lesson.spring_2.domain.StockPriceModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StockPriceRepository extends JpaRepository<StockPriceModel, Long> {
}
