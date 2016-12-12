package com.vlucenco.android.finance.core.start;

import com.vlucenco.android.finance.core.dao.impls.SourceDAOImpl;
import com.vlucenco.android.finance.core.decorator.SourceSync;

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
        SourceSync sourceSync = new SourceSync(new SourceDAOImpl());
        sourceSync.getAll();
    }
}
