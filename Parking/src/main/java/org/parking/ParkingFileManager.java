package org.parking;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ParkingFileManager {

    private String fileName;

    public ParkingFileManager(String fileName) {
        this.fileName = fileName;
    }

    // ---------- SAVE TO FILE ----------
    public void saveToFile(String[][] data) {

        try {
            FileWriter fw = new FileWriter(fileName);

            for (int i = 0; i < data.length; i++) {
                if (data[i][0] != null) {
                    for (int j = 0; j < data[i].length; j++) {
                        fw.write(data[i][j] + ",");
                    }
                    fw.write("\n");
                }
            }

            fw.close();
            System.out.println("Data saved successfully.");

        } catch (IOException e) {
            System.out.println("Error while writing to file!");
        }
    }

    // ---------- LOAD FROM FILE ----------
    public void loadFromFile(String[][] data) {

        try {
            Scanner scanner = new Scanner(new File(fileName));
            int row = 0;

            while (scanner.hasNextLine() && row < data.length) {
                String line = scanner.nextLine();
                data[row] = line.split(",");
                row++;
            }

            scanner.close();
            System.out.println("Data loaded successfully.");

        } catch (Exception e) {
            System.out.println("Error while reading from file!");
        }
    }
}
