package com.codecool.solver;

import com.codecool.model.Cell;
import com.codecool.model.Sudoku;

import java.util.List;
import java.util.stream.Collectors;

public class SudokuSolver {
    private Sudoku sudoku;
    private boolean isCorrect = true;

    public SudokuSolver(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    private List<Integer> checkPossibilities(Cell cell) {
        return intersect(intersect(possibleInRow(cell), possibleInColumn(cell)), possibleInSquare(cell));
    }

    private List<Integer> intersect(List<Integer> list, final List<Integer> secondList) {
        return list.stream()
                .filter(secondList::contains)
                .collect(Collectors.toList());
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
