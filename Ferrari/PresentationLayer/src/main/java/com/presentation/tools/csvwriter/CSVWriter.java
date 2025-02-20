package com.presentation.tools.csvwriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//karl
//CSVWriter class basic
public class CSVWriter {
    //private fields
    private String path;
    private String[] headers;
    private String[][] content;
    //constructor
    public CSVWriter(String path, String[] headers, String[][] content) {
        this.path = path;
        this.headers = headers;
        this.content = content;
    }
    //writeRow method
    private void writeRow(FileWriter writer, String[] row) throws IOException {
        String rowString = String.join(";", row);
        writer.write(rowString);
        writer.write("\n");
    }
    //write method that makes a file called what path and ends with csv
    public void write() {
        File file = new File(path + ".csv");

        try (FileWriter writer = new FileWriter(file)) {
            writeRow(writer, headers);
            for (String[] row : content) {
                writeRow(writer, row);
            }
            writer.close();

            System.out.println("CSV file has been written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
