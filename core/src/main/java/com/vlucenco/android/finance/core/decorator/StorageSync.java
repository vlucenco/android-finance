package com.vlucenco.android.finance.core.decorator;

import com.vlucenco.android.finance.core.dao.interfaces.StorageDAO;
import com.vlucenco.android.finance.core.enums.OperationType;
import com.vlucenco.android.finance.core.exceptions.CurrencyException;
import com.vlucenco.android.finance.core.interfaces.Storage;
import com.vlucenco.android.finance.core.utils.TreeUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// synchronizes all operations between collection objects and db
// Decorator pattern (modified)
public class StorageSync implements StorageDAO {

    private TreeUtils<Storage> treeUtils = new TreeUtils();


    private List<Storage> treeList = new ArrayList<>();
    private Map<Long, Storage> identityMap = new HashMap<>();

    private StorageDAO storageDAO;

    public StorageSync(StorageDAO storageDAO) {
        this.storageDAO = storageDAO;
        init();
    }

    private void init() {
        List<Storage> storageList = storageDAO.getAll();

        for (Storage s : storageList) {
            identityMap.put(s.getId(), s);
            treeUtils.addToTree(s.getParentId(), s, treeList);
        }
    }

    public StorageDAO getStorageDAO() {
        return storageDAO;
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
            identityMap.remove(storage.getId());
            if (storage.getParent() != null) {
                storage.getParent().remove(storage);
            } else {
                treeList.remove(storage);
            }
            return true;
        }
        return false;
    }

    @Override
    public List<Storage> getAll() {
        return treeList;
    }

    @Override
    public Storage get(long id) {
        return identityMap.get(id);
    }

}
