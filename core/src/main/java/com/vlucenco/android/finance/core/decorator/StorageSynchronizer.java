package com.vlucenco.android.finance.core.decorator;

import com.vlucenco.android.finance.core.dao.interfaces.StorageDAO;
import com.vlucenco.android.finance.core.exceptions.CurrencyException;
import com.vlucenco.android.finance.core.interfaces.Storage;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

// synchronizes all operations between collection objects and db
// Decorator pattern (modified)
public class StorageSynchronizer implements StorageDAO {

    private StorageDAO storageDAO;
    private List<Storage> storageList;

    public StorageSynchronizer(StorageDAO storageDAO) {
        this.storageDAO = storageDAO;
        init();
    }

    private void init() {
        storageList = storageDAO.getAll();
    }

    @Override
    public boolean addCurrency(Storage storage, Currency currency) throws CurrencyException {
        if (storageDAO.addCurrency(storage, currency)) {
            storage.addCurrency(currency);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCurrency(Storage storage, Currency currency) throws CurrencyException {
        if (storageDAO.deleteCurrency(storage, currency)) {
            storage.deleteCurrency(currency);
            return true;
        }
        return false;
    }

    // TODO при обновлении происходит наоборот - сначала обновляется объект в коллекции, потом уже в БД - продумать, как сделать, чтобы можно было откатить изменения в случае ошибки при запросе к БД
    @Override
    public boolean updateAmount(Storage storage, Currency currency, BigDecimal amount) {
        if (storageDAO.updateAmount(storage, currency, amount)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Storage storage) {
        if (storageDAO.update(storage)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Storage storage) {
        //TODO add necessary exceptions
        if (storageDAO.delete(storage)) {
            storageList.remove(storage);
            return true;
        }
        return false;
    }

    @Override
    public List<Storage> getAll() {
        if (storageList == null) {
            storageList = storageDAO.getAll();
        }
        return storageList;
    }

    @Override
    public Storage get(long id) {
        return storageDAO.get(id);
    }

}
