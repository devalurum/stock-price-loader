package org.devalurum.stockprice.service;

import lombok.RequiredArgsConstructor;
import org.devalurum.stockprice.price_loader.StockPriceLoader;
import org.devalurum.stockprice.repository.StockPriceRepository;
import org.devalurum.stockprice.domain.StockPriceModel;
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
