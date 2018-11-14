package com.codecool.reader;

import com.codecool.model.Cell;
import com.codecool.model.Sudoku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SudokuReader {

    private CsvReader csvReader;

    final int MAX_SIZE = 9;

    @Autowired
    public SudokuReader(CsvReader csvReader) {
        this.csvReader = csvReader;
    }

    public Sudoku createSudoku(String path) {
        List<Cell> cellList = new ArrayList<>();
        String [][] sudokuArr = csvReader.readFromFile(path);
        for(int i = 0; i < MAX_SIZE; i++) {
            for(int j = 0; j < MAX_SIZE; j++) {
                int value = Integer.valueOf(sudokuArr[i][j]);
                if(value == 0) {
                    cellList.add(new Cell(i,j));
                } else {
                    cellList.add(new Cell(i,j,value));
                }
            }
        }
        return new Sudoku(cellList);

    }
}
