package dev.mateuszkowalczyk.treebuilder.model;

public class TreeNodeSchema {
    private Long parentId;
    private int value;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
