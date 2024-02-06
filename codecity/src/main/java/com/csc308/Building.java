package com.csc308;
public class Building {

    // How sensitive is a building's width to its number of fields?
    static int WIDTH_SCALING = 3;

    static int MIN_WIDTH = 5;
    static int MIN_HEIGHT = 5;

    // How sensitive is a building's height to its number of methods?
    static int HEIGHT_SCALING = 3;

    String className;
    private int methods; // Based on number of methods
    private int fields;  // Based on number of attributes

    // Constructor
    public Building(String className, int fields, int methods) {
        this.className = className;
        this.setMethods(methods);
        this.setFields(fields);
    }

    // Getters
    public String getClassName() {
        return className;
    }

    public int getHeight() {
        return Building.MIN_HEIGHT + Building.HEIGHT_SCALING * this.getMethods();
    }

    public int getWidth() {
        return Building.MIN_WIDTH + Building.WIDTH_SCALING * this.getFields();
    }

    public int getMethods() {
        return methods;
    }

    private void setMethods(int methods) {
        this.methods = methods;
    }

    public int getFields() {
        return fields;
    }

    private void setFields(int fields) {
        this.fields = fields;
    }
}