package com.codecool.solver;

import com.codecool.model.Sudoku;
import com.codecool.reader.SudokuReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SudokuSolverTest {


    @Autowired
    private SudokuReader sudokuReader;

    @Test
    public void shouldRecogniseNotSolvedSudoku() {
        Sudoku sudoku = sudokuReader.createSudoku("src/test/resources/sudoku.csv");
        SudokuSolver sudokuSolver = new SudokuSolver(sudoku);

        assertFalse(sudokuSolver.isSudokuSolved());

    }


}