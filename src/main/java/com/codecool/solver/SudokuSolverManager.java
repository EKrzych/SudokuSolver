package com.codecool.solver;

import com.codecool.model.Sudoku;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class SudokuSolverManager {


    public SudokuSolverManager() {
    }

    public Sudoku findSolution(Sudoku sudokuToSolve) {
        List <SudokuSolver>  sudokuSolverList = new ArrayList<>();
        sudokuSolverList.add(new SudokuSolver(sudokuToSolve));
        List <SudokuSolver> sudokuSolversToAdd = new ArrayList<>();

        while(true) {
            sudokuSolverList.addAll(sudokuSolversToAdd);
            sudokuSolversToAdd = new ArrayList<>();

            Iterator <SudokuSolver> iterator = sudokuSolverList.iterator();

            while (iterator.hasNext()) {
                SudokuSolver sudokuSolver = iterator.next();
                if (sudokuSolver.isSudokuSolved()) {
                    return sudokuSolver.getSudoku();

                } else if (sudokuSolver.isSudokuIncorrect()) {
                    iterator.remove();

                } else if (sudokuSolver.isMoreThanOnePossibilityForEachCell()) {

                    for (Sudoku sudoku : sudokuSolver.createSudokuList()) {
                        sudokuSolversToAdd.add(new SudokuSolver(sudoku));
                    }
                    iterator.remove();

                } else if (sudokuSolverList.isEmpty()) {
                    return null;
                }
            }
        }
    }
}
