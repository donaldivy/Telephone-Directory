package com.telephone_directory.e_project;

import java.io.IOException;

import java.io.*;
import java.util.Scanner;
     class TelephoneDirectory {
    private static final int MAX_ENTRIES = 100;
    private static final String FILENAME = "telephone_directory.txt";

    private String[] names = new String[MAX_ENTRIES];
    private int[] ages = new int[MAX_ENTRIES];
    private String[] contactNumbers = new String[MAX_ENTRIES];
    private int size = 0;

    public static void main(String[] args) {
        TelephoneDirectory directory = new TelephoneDirectory();
        directory.loadFromFile(); // Load data from file if exists

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("1. Add new entry");
            System.out.println("2. Retrieve entry by index");
            System.out.println("3. Display entries in a range");
            System.out.println("4. Display all entries");
            System.out.println("5. Display entries starting with a character");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    directory.addEntry(scanner);
                    break;
                case 2:
                    directory.retrieveEntry(scanner);
                    break;
                case 3:
                    directory.displayRange(scanner);
                    break;
                case 4:
                    directory.displayAllEntries();
                    break;
                case 5:
                    directory.displayEntriesStartingWith(scanner);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        directory.saveToFile(); // Save data to file before exiting
    }

    private void addEntry(Scanner scanner) {
        if (size >= MAX_ENTRIES) {
            System.out.println("Telephone directory is full.");
            return;
        }
        System.out.print("Enter name: ");
        names[size] = scanner.nextLine();
        System.out.print("Enter age: ");
        ages[size] = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter contact number: ");
        contactNumbers[size] = scanner.nextLine();
        size++;
        System.out.println("Entry added successfully.");
    }

    private void retrieveEntry(Scanner scanner) {
        System.out.print("Enter index to retrieve: ");
        int index = scanner.nextInt();
        if (index >= 0 && index < size) {
            System.out.println("Name: " + names[index]);
            System.out.println("Age: " + ages[index]);
            System.out.println("Contact number: " + contactNumbers[index]);
        } else {
            System.out.println("Invalid index.");
        }
    }

    private void displayRange(Scanner scanner) {
        System.out.print("Enter start index: ");
        int start = scanner.nextInt();
        System.out.print("Enter end index: ");
        int end = scanner.nextInt();
        if (start >= 0 && end < size && start <= end) {
            for (int i = start; i <= end; i++) {
                System.out.println("Index " + i + ":");
                System.out.println("Name: " + names[i]);
                System.out.println("Age: " + ages[i]);
                System.out.println("Contact number: " + contactNumbers[i]);
            }
        } else {
            System.out.println("Invalid range.");
        }
    }

    private void displayAllEntries() {
        for (int i = 0; i < size; i++) {
            System.out.println("Index " + i + ":");
            System.out.println("Name: " + names[i]);
            System.out.println("Age: " + ages[i]);
            System.out.println("Contact number: " + contactNumbers[i]);
        }
    }

    private void displayEntriesStartingWith(Scanner scanner) {
        System.out.print("Enter character to search for: ");
        char startChar = scanner.next().charAt(0);
        for (int i = 0; i < size; i++) {
            if (names[i].charAt(0) == startChar) {
                System.out.println("Index " + i + ":");
                System.out.println("Name: " + names[i]);
                System.out.println("Age: " + ages[i]);
                System.out.println("Contact number: " + contactNumbers[i]);
            }
        }
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    names[size] = parts[0];
                    ages[size] = Integer.parseInt(parts[1]);
                    contactNumbers[size] = parts[2];
                    size++;
                }
            }
            System.out.println("Data loaded from file.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading data from file: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME))) {
            for (int i = 0; i < size; i++) {
                writer.println(names[i] + "," + ages[i] + "," + contactNumbers[i]);
            }
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }
}
