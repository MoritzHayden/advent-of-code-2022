package com.haydenmoritz.aoc2022.models;

public class File {
    // regionVARIABLES
    private String name;
    private int size;
    //endregion

    //region CONSTRUCTOR
    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }
    //endregion

    //region GETTERS
    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }
    //endregion

    //region METHODS
    public boolean isDirectory() {
        return size == 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.isDirectory()) {
            sb.append(this.name).append(" (dir)");
        } else {
            sb.append(this.name).append(" (file, size=");
            sb.append(this.size).append(")");
        }
        return sb.toString();
    }

    public boolean equals(File file) {
        return this.name.equals(file.name) && this.size == file.size;
    }
    //endregion
}
