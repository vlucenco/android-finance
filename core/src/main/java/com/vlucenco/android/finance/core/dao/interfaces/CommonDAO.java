package com.vlucenco.android.finance.core.dao.interfaces;

import java.util.List;

// describes common operations with DB for all objects
public interface CommonDAO<T> {

    List<T> getAll();
    T get (long id);

    // boolean is used (instead of void) to make sure that operation was made successfully
    boolean update(T storage);
    boolean delete(T storage);
}
