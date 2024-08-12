package custom.tibame201020.coindesk.service.impl;

import custom.tibame201020.coindesk.domain.Currency;
import custom.tibame201020.coindesk.repo.CurrencyRepo;
import custom.tibame201020.coindesk.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepo currencyRepo;

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyRepo.findAll();
    }

    @Override
    public Currency getCurrency(String coinCategory) {
        return currencyRepo.findById(coinCategory)
                .orElseThrow(() -> {
                    log.error("currency not find: {}", coinCategory);
                    return new RuntimeException("currency not find: " + coinCategory);
                });
    }

    @Override
    public Currency createCurrency(String coinCategory, String coinDescription, BigDecimal rate) {
        Currency currency = currencyRepo.findById(coinCategory).orElse(null);

        if (Objects.nonNull(currency)) {
            log.error("currency already exists: {}", coinCategory);
            throw new RuntimeException("currency already exists: " + coinCategory);
        }

        Instant createTime = Instant.now();

        return currencyRepo.save(
                Currency.builder()
                        .coinCategory(coinCategory)
                        .coinDescription(coinDescription)
                        .rate(rate)
                        .createTime(createTime)
                        .updateTime(createTime)
                        .build()
        );
    }

    @Override
    public Currency updateCurrency(String coinCategory, String coinDescription, BigDecimal rate) {
        Instant updateTime = Instant.now();

        Currency currency = getCurrency(coinCategory);
        currency.setCoinDescription(Optional.ofNullable(coinDescription).orElse(currency.getCoinDescription()));
        currency.setRate(Optional.ofNullable(rate).orElse(currency.getRate()));
        currency.setUpdateTime(updateTime);

        return currencyRepo.save(currency);
    }

    @Override
    public void deleteCurrency(String coinCategory) {
        Currency currency = getCurrency(coinCategory);
        currencyRepo.delete(currency);
    }
}
