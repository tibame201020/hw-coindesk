package custom.tibame201020.coindesk.service;

import custom.tibame201020.coindesk.BasicMockitoExtensionTest;
import custom.tibame201020.coindesk.domain.Currency;
import custom.tibame201020.coindesk.dto.BitCoinRateData;
import custom.tibame201020.coindesk.repo.CurrencyRepo;
import custom.tibame201020.coindesk.service.impl.CurrencyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CurrencyServiceTest implements BasicMockitoExtensionTest {

    @InjectMocks
    private CurrencyServiceImpl currencyService;
    @Mock
    private CurrencyRepo currencyRepo;

    private Currency currency;
    private BitCoinRateData testBitCoinRateData;

    @BeforeEach
    void init() {
        currency = Currency.builder().build();


        BitCoinRateData.Time time = new BitCoinRateData.Time();
        time.setUpdatedISO("2024-08-12T01:10:22+00:00");

        BitCoinRateData.CurrencyInfo usdData = new BitCoinRateData.CurrencyInfo();
        usdData.setCode("USD");
        usdData.setRateFloat(50000f);
        usdData.setDescription("United States Dollar");

        testBitCoinRateData = new BitCoinRateData();
        testBitCoinRateData.setTime(time);
        Map<String, BitCoinRateData.CurrencyInfo> bpiMap = new HashMap<>();
        bpiMap.put("USD", usdData);
        testBitCoinRateData.setBpi(bpiMap);
    }

    @Test
    void getAllCurrencies() {
        when(currencyRepo.findAll()).thenReturn(Stream.of(currency).collect(Collectors.toList()));

        List<Currency> result = currencyService.getAllCurrencies();

        assertThat(result)
                .isNotNull()
                .isEqualTo(Stream.of(currency).collect(Collectors.toList()));
    }

    @Test
    void getCurrency_Success() {
        when(currencyRepo.findById("BTC")).thenReturn(Optional.of(currency));

        Currency result = currencyService.getCurrency("BTC");

        assertThat(result).isEqualTo(currency);
    }

    @Test
    void getCurrency_NotFound() {
        when(currencyRepo.findById("ETH")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> currencyService.getCurrency("ETH"));
    }

    @Test
    void createCurrency_Success() {
        when(currencyRepo.findById("ETH")).thenReturn(Optional.empty());
        when(currencyRepo.save(any(Currency.class))).thenReturn(currency);

        Currency result = currencyService.createCurrency("ETH", "Ethereum", new BigDecimal("3000"));

        assertThat(result).isEqualTo(currency);
        verify(currencyRepo).save(any(Currency.class));
    }

    @Test
    void createCurrency_AlreadyExists() {
        when(currencyRepo.findById("BTC")).thenReturn(Optional.of(currency));

        assertThrows(RuntimeException.class, () -> currencyService.createCurrency("BTC", "Bitcoin", new BigDecimal("50000")));
    }

    @Test
    void updateCurrency_Success() {
        Currency updatedCurrency = Currency.builder()
                .coinCategory("BTC")
                .coinDescription("Updated Bitcoin")
                .rate(new BigDecimal("55000"))
                .createTime(currency.getCreateTime())
                .updateTime(Instant.now())
                .build();

        when(currencyRepo.findById("BTC")).thenReturn(Optional.of(currency));
        when(currencyRepo.save(any(Currency.class))).thenReturn(updatedCurrency);

        Currency result = currencyService.updateCurrency("BTC", "Updated Bitcoin", new BigDecimal("55000"));

        assertThat(result).isEqualTo(updatedCurrency);
        verify(currencyRepo).save(any(Currency.class));
    }

    @Test
    void updateCurrency_NotFound() {
        when(currencyRepo.findById("ETH")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> currencyService.updateCurrency("ETH", "Ethereum", new BigDecimal("3000")));
    }

    @Test
    void deleteCurrency_Success() {
        when(currencyRepo.findById("BTC")).thenReturn(Optional.of(currency));
        doNothing().when(currencyRepo).delete(currency);

        currencyService.deleteCurrency("BTC");

        verify(currencyRepo).delete(currency);
    }

    @Test
    void deleteCurrency_NotFound() {
        when(currencyRepo.findById("ETH")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> currencyService.deleteCurrency("ETH"));
    }

    @Test
    void convertBigCoinRateData_Success() {
        when(currencyRepo.findById("USD")).thenReturn(Optional.empty());
        when(currencyRepo.save(any(Currency.class))).thenReturn(currency);

        List<Currency> result = currencyService.convertBigCoinRateData(testBitCoinRateData);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(currency, result.get(0));
        verify(currencyRepo).save(any(Currency.class));
    }

    @Test
    void convertBigCoinRateData_NullInput() {
        List<Currency> result = currencyService.convertBigCoinRateData(null);

        assertTrue(result.isEmpty());
    }
}