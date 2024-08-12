package custom.tibame201020.coindesk.controller;

import custom.tibame201020.coindesk.domain.Currency;
import custom.tibame201020.coindesk.dto.CurrencyDTO;
import custom.tibame201020.coindesk.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/currency")
@RequiredArgsConstructor
@Slf4j
public class CurrencyApi {
    private final CurrencyService currencyService;

    /**
     * get all currencies
     * @return all currencies
     */
    @GetMapping("/all")
    public List<Currency> getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }

    /**
     * get currency by coinCategory
     * @param coinCategory 幣別
     * @return currency data
     */
    @GetMapping("/{coinCategory}")
    public Currency getCurrency(@PathVariable("coinCategory") String coinCategory) {
        return currencyService.getCurrency(coinCategory);
    }

    /**
     * create currency
     * @param currencyDTO currencyDTO
     * @return created currency data
     */
    @PostMapping
    public Currency createCurrency(@RequestBody CurrencyDTO currencyDTO) {
        return currencyService.createCurrency(
                currencyDTO.getCoinCategory(),
                currencyDTO.getCoinDescription(),
                currencyDTO.getRate());
    }

    /**
     * update currency
     * @param coinCategory 幣別
     * @param currencyDTO currencyDTO
     * @return updated currency data
     */
    @PatchMapping("/{coinCategory}")
    public Currency updateCurrency(@PathVariable("coinCategory") String coinCategory, @RequestBody CurrencyDTO currencyDTO) {
        return currencyService.updateCurrency(
                coinCategory,
                currencyDTO.getCoinDescription(),
                currencyDTO.getRate()
        );
    }

    /**
     * delete currency by coinCategory
     * @param coinCategory 幣別
     */
    @DeleteMapping("/{coinCategory}")
    public void deleteCurrency(@PathVariable("coinCategory") String coinCategory) {
        currencyService.deleteCurrency(coinCategory);
    }
}
