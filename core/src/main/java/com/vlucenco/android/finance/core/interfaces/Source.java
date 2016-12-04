package com.vlucenco.android.finance.core.interfaces;

import com.vlucenco.android.finance.core.objects.OperationType;

public interface Source {

    String getName();

    long getId();

    OperationType getOperationType();
}
