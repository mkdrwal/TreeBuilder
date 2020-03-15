package dev.mateuszkowalczyk.treebuilder.builder;

import dev.mateuszkowalczyk.treebuilder.entity.Node;
import dev.mateuszkowalczyk.treebuilder.model.Tree;
import dev.mateuszkowalczyk.treebuilder.model.TreeNode;

public class TreeBuilder {
    public TreeBuilder() {
    }

    public Tree buildTree(Node parentNode) {
        Tree tree = new Tree();

        tree.setStructure(this.buildNode(parentNode));

        return tree;
    }

    public TreeNode buildNode(Node node) {
        TreeNode treeNode = new TreeNode(node);

        for (Node childrenNode : node.getChildren()) {
            treeNode.addChildren(this.buildNode(childrenNode));
        }

        return treeNode;
    }
}
