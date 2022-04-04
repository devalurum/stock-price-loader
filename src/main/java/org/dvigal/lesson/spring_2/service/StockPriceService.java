package org.dvigal.lesson.spring_2.service;

import lombok.RequiredArgsConstructor;
import org.dvigal.lesson.spring_2.domain.StockPriceModel;
import org.dvigal.lesson.spring_2.persistent.StockPriceRepository;
import org.dvigal.lesson.spring_2.price_loader.StockPriceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StockPriceService {
    private final StockPriceLoader priceLoader;
    private final StockPriceRepository stockPriceRepository;

    @Transactional(readOnly = true)
    public List<StockPriceModel> getPrices() {
        return stockPriceRepository.findAll();
    }

    @Transactional
    public void save(List<StockPriceModel> prices) {
        stockPriceRepository.saveAll(prices);
    }

    public void loadAndSave() {
//        Spring retry
        var priceStream = priceLoader.load(LocalDate.now());
        save(priceStream.collect(Collectors.toList()));
    }
}
