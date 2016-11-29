package com.vlucenco.android.finance.core.impls;

import com.sun.xml.internal.ws.server.UnsupportedMediaException;
import com.vlucenco.android.finance.core.interfaces.Storage;
import com.vlucenco.android.finance.core.exceptions.AmountException;
import com.vlucenco.android.finance.core.exceptions.CurrencyException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultStorage implements Storage {

    private String name;
    private Map<Currency, BigDecimal> currencyAmounts = new HashMap<>();
    private List<Currency> currencyList = new ArrayList<>();

    public DefaultStorage(){};

    public DefaultStorage(String name){
        this.name = name;
    };

    public DefaultStorage(Map<Currency, BigDecimal> currencyAmounts) {
        this.currencyAmounts = currencyAmounts;
    }

    public DefaultStorage(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }

    public DefaultStorage(String name, Map<Currency, BigDecimal> currencyAmounts, List<Currency> currencyList) {
        this.name = name;
        this.currencyAmounts = currencyAmounts;
        this.currencyList = currencyList;
    }

    @Override

    public List<Currency> getAvailableCurrencies() {
        return currencyList;
    }

    public void setAvailableCurrencies(List<Currency> availableCurrencies) {
        this.currencyList = availableCurrencies;
    }

    @Override
    public Map<Currency, BigDecimal> getCurrencyAmounts() {
        return currencyAmounts;
    }

    public void setCurrencyAmounts(Map<Currency, BigDecimal> currencyAmounts) {
        this.currencyAmounts = currencyAmounts;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public BigDecimal getAmount(Currency currency) throws CurrencyException {
        checkCurrencyExist(currency);
        return currencyAmounts.get(currency);
    }

    // manual update of amount
    @Override
    public void changeAmount(BigDecimal amount, Currency currency) throws CurrencyException {
        checkCurrencyExist(currency);
        currencyAmounts.put(currency, amount);
    }

    private void checkCurrencyExist(Currency currency) throws CurrencyException {
        if (!currencyAmounts.containsKey(currency)) {
            throw new CurrencyException("Currency " + currency + " not exist");
        }
    }

    // adding money to storage
    @Override
    public void addAmount(BigDecimal amount, Currency currency) throws CurrencyException {
        checkCurrencyExist(currency);
        BigDecimal oldAmount = currencyAmounts.get(currency);
        currencyAmounts.put(currency, oldAmount.add(amount));
    }

    // money withdrawal from storage
    @Override
    public void expenseAmount(BigDecimal amount, Currency currency) throws CurrencyException, AmountException {
        checkCurrencyExist(currency);
        BigDecimal oldAmount = currencyAmounts.get(currency);
        BigDecimal newValue = oldAmount.subtract(amount);
        checkAmount(newValue);
        currencyAmounts.put(currency, newValue);
    }

    private void checkAmount(BigDecimal amount) throws AmountException {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new AmountException("Amount can't be < 0");
        }
    }

    @Override
    public void addCurrency(Currency currency) throws CurrencyException {
        if (currencyAmounts.containsKey(currency)) {
            throw new CurrencyException("Currency already exists");
        }
        currencyList.add(currency);
        currencyAmounts.put(currency, BigDecimal.ZERO);
    }

    @Override
    public void deleteCurrency(Currency currency) throws CurrencyException {
        checkCurrencyExist(currency);
        if (!currencyAmounts.get(currency).equals(BigDecimal.ZERO)) {
            throw new CurrencyException("Can't delete currency with amount");
        }
        currencyAmounts.remove(currency);
        currencyList.remove(currency);
    }

    @Override
    public BigDecimal getApproxAmount(Currency currency) {
        // TODO implement balance calculation with bringing it to one currency
        throw new UnsupportedMediaException("Not implemented");
    }

    @Override
    public Currency getCurrency(String code) {
        for (Currency currency : currencyList) {
            if (currency.getCurrencyCode().equals(code)) {
                return currency;
            }
        }
        return null;
    }
}
