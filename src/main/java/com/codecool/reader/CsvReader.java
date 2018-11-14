package com.codecool.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CsvReader {


    public String[][] readFromFile(String path) {
        String[][] sudoku = new String[9][9];

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;
            int i = 0;

            while ((line = br.readLine()) != null) {
                sudoku[i] = line.split("\t");
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sudoku;
    }
}
