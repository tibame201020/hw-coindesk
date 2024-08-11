package custom.tibame201020.coindesk.controller;

import custom.tibame201020.coindesk.client.BitCoinClient;
import custom.tibame201020.coindesk.dto.BitcoinRateData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coin-desk")
@RequiredArgsConstructor
@Slf4j
public class CoinDeskApi {
    private final BitCoinClient bitCoinClient;

    @GetMapping("/current-rate")
    public BitcoinRateData getCurrentPriceRate() {
        return bitCoinClient.getCurrentPriceRate();
    }
}
