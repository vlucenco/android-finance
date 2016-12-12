package com.vlucenco.android.finance.core.impls;

import com.vlucenco.android.finance.core.abstracts.AbstractTreeNode;
import com.vlucenco.android.finance.core.interfaces.Source;
import com.vlucenco.android.finance.core.interfaces.TreeNode;
import com.vlucenco.android.finance.core.enums.OperationType;

import java.util.List;

public class DefaultSource extends AbstractTreeNode implements Source {

    private OperationType operationType;

    public DefaultSource() {
    }

    public DefaultSource(String name) {
        super(name);
    }

    public DefaultSource(List<TreeNode> children) {
        super(children);
    }

    public DefaultSource(String name, long id) {
        super(name, id);
    }

    public DefaultSource(long id, List<TreeNode> children, TreeNode parent, String name) {
        super(id, children, parent, name);
    }

    public DefaultSource(String name, long id, OperationType operationType) {
        super(name, id);
        this.operationType = operationType;
    }

    @Override
    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    @Override
    public void add(TreeNode child) {

        //TODO apply a pattern
        // Set child's operationType to be the same as parent's one
        if (child instanceof DefaultSource) {
            ((DefaultSource)child).setOperationType(operationType);
        }
        super.add(child);
    }
}
