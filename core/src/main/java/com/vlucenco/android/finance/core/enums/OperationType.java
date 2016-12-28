package com.vlucenco.android.finance.core.enums;

import java.util.HashMap;
import java.util.Map;

public enum OperationType {

    INCOME(1), OUTCOME(2), TRANSFER(3), CONVERT(4); // id numeration the same as in db table

    private static Map<Integer, OperationType> map = new HashMap<>();

    static {
        for (OperationType oper : OperationType.values()) {
            map.put(oper.getId(), oper);
        }
    }

    private Integer id;

    OperationType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static OperationType getType(int id) {
        return map.get(id);
    }

}
