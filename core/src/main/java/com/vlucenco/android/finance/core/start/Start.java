package com.vlucenco.android.finance.core.start;

import com.vlucenco.android.finance.core.database.SQLiteConnection;

import java.sql.ResultSet;
import java.sql.Statement;

public class Start {

    public static void main(String[] args) {

        try (Statement stmt = SQLiteConnection.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("select * from storage")) {

            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
