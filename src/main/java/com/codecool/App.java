package com.codecool;

import com.codecool.reader.SudokuReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner {
    @Autowired
    SudokuReader sudokuReader;

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);

    }


    @Override
    public void run(String... strings) throws Exception {
        System.out.println(sudokuReader.createSudoku("src/main/resources/sudoku.csv").toString());
    }
}
