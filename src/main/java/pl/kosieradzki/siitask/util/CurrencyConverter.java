package pl.kosieradzki.siitask.util;

import pl.kosieradzki.siitask.model.Currency;

import java.math.BigDecimal;

public class CurrencyConverter {

    //matrix of rates
    //     PLN   EUR   USD
    //PLN   1    0.22  0.25
    //EUR  4.50   1    1.10
    //USD  4.00  0.91   1
    private static final BigDecimal[][] CurrRates = {
            {BigDecimal.ONE, BigDecimal.valueOf(0.22), BigDecimal.valueOf(0.25)},
            {BigDecimal.valueOf(4.50), BigDecimal.ONE, BigDecimal.valueOf(1.10)},
            {BigDecimal.valueOf(4.00), BigDecimal.valueOf(0.91), BigDecimal.ONE}
    };

    public static BigDecimal convert(BigDecimal amount, Currency from, Currency to) {
        return amount.multiply(CurrRates[from.ordinal()][to.ordinal()]);
    }
}
