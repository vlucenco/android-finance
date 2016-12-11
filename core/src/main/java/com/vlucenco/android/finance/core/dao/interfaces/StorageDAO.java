package com.vlucenco.android.finance.core.dao.interfaces;

import com.vlucenco.android.finance.core.exceptions.CurrencyException;
import com.vlucenco.android.finance.core.interfaces.Storage;

import java.math.BigDecimal;
import java.util.Currency;

public interface StorageDAO extends CommonDAO<Storage> {

    // boolean is used (instead of void) to make sure that operation was made successfully
    boolean addCurrency(Storage storage, Currency currency) throws CurrencyException;
    boolean deleteCurrency(Storage storage, Currency currency) throws CurrencyException;
    boolean updateAmount(Storage storage, Currency currency, BigDecimal amount);
}
