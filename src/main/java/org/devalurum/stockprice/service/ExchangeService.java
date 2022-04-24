package org.devalurum.stockprice.service;

import lombok.RequiredArgsConstructor;
import org.devalurum.stockprice.repository.ExchangerRepository;
import org.devalurum.stockprice.domain.ExchangeModel;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ExchangeService {
    private final ExchangerRepository exchangerRepository;

    public Optional<ExchangeModel> findById(long id) {
        return exchangerRepository.findById(id);
    }
}
