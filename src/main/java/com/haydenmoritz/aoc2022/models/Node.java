package com.haydenmoritz.aoc2022.models;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private List<Node<T>> children = new ArrayList<Node<T>>();
    private Node<T> parent = null;
    private T data = null;

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, Node<T> parent) {
        this.data = data;
        this.parent = parent;
    }

    public Node<T> getRoot() {
        if (this.isRoot()) { return this; }
        return this.getParent().getRoot();
    }

    public boolean containsData(T data) {
        if (this.getData() == data) { return true; }
        int total = this.children.size();
        for (int i = 0; i < total; i++) {
            this.children.get(i).containsData(data);
        }
        return false;
    }

    public ArrayList<T> inorderTraversal(ArrayList<T> files)
    {
        if (this == null) { return files; }

        int total = this.children.size();
        for (int i = 0; i < total; i++) {
            this.children.get(i).inorderTraversal(files);
        }

        files.add(this.data);
        return files;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public Node<T> getParent() {
        return this.parent;
    }

    public Node<T> addChild(T data) {
        Node<T> child = new Node<T>(data);
        child.parent = this;
        this.children.add(child);
        return child;
    }

    public void addChild(Node<T> child) {
        child.parent = this;
        this.children.add(child);
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
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
