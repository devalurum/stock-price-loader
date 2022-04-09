package org.dvigal.lesson.spring_2.repository;

import org.dvigal.lesson.spring_2.domain.ExchangeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangerRepository extends JpaRepository<ExchangeModel, Long> {
}
