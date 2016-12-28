package com.vlucenco.android.finance.core.start;

import com.vlucenco.android.finance.core.dao.impls.SourceDAOImpl;
import com.vlucenco.android.finance.core.decorator.SourceSync;
import com.vlucenco.android.finance.core.interfaces.Source;

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

        SourceSync sourceSync = new SourceSync(new SourceDAOImpl());
        sourceSync.getAll();

//        Source s = sourceSync.get(1);
//        sourceSync.delete(s);

        Source s2 = sourceSync.get(3);
        sourceSync.delete(s2);
    }
}
