package com.vlucenco.android.finance.core;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Map;

// TODO Change BigDecimal type to a ready made class for working with Money
public interface Storage {

    String getName();

    // getting the balance
    Map<Currency, BigDecimal> getCurrencyAmounts(); // amount for each currency in storage
    BigDecimal getAmount(Currency currency); // amount of particular currency
    BigDecimal getApproxAmount(Currency currency); // approximate amount of all the money converted into a currency

    // changing the balance
    void changeAmount(BigDecimal amount, Currency currency);
    void addAmount(BigDecimal amount, Currency currency);
    void expenseAmount(BigDecimal amount, Currency currency);

    // work with currency
    void addCurrency(Currency currency);
    void deleteCurrency(Currency currency);
    Currency getCurrency(String code);
    List<Currency> getAvailableCurrencies();

}
