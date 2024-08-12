package custom.tibame201020.coindesk.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Currency implements Serializable {
    /**
     * 幣別
     */
    @Id
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
    private Instant apiUpdateTime;
    /**
     * entity 建立時間
     */
    private Instant createTime;
    /**
     * entity 更新時間
     */
    private Instant updateTime;
}
