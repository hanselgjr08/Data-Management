package com.mycompany.datamanagement.control;

import java.util.ArrayList;
import java.io.*;

/**
 * Handles low-level reading of CSV files located in the inputfiles folder,
 * only reads raw text lines; it does not know anything about Customers.
 *
 * @author Hansel
 */
public class CSVFileManagerControl {
    
    /**
     * Reads all lines from a CSV file located in the inputfiles folder.
     *
     * @param fileName the name of the CSV file, without extension
     * @return a list with every line of the file, including the header
     * @throws IOException if the file cannot be found or read
     */
    public static ArrayList<String> readLine(String fileName) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader inputStream = null;
        try {
            inputStream = new BufferedReader(new FileReader("inputfiles/" + fileName + ".csv"));
            String l;
            while ((l = inputStream.readLine()) != null) {
                lines.add(l);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return lines;
    }

}
