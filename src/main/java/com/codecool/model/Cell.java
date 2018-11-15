package com.codecool.model;

public class Cell {
    private int row;
    private int column;
    private int value;
    private boolean isSet;

    public Cell(int row, int column, int value) {
        this.row = row;
        this.column = column;
        this.value = value;
        this.isSet = true;
    }

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Cell(Cell cell) {
        this.row = cell.getRow();
        this.column = cell.getColumn();
        this.value = cell.getValue();
        this.isSet = cell.isSet();
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getValue() {
        return value;
    }

    public boolean isSet() {
        return isSet;
    }

    public void insertValue(int value) {
        this.value = value;
        this.isSet = true;
    }

}
