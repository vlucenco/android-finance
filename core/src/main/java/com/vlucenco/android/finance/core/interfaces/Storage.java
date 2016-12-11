package com.vlucenco.android.finance.core.interfaces;

import com.vlucenco.android.finance.core.exceptions.AmountException;
import com.vlucenco.android.finance.core.exceptions.CurrencyException;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Map;

// TODO Change BigDecimal type to a ready made class for working with Money
public interface Storage extends TreeNode {

    // getting the balance
    Map<Currency, BigDecimal> getCurrencyAmounts(); // amount for each currency in storage
    BigDecimal getAmount(Currency currency) throws CurrencyException; // amount of particular currency
    BigDecimal getApproxAmount(Currency currency); // approximate amount of all the money converted into a currency

    // changing the balance
    void changeAmount(BigDecimal amount, Currency currency) throws CurrencyException;
    void addAmount(BigDecimal amount, Currency currency) throws CurrencyException;
    void expenseAmount(BigDecimal amount, Currency currency) throws CurrencyException, AmountException;

    // work with currency
    void addCurrency(Currency currency) throws CurrencyException;
    void deleteCurrency(Currency currency) throws CurrencyException;
    Currency getCurrency(String code);
    List<Currency> getAvailableCurrencies();

}
