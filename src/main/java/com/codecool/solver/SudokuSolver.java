package com.codecool.solver;

import com.codecool.model.Cell;
import com.codecool.model.Sudoku;

import java.util.ArrayList;
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
        return possible(getValuesFromRow(cell.getRow()));
    }

    private List<Integer> possible(List<Integer> neighboringValues) {
        List<Integer> possibilities = new ArrayList<>();
        for (int i = 1; i <= 9 ; i++) {
            if (!neighboringValues.contains(i)) {
                possibilities.add(i);
            }
        }
        return possibilities;
    }

    private List<Integer> getValuesFromRow(int row) {
        return sudoku.getCellList().stream()
                .filter(cell -> cell.getRow() == row)
                .filter(Cell::isSet)
                .map(Cell::getValue)
                .collect(Collectors.toList());
    }

    private List<Integer> possibleInColumn(Cell cell) {

        return null;
    }

    private List<Integer> possibleInSquare(Cell cell) {
        return null;
    }


}
