package com.vlucenco.android.finance.core.interfaces;

import com.vlucenco.android.finance.core.enums.OperationType;

public interface Source extends TreeNode{

    OperationType getOperationType();
}
