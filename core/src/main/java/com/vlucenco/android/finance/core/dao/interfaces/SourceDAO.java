package com.vlucenco.android.finance.core.dao.interfaces;

import com.vlucenco.android.finance.core.enums.OperationType;
import com.vlucenco.android.finance.core.interfaces.Source;

import java.util.List;

public interface SourceDAO extends CommonDAO<Source> {

    List<Source> getList(OperationType operationType); // get list of all root elements for an operation type
}
