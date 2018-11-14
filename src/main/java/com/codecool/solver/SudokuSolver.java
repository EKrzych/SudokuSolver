package com.codecool.solver;

import com.codecool.model.Cell;
import com.codecool.model.Sudoku;

import java.util.List;
import java.util.stream.Collectors;

public class SudokuSolver implements Runnable {
    private Sudoku sudoku;
    Thread thread;

    private boolean isCorrect = true;

    public SudokuSolver(Sudoku sudoku) {
        this.sudoku = sudoku;
        thread = new Thread(this);
        thread.start();
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

    public boolean setValueIfPossible(Cell cell) {
       List<Integer> possibilities = checkPossibilities(cell);
       if(possibilities.size() == 1) {
           cell.insertValue(possibilities.get(0));
           return true;
       }
       return false;
    }

    public boolean isSudokuSolved() {
        return this.sudoku.getCellList().stream()
                .filter(n -> !n.isSet())
                .collect(Collectors.toList())
                .isEmpty();
    }



    @Override
    public void run() {

    }
}
