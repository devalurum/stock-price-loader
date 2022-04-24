package org.devalurum.stockprice.repository;

import org.devalurum.stockprice.domain.ExchangeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangerRepository extends JpaRepository<ExchangeModel, Long> {
}
