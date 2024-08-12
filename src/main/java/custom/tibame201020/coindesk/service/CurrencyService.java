package custom.tibame201020.coindesk.service;

import custom.tibame201020.coindesk.domain.Currency;

import java.math.BigDecimal;
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
}
