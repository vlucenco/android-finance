package com.vlucenco.android.finance.core.interfaces;

import java.util.List;

// Allows creation of tree-set structure for any set of objects which implement TreeNode interface
// Custom implementation of "Composite" design pattern
public interface TreeNode {

    String getName();

    long getId(); // каждый элемент дерева должен иметь свой уникальный идентификатор

    long getParentId();

    void add(TreeNode child); // добавить один дочерний элемент

    void remove(TreeNode child); // удалить один дочерний элемент

    List<TreeNode> getChildren(); // дочерних элементов может быть любое количество

    TreeNode getChild(long id); // получение дочернего элемента по id

    TreeNode getParent(); // получение родительского элемента - пригодится в разных ситуациях, например для отчетности по всем узлам дерева

    void setParent(TreeNode parent);	// установка родительского элемента

    boolean hasChildren(); // проверяет, есть ли дочерние элементы
}
