package custom.tibame201020.coindesk.client;

import custom.tibame201020.coindesk.constant.SystemProps;
import custom.tibame201020.coindesk.dto.BitcoinRateData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "bitCoinClient",
        url = SystemProps.coinDataUrl
)
public interface BitCoinClient {
    @GetMapping("/currentprice.json")
    BitcoinRateData getCurrentPriceRate();
}
