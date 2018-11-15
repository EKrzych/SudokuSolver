package com.codecool.solver;

import com.codecool.reader.SudokuReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SudokuSolverManagerTest {
    @Autowired
    private SudokuReader sudokuReader;
    @Autowired
    private SudokuSolverManager sudokuSolverManager;

    @Test
    public void shouldFindSolution_easyLevel() {
        String expectedSudoku =
                " 2 6 1 | 3 7 5 | 8 9 4\n" +
                " 5 3 7 | 8 9 4 | 1 6 2\n" +
                " 9 4 8 | 2 1 6 | 3 5 7\n" +
                " - - - - - - - - - - - \n" +
                " 6 9 4 | 7 5 1 | 2 3 8\n" +
                " 8 2 5 | 9 4 3 | 6 7 1\n" +
                " 7 1 3 | 6 2 8 | 9 4 5\n" +
                " - - - - - - - - - - - \n" +
                " 3 5 6 | 4 8 2 | 7 1 9\n" +
                " 4 8 9 | 1 6 7 | 5 2 3\n" +
                " 1 7 2 | 5 3 9 | 4 8 6";
        String result = sudokuSolverManager.findSolution(
                sudokuReader.createSudoku("src/test/resources/sudoku.csv")
        )
                .toString();

        assertEquals(expectedSudoku, result);
    }
    @Test
    public void shouldFindSolution_hardestLevel() {
        String expectedSudoku =
                " 8 1 2 | 7 5 3 | 6 4 9\n" +
                " 9 4 3 | 6 8 2 | 1 7 5\n" +
                " 6 7 5 | 4 9 1 | 2 8 3\n" +
                " - - - - - - - - - - - \n" +
                " 1 5 4 | 2 3 7 | 8 9 6\n" +
                " 3 6 9 | 8 4 5 | 7 2 1\n" +
                " 2 8 7 | 1 6 9 | 5 3 4\n" +
                " - - - - - - - - - - - \n" +
                " 5 2 1 | 9 7 4 | 3 6 8\n" +
                " 4 3 8 | 5 2 6 | 9 1 7\n" +
                " 7 9 6 | 3 1 8 | 4 5 2";
        String result = sudokuSolverManager.findSolution(
                sudokuReader.createSudoku("src/test/resources/hardest_sudoku.csv")
        )
                .toString();

        assertEquals(expectedSudoku, result);
    }
}