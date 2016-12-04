package com.vlucenco.android.finance.core.interfaces;

import java.util.List;

// Allows creation of tree-set structure for any set of objects which implement TreeNode interface
// Custom implementation of "Composite" design pattern
public interface TreeNode {

    String getName();

    long getId();

    void add(TreeNode child);

    void remove(TreeNode child);

    List<TreeNode> getChildren();

    TreeNode getChild(long id);

    TreeNode getParent();

    void setParent(TreeNode parent);
}
