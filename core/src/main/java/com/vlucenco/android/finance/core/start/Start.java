package com.vlucenco.android.finance.core.start;

import com.vlucenco.android.finance.core.dao.impls.StorageDAOImpl;
import com.vlucenco.android.finance.core.decorator.StorageSynchronizer;
import com.vlucenco.android.finance.core.exceptions.CurrencyException;
import com.vlucenco.android.finance.core.impls.DefaultStorage;

import java.util.Currency;

public class Start {

    public static void main(String[] args) {
//
//        try (Statement stmt = SQLiteConnection.getConnection().createStatement();
//             ResultSet rs = stmt.executeQuery("select * from storage")) {
//
//            while (rs.next()) {
//                System.out.println(rs.getString("name"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        new StorageDAOImpl().getAll();

        StorageSynchronizer storageSync = new StorageSynchronizer(new StorageDAOImpl());
        DefaultStorage tmpStorage = (DefaultStorage) storageSync.getAll().get(1).getChildren().get(0);

        try {
            storageSync.addCurrency(tmpStorage, Currency.getInstance("USD"));
            System.out.println("storageSync.getAll() = " + storageSync.getAll());
        } catch (CurrencyException e) {
            e.printStackTrace();
        }
    }
}
