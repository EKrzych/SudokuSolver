package com.codecool.reader;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class CsvReader {

    public String[][] readFromFile(String path) {
        String[][] sudoku = new String[9][9];

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;
            int i = 0;

            while ((line = br.readLine()) != null) {
                sudoku[i] = line.split(" {4}");
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sudoku;
    }
}
