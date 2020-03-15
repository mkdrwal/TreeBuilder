package dev.mateuszkowalczyk.treebuilder.model;

import java.io.Serializable;

public class Tree implements Serializable {
    private TreeNode structure;

    public Tree() {
        this.structure = new TreeNode();
    }

    public TreeNode getStructure() {
        return structure;
    }

    public void setStructure(TreeNode structure) {
        this.structure = structure;
    }
}
