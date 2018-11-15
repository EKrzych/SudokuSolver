package com.codecool.solver;

import com.codecool.model.Cell;
import com.codecool.model.Sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class SudokuSolver implements Runnable {
    private Sudoku sudoku;
    Thread thread;
    boolean stopped = false;

    private boolean isCorrect = true;

    public SudokuSolver(Sudoku sudoku) {
        this.sudoku = sudoku;
        thread = new Thread(this);
        thread.start();
    }

    public SudokuSolver() {
    }

    @Override
    public void run() {
        while(solve()) {
//            synchronized (this) {
//                if(stopped) {
//                    break;
//                }
//            }
            System.out.println("Im in sudoku solver");
        }

    }

    public boolean solve() {
        boolean isChanged = false;
        for (Cell cell : sudoku.getCellList()) {
            if (setValueIfPossible(cell)) {
                isChanged = true;
            }
        }
        return isChanged;
    }

    List<Integer> checkPossibilities(Cell cell) {
        return intersect(intersect(possibleInRow(cell), possibleInColumn(cell)), possibleInSquare(cell));
    }

    private List<Integer> intersect(List<Integer> list, List<Integer> secondList) {
        return list.stream()
                .filter(secondList::contains)
                .collect(Collectors.toList());
    }

    List<Integer> possibleInRow(Cell cell) {
        return possible(getValuesFromRow(cell.getRow()));
    }

    List<Integer> possibleInColumn(Cell cell) {
        return possible(getValuesFromColumn(cell.getColumn()));
    }

    List<Integer> possibleInSquare(Cell cell) {
        return possible(getValuesFromSquare(cell.getRow(), cell.getColumn()));
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

    private List<Integer> getValuesFromColumn(int column) {
        return sudoku.getCellList().stream()
                .filter(cell -> cell.getColumn() == column)
                .filter(Cell::isSet)
                .map(Cell::getValue)
                .collect(Collectors.toList());
    }

    private List<Integer> getValuesFromSquare(int row, int column) {
        List<Integer> rows = getSquareCoordinates(row);
        List<Integer> columns = getSquareCoordinates(column);
        return sudoku.getCellList().stream()
                .filter(cell ->
                        columns.contains(cell.getColumn())
                                && rows.contains(cell.getRow())
                )
                .filter(Cell::isSet)
                .map(Cell::getValue)
                .collect(Collectors.toList());
    }

    private List<Integer> getSquareCoordinates(int coordinate) {
        int shift = coordinate % 3;
        return Arrays.asList(coordinate - shift, coordinate + 1 - shift, coordinate + 2 - shift);
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

    public boolean isSudokuIncorrect() {
        for( Cell cell : sudoku.getCellList()) {
            if(!cell.isSet() && checkPossibilities(cell).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private synchronized void stopThisThread() {
        stopped = true;
        notify();
    }

    public Sudoku getSudoku() {
        return sudoku;
    }

    public boolean isMoreThanOnePossibilityForEachCell() {
        boolean isisMoreThanOnePossibility = true;
        for(Cell cell : sudoku.getCellList()) {

            if(!cell.isSet() && checkPossibilities(cell).size() <= 1) {

                isisMoreThanOnePossibility = false;
            }
        }
        return isisMoreThanOnePossibility;
    }

    public List<Sudoku> createSudokuList() {
        return new ArrayList<>();
    }
}
