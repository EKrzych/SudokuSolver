package com.codecool.solver;

import com.codecool.model.Cell;
import com.codecool.model.Sudoku;
import com.codecool.reader.SudokuReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SudokuSolverTest {


    @Autowired
    private SudokuReader sudokuReader;

    @Test
    public void shouldRecogniseSolvedSudoku() {
        Sudoku sudoku = sudokuReader.createSudoku("src/test/resources/sudoku.csv");
        SudokuSolver sudokuSolver = new SudokuSolver(sudoku);

        assertFalse(sudokuSolver.isSudokuSolved());
    }

    @Test
    public void shouldReturnPossibleValuesInRow() {
        Sudoku sudoku = sudokuReader.createSudoku("src/test/resources/sudoku.csv");
        SudokuSolver sudokuSolver = new SudokuSolver(sudoku);

        Cell cellFromFirstRow = sudoku.getCellList().get(0);
        List<Integer> expectedValuesFromFirstRow = Arrays.asList(1, 2, 5, 7, 9);
        assertEquals(expectedValuesFromFirstRow, sudokuSolver.possibleInRow(cellFromFirstRow));

        Cell cellFromSeventhRow = sudoku.getCellList().get(6 * 9 + 1);
        List<Integer> expectedValuesFromSeventhRow = Arrays.asList(2, 5, 7, 8, 9);
        assertEquals(expectedValuesFromSeventhRow, sudokuSolver.possibleInRow(cellFromSeventhRow));
    }

    @Test
    public void shouldReturnPossibleValuesInColumn() {
        Sudoku sudoku = sudokuReader.createSudoku("src/test/resources/sudoku.csv");
        SudokuSolver sudokuSolver = new SudokuSolver(sudoku);

        Cell cellFromFirstColumn = sudoku.getCellList().get(0);
        List<Integer> expectedValuesFromFirstColumn = Arrays.asList(2, 4, 6, 8, 9);
        assertEquals(expectedValuesFromFirstColumn, sudokuSolver.possibleInColumn(cellFromFirstColumn));

        Cell cellFromSeventhColumn = sudoku.getCellList().get(6);
        List<Integer> expectedValuesFromSeventhColumn = Arrays.asList(1, 4, 6, 7, 9);
        assertEquals(expectedValuesFromSeventhColumn, sudokuSolver.possibleInColumn(cellFromSeventhColumn));
    }

    @Test
    public void shouldReturnPossibleValuesInSquare() {
        Sudoku sudoku = sudokuReader.createSudoku("src/test/resources/sudoku.csv");
        SudokuSolver sudokuSolver = new SudokuSolver(sudoku);

        Cell cellFromFirstSquare = sudoku.getCellList().get(0);
        List<Integer> expectedValuesFromFirstSquare = Arrays.asList(1, 2, 8, 9);
        assertEquals(expectedValuesFromFirstSquare, sudokuSolver.possibleInSquare(cellFromFirstSquare));

        Cell cellFromSeventhSquare = sudoku.getCellList().get(8 * 9 + 1);
        List<Integer> expectedValuesFromSeventhSquare = Arrays.asList(4, 5, 7, 8, 9);
        assertEquals(expectedValuesFromSeventhSquare, sudokuSolver.possibleInSquare(cellFromSeventhSquare));
    }

    @Test
    public void shouldReturnPossibilities() {
        Sudoku sudoku = sudokuReader.createSudoku("src/test/resources/sudoku.csv");
        SudokuSolver sudokuSolver = new SudokuSolver(sudoku);

        Cell firstCell = sudoku.getCellList().get(0);
        List<Integer> expectedValues = Arrays.asList(2, 9);
        assertEquals(expectedValues, sudokuSolver.checkPossibilities(firstCell));
    }

    @Test
    public void shouldSetValueIfPossible_true() {
        Sudoku sudoku = sudokuReader.createSudoku("src/test/resources/sudoku.csv");
        SudokuSolver sudokuSolver = new SudokuSolver(sudoku);
        Cell cell = sudoku.getCellList().get(5 * 9 + 5);

        assertTrue(sudokuSolver.setValueIfPossible(cell));
        assertEquals(8, cell.getValue());
        assertTrue(cell.isSet());
    }

    @Test
    public void shouldSetValueIfPossible_false() {
        Sudoku sudoku = sudokuReader.createSudoku("src/test/resources/sudoku.csv");
        SudokuSolver sudokuSolver = new SudokuSolver(sudoku);
        Cell cell = sudoku.getCellList().get(0);

        assertFalse(sudokuSolver.setValueIfPossible(cell));
        assertEquals(0, cell.getValue());
        assertFalse(cell.isSet());
    }

}