package custom.tibame201020.coindesk.controller;

import custom.tibame201020.coindesk.client.BitCoinClient;
import custom.tibame201020.coindesk.domain.Currency;
import custom.tibame201020.coindesk.dto.BitCoinRateData;
import custom.tibame201020.coindesk.dto.CurrencyDTO;
import custom.tibame201020.coindesk.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/coin-desk")
@RequiredArgsConstructor
@Slf4j
public class CoinDeskApi {
    private final BitCoinClient bitCoinClient;
    private final CurrencyService currencyService;

    /**
     * get bit-coin rate origin data
     * @return BitCoinRateData
     */
    @GetMapping("/origin")
    public BitCoinRateData getOriginCurrentPriceRate() {
        return bitCoinClient.getCurrentPriceRate();
    }

    /**
     * get converted bit-coin rate data
     * @return
     */
    @GetMapping("/convert")
    public List<CurrencyDTO> getCovertCurrentPriceRate() {
        BitCoinRateData bitCoinRateData = bitCoinClient.getCurrentPriceRate();
        return currencyService.convertBigCoinRateData(bitCoinRateData)
                .stream()
                .map(currencyService::convertToDTO)
                .collect(Collectors.toList());
    }
}
