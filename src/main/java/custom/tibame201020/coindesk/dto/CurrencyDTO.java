package custom.tibame201020.coindesk.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class CurrencyDTO implements Serializable {
    /**
     * 幣別
     */
    private String coinCategory;
    /**
     * 幣別中文名稱
     */
    private String coinDescription;
    /**
     * 匯率
     */
    private BigDecimal rate;
    /**
     * api更新時間
     */
    private String updateTime;
}
