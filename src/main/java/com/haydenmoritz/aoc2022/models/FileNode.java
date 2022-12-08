package com.haydenmoritz.aoc2022.models;

import java.util.ArrayList;
import java.util.List;

public class FileNode {
    private final List<FileNode> children = new ArrayList<>();
    private FileNode parent = null;
    private File data;

    public FileNode(File data) {
        this.data = data;
    }

    public FileNode(File data, FileNode parent) {
        this.data = data;
        this.parent = parent;
    }

    public FileNode getRoot() {
        if (this.isRoot()) { return this; }
        return this.parent.getRoot();
    }

    public boolean containsDataOLD(File data) {
        if (this.data.equals(data)) { return true; }
        int total = this.children.size();
        for (int i = 0; i < total; i++) {
            this.children.get(i).containsDataOLD(data);
        }
        return false;
    }

    public boolean containsData(File data) {
        if (this.data.equals(data)) { return true; }

        Boolean containsData = false;
        for (FileNode it : this.children) {
            containsData = it.containsData(data);
        }

        return containsData;
    }

    public int countOccurrences(File data) {
        if (this.data.equals(data)) { return 1; }

        int occurrences = 0;
        for (FileNode it : this.children) {
            occurrences = Math.max(occurrences, it.countOccurrences(data));
        }

        return occurrences + 1;
    }

    public ArrayList<File> inorderTraversal(ArrayList<File> files) {
        if (this == null) { return files; }

        int total = this.children.size();
        for (int i = 0; i < total; i++) {
            this.children.get(i).inorderTraversal(files);
        }

        files.add(this.data);
        return files;
    }

    public int getDepth() {
        if (this.children.size() == 0) { return 0; }

        int maxDepth = 0;
        for (FileNode it : this.children) {
            maxDepth = Math.max(maxDepth, it.getDepth());
        }

        return maxDepth + 1;
    }

    public ArrayList<File> searchDFS(ArrayList<File> files) {
        // Base case
        if (this.children.size() == 0) {
            return files;
        }

        for (FileNode it : this.children) {
            files.add(it.data);
            it.searchDFS(files);
        }

        return files;
    }

    public List<FileNode> getChildren() {
        return children;
    }

    public FileNode getParent() {
        return this.parent;
    }

    public FileNode addChild(File data) {
        FileNode child = new FileNode(data);
        child.parent = this;
        this.children.add(child);
        return child;
    }

    public void addChild(FileNode child) {
        child.parent = this;
        this.children.add(child);
    }

    public File getData() {
        return this.data;
    }

    public void setData(File data) {
        this.data = data;
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

    public boolean isLeaf() {
        return this.children.size() == 0;
    }

    public void removeParent() {
        this.parent = null;
    }
}
