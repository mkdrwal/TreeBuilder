package dev.mateuszkowalczyk.treebuilder.model;

import dev.mateuszkowalczyk.treebuilder.entity.Node;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private Long id;
    private int value;
    private TreeNode parent;
    private List<TreeNode> children = new ArrayList<>();

    public TreeNode() {
        this.value = 0;
    }

    public TreeNode(Long id, int value) {
        this.id = id;
        this.value = value;
    }

    public TreeNode(Long id, int value, TreeNode parent) {
        this.value = value;
        this.parent = parent;
    }

    public TreeNode(Node node) {
        this.id = node.getId();
        this.value = node.getValue();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public void addChildren(TreeNode node) {
        node.setParent(this);
        this.children.add(node);
    }
}
