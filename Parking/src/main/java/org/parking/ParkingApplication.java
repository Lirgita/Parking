package org.parking;

import java.util.Scanner;

public class ParkingApplication {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        SmartParking[] places = new SmartParking[5];

        for (int i = 0; i < places.length; i++) {
            places[i] = new SmartParking(
                    i + 1,
                    "NORMAL",
                    "FREE",
                    "-",
                    -1,
                    -1,
                    0,
                    0.0
            );
            places[i].fillArray(i);
        }

        ParkingFileManager fileManager =
                new ParkingFileManager("parking.txt");

        int choice = -1;

        while (choice != 0) {

            System.out.println("\n===== PARKING SYSTEM =====");
            System.out.println("1. Park a vehicle");
            System.out.println("2. Release parking place");
            System.out.println("3. Show parking status");
            System.out.println("4. Save parking data");
            System.out.println("5. Load parking data");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            try {
                choice = sc.nextInt();

                switch (choice) {

                    case 1: // PARK VEHICLE
                        System.out.print("Enter parking place number (1-5): ");
                        int placeNr = sc.nextInt();

                        System.out.print("Enter license plate: ");
                        String plate = sc.next();

                        System.out.print("Enter entry hour (0-23): ");
                        int entryHour = sc.nextInt();

                        if (entryHour < 0 || entryHour > 23)
                            throw new IllegalArgumentException("Invalid hour!");

                        SmartParking p = places[placeNr - 1];

                        // save status before parking
                        String statusBefore = p.getStatus();

                        p.parkCar(plate, entryHour);

                        // check if parking actually happened
                        if (!statusBefore.equals(p.getStatus())) {
                            p.fillArray(placeNr - 1);
                            System.out.println("Vehicle parked successfully!");
                        }
                        break;

                    case 2: // RELEASE PLACE
                        System.out.print("Enter parking place number (1-5): ");
                        int releasePlace = sc.nextInt();

                        System.out.print("Enter exit hour (0-23): ");
                        int exitHour = sc.nextInt();

                        if (exitHour < 0 || exitHour > 23)
                            throw new IllegalArgumentException("Invalid hour!");

                        places[releasePlace - 1].releaseCar(exitHour);
                        places[releasePlace - 1].fillArray(releasePlace - 1);

                        System.out.println("Parking place released!");
                        break;

                    case 3: // DISPLAY STATUS
                        System.out.println("\n--- PARKING STATUS ---");
                        SmartParking.displayParkingData();
                        break;

                    case 4: // SAVE
                        fileManager.saveToFile(SmartParking.getParkingData());
                        break;

                    case 5: // LOAD
                        fileManager.loadFromFile(SmartParking.getParkingData());
                        break;

                    case 0:
                        System.out.println("Thank you for choosing our parking system!");
                        break;

                    default:
                        System.out.println("Invalid option!");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine(); // clear invalid input
            }
        }

        sc.close();
    }
}
