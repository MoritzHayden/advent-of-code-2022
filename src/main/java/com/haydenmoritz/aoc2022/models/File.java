package com.haydenmoritz.aoc2022.models;

public class File {
    private String name;
    private boolean isDirectory;
    private int size;

    public File(String name, boolean isDirectory, int size) {
        this.name = name;
        this.isDirectory = isDirectory;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsDirectory() {
        return isDirectory;
    }

    public void setIsDirectory(boolean isDirectory) {
        this.isDirectory = isDirectory;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
