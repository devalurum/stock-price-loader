package org.dvigal.lesson.spring_2.service;

import lombok.RequiredArgsConstructor;
import org.dvigal.lesson.spring_2.domain.ExchangeModel;
import org.dvigal.lesson.spring_2.repository.ExchangerRepository;
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
