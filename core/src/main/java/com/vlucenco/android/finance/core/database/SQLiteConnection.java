package com.vlucenco.android.finance.core.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLiteConnection {

    private static Connection con;

    public static Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC").newInstance();

            // creates db connection using the url path
            String url = "jdbc:sqlite:D:\\Dev\\repos\\money.db";

            if (con == null) con = DriverManager.getConnection(url);

            con.createStatement().execute("PRAGMA foreign_keys = ON");
            return con;
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
