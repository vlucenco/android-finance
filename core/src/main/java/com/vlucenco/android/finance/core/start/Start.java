package com.vlucenco.android.finance.core.start;

import com.vlucenco.android.finance.core.dao.impls.StorageDAOImpl;

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
        new StorageDAOImpl().getAll();
    }
}
