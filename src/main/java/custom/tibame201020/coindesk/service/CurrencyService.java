package custom.tibame201020.coindesk.service;

import custom.tibame201020.coindesk.domain.Currency;
import custom.tibame201020.coindesk.dto.BitCoinRateData;
import custom.tibame201020.coindesk.dto.CurrencyDTO;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public interface CurrencyService {

    /**
     * 取得所有currency
     * @return List<Currency>
     */
    List<Currency> getAllCurrencies();

    /**
     * 取得currency
     * @param coinCategory 幣別
     * @return currency
     */
    Currency getCurrency(String coinCategory);

    /**
     * 建立currency
     * @param coinCategory 幣別
     * @param coinDescription 幣別中文名稱
     * @param rate 匯率
     * @return created currency
     */
    Currency createCurrency(String coinCategory, String coinDescription, BigDecimal rate);

    /**
     * 更新currency
     * @param coinCategory 幣別
     * @param coinDescription 幣別中文名稱
     * @param rate 匯率
     * @return updated currency
     */
    Currency updateCurrency(String coinCategory, String coinDescription, BigDecimal rate);

    /**
     * 刪除currency
     * @param coinCategory 幣別
     */
    void deleteCurrency(String coinCategory);

    /**
     * bitCoinRateData convert to currency
     * @param bitCoinRateData bitCoinRateData
     * @return list currency
     */
    List<Currency> convertBigCoinRateData(BitCoinRateData bitCoinRateData);


    /**
     * parse string to instant
     * @param isoDateString 日期時間string
     * @return instant
     */
    default Instant parseISODate(String isoDateString) {
        try {
            return Instant.parse(isoDateString);
        } catch (DateTimeParseException e) {
            return Instant.now();
        }
    }

    /**
     * parse instant to string
     * @param instant instant
     * @return date string
     */
    default String formatDateTime(Instant instant) {
        return instant.atZone(ZoneId.of("Asia/Taipei"))
                .format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }


    /**
     * convert entity to dto
     * @param currency currency
     * @return currency dto
     */
    default CurrencyDTO convertToDTO(Currency currency) {
        return CurrencyDTO.builder()
                .coinCategory(currency.getCoinCategory())
                .coinDescription(currency.getCoinDescription())
                .rate(currency.getRate())
                .updateTime(formatDateTime(currency.getApiUpdateTime()))
                .build();
    }
}
