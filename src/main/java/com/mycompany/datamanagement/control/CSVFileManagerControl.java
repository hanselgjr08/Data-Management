package com.mycompany.datamanagement.control;

import java.util.ArrayList;
import java.io.*;
/**
 *
 * @author Hansel
 */
public class CSVFileManagerControl {
    
    //Define path to the CSV
    public ArrayList<String> readLine(String ruta) throws IOException {
        ArrayList<String> lineas = new ArrayList<>();
        BufferedReader inputStream = null;
        try {
            inputStream = new BufferedReader(new FileReader(ruta));
            String l;
            while ((l = inputStream.readLine()) != null) {
                lineas.add(l);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return lineas;
    }
    
}
