package custom.tibame201020.coindesk.controller;

import custom.tibame201020.coindesk.domain.Currency;
import custom.tibame201020.coindesk.dto.CurrencyDTO;
import custom.tibame201020.coindesk.service.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Get all currencies", description = "Fetches a list of all available currencies from the database.")
    @GetMapping("/all")
    public List<Currency> getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }

    /**
     * get currency by coinCategory
     * @param coinCategory 幣別
     * @return currency data
     */
    @Operation(summary = "查詢幣別對應表資料 API,並顯示其內容", description = "Fetches the details of a specific currency based on its category (coinCategory).")
    @GetMapping("/{coinCategory}")
    public Currency getCurrency(@PathVariable("coinCategory") String coinCategory) {
        return currencyService.getCurrency(coinCategory);
    }

    /**
     * create currency
     * @param currencyDTO currencyDTO
     * @return created currency data
     */
    @Operation(summary = "新增幣別對應表資料 API", description = "Creates a new currency entry in the database using the provided details in currencyDTO.")
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
    @Operation(summary = "更新幣別對應表資料 API,並顯示其內容", description = "Updates the details of an existing currency based on its category (coinCategory).")
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
    @Operation(summary = "刪除幣別對應表資料 API", description = "Deletes a currency from the database based on its category (coinCategory).")
    @DeleteMapping("/{coinCategory}")
    public void deleteCurrency(@PathVariable("coinCategory") String coinCategory) {
        currencyService.deleteCurrency(coinCategory);
    }
}
