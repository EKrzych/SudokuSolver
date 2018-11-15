package com.codecool.solver;

import com.codecool.model.Cell;
import com.codecool.model.Sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class SudokuSolver implements Runnable {
    private Sudoku sudoku;



    public SudokuSolver(Sudoku sudoku) {
        this.sudoku = sudoku;
        new Thread(this).start();
    }

    public SudokuSolver() {
    }

    @Override
    public void run() {
        while(solve()) {
        }

    }

    public boolean solve() {
        boolean isChanged = false;
        for (Cell cell : getCellsWithoutValue()) {
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

    public synchronized boolean setValueIfPossible(Cell cell) {
       List<Integer> possibilities = checkPossibilities(cell);
       if(possibilities.size() == 1) {
           cell.insertValue(possibilities.get(0));
           return true;
       }
       return false;
    }

    public synchronized boolean isSudokuSolved() {
        return this.sudoku.getCellList().stream()
                .filter(n -> !n.isSet())
                .collect(Collectors.toList())
                .isEmpty();
    }

    public synchronized boolean isSudokuIncorrect() {
        for( Cell cell : getCellsWithoutValue()) {
            if(checkPossibilities(cell).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public Sudoku getSudoku() {
        return sudoku;
    }

    private List<Cell> getCellsWithoutValue() {
        return sudoku.getCellList().stream()
                .filter(n -> !n.isSet())
                .collect(Collectors.toList());
    }

    public synchronized boolean isMoreThanOnePossibilityForEachCell() {
        if(getCellsWithoutValue().isEmpty()) {
            return false;
        }
        for(Cell cell : getCellsWithoutValue()) {

            if(checkPossibilities(cell).size() <= 1) {
                return false;
            }
        }

        return true;
    }

    private Cell getCellWithMinPossibilities() {
        Cell cellWithMinPossibilities = null;
        int minCount = 9;
        for(Cell cell : getCellsWithoutValue()) {
            int possibilitiesCount = checkPossibilities(cell).size();
            if (possibilitiesCount <= minCount) {
                cellWithMinPossibilities = cell;
                minCount = possibilitiesCount;
            }
        }

        return cellWithMinPossibilities;
    }

    public List<Sudoku> createSudokuList() {
        List<Sudoku> sudokuList = new ArrayList<>();
        Cell cell = getCellWithMinPossibilities();
        for(int value : checkPossibilities(cell)) {

            Sudoku newSudoku = sudoku.deepCopy();
            newSudoku.getCell(cell.getRow(), cell.getColumn()).insertValue(value);
            sudokuList.add(newSudoku);

        }
        return sudokuList;
    }
}
