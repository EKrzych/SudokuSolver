package com.codecool.model;

import java.util.List;

public class Sudoku {
    private List<Cell> cellList;

    public Sudoku(List<Cell> cellList) {
        this.cellList = cellList;
    }

    public List<Cell> getCellList() {
        return cellList;
    }

    public String toString() {
        StringBuilder sudokuBuilder = new StringBuilder();
        for (int i = 0; i < 81; i++) {
            if (i != 0 && i % 27 == 0) {
                sudokuBuilder.append("\n - - - - - - - - - - - ");
            }
            if (i % 9 == 0) {
                sudokuBuilder.append("\n");
            }
            if ( i%9 != 0 && i % 3 == 0) {
                sudokuBuilder.append(" | ");
            } else {
                sudokuBuilder.append(" ");
            }
            sudokuBuilder.append(cellList.get(i).getValue());
        }
        return sudokuBuilder.toString();
    }
}
