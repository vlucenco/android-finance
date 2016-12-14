package com.vlucenco.android.finance.core.decorator;

import com.vlucenco.android.finance.core.dao.interfaces.SourceDAO;
import com.vlucenco.android.finance.core.enums.OperationType;
import com.vlucenco.android.finance.core.interfaces.Source;

import java.util.List;

public class SourceSync implements SourceDAO {

    private SourceDAO sourceDAO;
    private List<Source> sourceList;

    public SourceSync(SourceDAO sourceDAO) {
        this.sourceDAO = sourceDAO;
    }

    @Override
    public List<Source> getAll() {
        if (sourceList == null) {
            sourceList = sourceDAO.getAll();
        }
        return sourceList;
    }

    @Override
    public Source get(long id) {
        return null;
    }

    @Override
    public boolean update(Source source) {
        if (sourceDAO.update(source)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Source source) {
        // TODO добавить нужные Exceptions
        if (sourceDAO.delete(source)) {
            sourceList.remove(source);
            sourceDAO.getList(source.getOperationType()).remove(source);
            return true;
        }
        return false;
    }

    @Override
    public List<Source> getList(OperationType operationType) {
        return sourceDAO.getList(operationType);
    }
}
