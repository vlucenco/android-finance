package com.vlucenco.android.finance.core.dao.interfaces;

import com.vlucenco.android.finance.core.interfaces.Storage;

import java.math.BigDecimal;
import java.util.Currency;

public interface StorageDAO extends CommonDAO<Storage> {

    // boolean is used (instead of void) to make sure that operation was made successfully
    boolean addCurrency(Storage storage, Currency currency);
    boolean deleteCurrency(Storage storage, Currency currency);
    boolean updateAmount(Storage storage, BigDecimal amount);
}
