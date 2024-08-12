package custom.tibame201020.coindesk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class BitCoinRateData implements Serializable {
    private Time time;
    private String disclaimer;
    private String chartName;
    private Map<String, CurrencyInfo> bpi;

    @Data
    public static class Time implements Serializable {
        private String updated;
        private String updatedISO;
        @JsonProperty("updateduk")
        private String updateDuk;

    }

    @Data
    public static class CurrencyInfo implements Serializable {
        private String code;
        private String symbol;
        private String rate;
        private String description;
        @JsonProperty("rate_float")
        private float rateFloat;
    }
}
