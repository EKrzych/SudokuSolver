package com.codecool;

import com.codecool.reader.SudokuReader;
import com.codecool.solver.SudokuSolver;
import com.codecool.solver.SudokuSolverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner {
    @Autowired
    private SudokuReader sudokuReader;

    @Autowired
    private SudokuSolverManager sudokuSolverManager;

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);

    }


    @Override
    public void run(String... strings) throws Exception {
        System.out.println(sudokuSolverManager.findSolution(sudokuReader.createSudoku("src/main/resources/sudoku.csv")).toString());

    }
}
