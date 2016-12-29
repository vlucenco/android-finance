package com.vlucenco.android.finance.core.start;

import com.vlucenco.android.finance.core.dao.impls.SourceDAOImpl;
import com.vlucenco.android.finance.core.dao.impls.StorageDAOImpl;
import com.vlucenco.android.finance.core.decorator.SourceSync;
import com.vlucenco.android.finance.core.decorator.StorageSync;
import com.vlucenco.android.finance.core.exceptions.CurrencyException;
import com.vlucenco.android.finance.core.interfaces.Source;
import com.vlucenco.android.finance.core.interfaces.Storage;

import java.util.Currency;

public class Start {

    public static void main(String[] args) {

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
//
//        StorageSync storageSync = new StorageSync(new StorageDAOImpl());
//        DefaultStorage tmpStorage = (DefaultStorage) storageSync.getAll().get(1).getChildren().get(0);
//
//        try {
//            storageSync.addCurrency(tmpStorage, Currency.getInstance("USD"));
//            System.out.println("storageSync.getAll() = " + storageSync.getAll());
//        } catch (CurrencyException e) {
//            e.printStackTrace();
//        }

//        SourceDAOImpl impl = new SourceDAOImpl();
//        impl.getAll();
//        System.out.println("impl.get(3) = " + impl.get(3));
//        impl.getList(OperationType.OUTCOME);

        StorageSync storageSync = new StorageSync(new StorageDAOImpl());
        Storage s = storageSync.get(9);

        try {
            storageSync.addCurrency(s, Currency.getInstance("USD"));
        } catch (CurrencyException e) {
            e.printStackTrace();
        }

    }
}
