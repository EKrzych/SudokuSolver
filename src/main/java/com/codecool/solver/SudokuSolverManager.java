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

        while(true) {
            Iterator iterator = sudokuSolverList.iterator();
            while (iterator.hasNext()) {
                SudokuSolver sudokuSolver = (SudokuSolver) iterator.next();
                if (sudokuSolver.isSudokuSolved()) {
                    return sudokuSolver.getSudoku();

                } else if (sudokuSolver.isSudokuIncorrect()) {
                    sudokuSolverList.remove(sudokuSolver);

                } else if (sudokuSolver.isMoreThanOnePossibilityForEachCell()) {

                    for (Sudoku sudoku : sudokuSolver.createSudokuList()) {
                        sudokuSolverList.add(new SudokuSolver(sudoku));
                    }
                    sudokuSolverList.remove(sudokuSolver);

                } else if (sudokuSolverList.isEmpty()) {
                    return null;
                }
            }
        }
    }
}
