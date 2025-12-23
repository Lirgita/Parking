package org.parking;

public class SmartParking {

    private static String[][] parkingData = new String[100][8];

    private int placeNumber;
    private String type;        // NORMAL / VIP
    private String status;      // FREE / OCCUPIED
    private String plateNumber;
    private int entryHour;
    private int exitHour;
    private int totalTime;
    private double fine;

    public SmartParking(int placeNumber, String type, String status,
                        String plateNumber, int entryHour,
                        int exitHour, int totalTime, double fine) {
        this.placeNumber = placeNumber;
        this.type = type;
        this.status = status;
        this.plateNumber = plateNumber;
        this.entryHour = entryHour;
        this.exitHour = exitHour;
        this.totalTime = totalTime;
        this.fine = fine;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public int getEntryHour() {
        return entryHour;
    }

    public int getExitHour() {
        return exitHour;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public double getFine() {
        return fine;
    }

    public static String[][] getParkingData() {
        return parkingData;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setEntryHour(int entryHour) {
        this.entryHour = entryHour;
    }

    public void setExitHour(int exitHour) {
        this.exitHour = exitHour;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    // Park a car if the parking place is free
    public void parkCar(String plateNumber, int entryHour) {
        if (status.equals("OCCUPIED")) {
            System.out.println("The parking place is already occupied!");
            return;
        }

        status = "OCCUPIED";
        this.plateNumber = plateNumber;
        this.entryHour = entryHour;
    }

    // Release the parking place and calculate fine if needed
    public void releaseCar(int exitHour) {
        if (status.equals("FREE")) {
            System.out.println("The parking place is already free!");
            return;
        }

        this.exitHour = exitHour;
        this.totalTime = exitHour - entryHour;

        calculateFine();

        if (fine > 0) {
            System.out.println("Violation! The allowed parking time is 3 hours.");
            System.out.println("Applied fine: " + fine + " EUR");
        } else {
            System.out.println("Parking finished without any violation.");
        }

        status = "FREE";
        plateNumber = "-";
        entryHour = -1;
    }

    // Calculate fine based on allowed time
    private void calculateFine() {
        int allowedTime = 3; // allowed hours

        if (totalTime > allowedTime) {
            fine = (totalTime - allowedTime) * 5.0;
        } else {
            fine = 0.0;
        }
    }

    // Display single parking place info
    public void display() {
        System.out.println(
                placeNumber + " | " +
                        type + " | " +
                        status + " | " +
                        plateNumber + " | " +
                        entryHour + " | " +
                        exitHour + " | " +
                        totalTime + " | " +
                        fine
        );
    }

    // Save parking place data into 2D array
    public void fillArray(int row) {
        if (row < 0 || row >= parkingData.length) {
            System.out.println("Invalid index!");
            return;
        }

        parkingData[row][0] = String.valueOf(placeNumber);
        parkingData[row][1] = type;
        parkingData[row][2] = status;
        parkingData[row][3] = plateNumber;
        parkingData[row][4] = String.valueOf(entryHour);
        parkingData[row][5] = String.valueOf(exitHour);
        parkingData[row][6] = String.valueOf(totalTime);
        parkingData[row][7] = String.valueOf(fine);
    }

    // Display all parking data from 2D array
    public static void displayParkingData() {
        for (int i = 0; i < parkingData.length; i++) {
            if (parkingData[i][0] != null) {
                for (int j = 0; j < parkingData[i].length; j++) {
                    System.out.print(parkingData[i][j] + " | ");
                }
                System.out.println();
            }
        }
    }
}
