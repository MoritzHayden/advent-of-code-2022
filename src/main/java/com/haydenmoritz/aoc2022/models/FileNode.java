package com.haydenmoritz.aoc2022.models;

import java.util.ArrayList;
import java.util.List;

public class FileNode {
    //region VARIABLES
    private final List<FileNode> children = new ArrayList<>();
    private FileNode parent = null;
    private File data;
    //endregion

    //region CONSTRUCTORS
    public FileNode(File data) {
        this.data = data;
    }

    public FileNode(File data, FileNode parent) {
        this.data = data;
        this.parent = parent;
    }
    //endregion

    //region GETTERS
    public List<FileNode> getChildren() {
        return children;
    }

    public FileNode getParent() {
        return this.parent;
    }

    public File getData() {
        return data;
    }
    //endregion

    //region SETTERS
    public FileNode addChild(File data) {
        FileNode child = new FileNode(data, this);
        this.children.add(child);
        return child;
    }

    public FileNode setParent(FileNode parent) {
        return this.parent = parent;
    }

    public void removeParent() {
        this.parent = null;
    }

    public void setData(File data) {
        this.data = data;
    }
    //endregion

    //region METHODS
    public Boolean childContainsFile(File file) {
        for (FileNode child : this.getChildren()) {
            if (child.getData().equals(file)) {
                return true;
            }
        }
        return false;
    }

    public FileNode findChild(File file) {
        for (FileNode child : this.getChildren()) {
            if (child.getData().equals(file)) {
                return child;
            }
        }
        return null;
    }

    public FileNode getRoot() {
        if (this.isRoot()) {
            return this;
        }
        return this.parent.getRoot();
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

    public boolean isLeaf() {
        return this.children.size() == 0;
    }
    //endregion
}
