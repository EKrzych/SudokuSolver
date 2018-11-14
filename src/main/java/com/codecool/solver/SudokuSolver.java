package com.codecool.solver;

import com.codecool.model.Cell;
import com.codecool.model.Sudoku;

import java.util.List;

public class SudokuSolver {
    private Sudoku sudoku;
    private boolean isCorrect = true;

    public SudokuSolver(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    private List<Integer> checkPossibilities(Cell cell) {
        return merge(merge(possibleInRow(cell), possibleInColumn(cell)), possibleInSquare(cell));
    }

    private List<Integer> merge(List<Integer> list, List<Integer> secondList) {
        return null;
    }

    private List<Integer> possibleInRow(Cell cell) {
        return null;
    }

    private List<Integer> possibleInColumn(Cell cell) {
        return null;
    }

    private List<Integer> possibleInSquare(Cell cell) {
        return null;
    }


}
