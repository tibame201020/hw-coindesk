package custom.tibame201020.coindesk.controller;

import custom.tibame201020.coindesk.client.BitCoinClient;
import custom.tibame201020.coindesk.dto.BitCoinRateData;
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
    public void getCovertCurrentPriceRate() {
        //todo impl convert
    }
}
