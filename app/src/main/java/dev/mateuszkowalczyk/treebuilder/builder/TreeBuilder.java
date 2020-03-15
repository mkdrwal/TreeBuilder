package dev.mateuszkowalczyk.treebuilder.builder;

import dev.mateuszkowalczyk.treebuilder.entity.Node;
import dev.mateuszkowalczyk.treebuilder.model.Tree;
import dev.mateuszkowalczyk.treebuilder.model.TreeNode;

public class TreeBuilder {

    private Node node;
    private Tree tree;

    public TreeBuilder(Node node) {
        this.node = node;
    }

    public Tree buildTree() {
        this.tree = new Tree();

        tree.setStructure(this.buildNode(this.node));

        return tree;
    }

    private TreeNode buildNode(Node node) {
        TreeNode treeNode = new TreeNode(node);

        for (Node childrenNode : node.getChildren()) {
            treeNode.addChildren(this.buildNode(childrenNode));
        }

        return treeNode;
    }
}
