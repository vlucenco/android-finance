package com.vlucenco.android.finance.core.utils;

import com.vlucenco.android.finance.core.interfaces.TreeNode;

import java.util.List;

public class TreeUtils<T extends TreeNode> {

    // встраивает новый элемент в нужное место дерева: суть в том, что нужно найти родительский элемент для объекта newNode
    public void addToTree(long parentId, T newNode, List<T> storageList) {
        if (parentId!=0){
            for (T currentNode: storageList) {// искать сначала во всех корневых объектах
                if (currentNode.getId()==parentId){
                    currentNode.add(newNode);
                    return;
                }else {// если среди корневых элементов не найдены родители
                    TreeNode node = recursiveSearch(parentId, currentNode);// проходим по всем уровням дочерних элементов
                    if (node!=null){// если нашли среди дочерних элементов
                        node.add(newNode);
                        return;
                    }
                }
            }
        }
        storageList.add(newNode);// если не найден родительский элемент - добавляем как корневой
    }

    // рекурсивно проходит по всем дочерним элементам
    private TreeNode recursiveSearch(long parentId, TreeNode child){
        for (TreeNode node: child.getChildren()) {
            if (node.getId() == parentId){
                return node;
            }else if (node.hasChildren()){// если у текущего узла есть свои дочерние элемента - проходим и по ним
                recursiveSearch(parentId, node);
            }
        }
        return null;
    }
}
