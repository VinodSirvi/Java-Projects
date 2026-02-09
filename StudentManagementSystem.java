package com.project.school;


import java.util.ArrayList;
import java.util.Scanner;

// Main class to manage student operations
public class StudentManagementSystem {

    // List to store student objects
    private static ArrayList<Student> students = new ArrayList<>();

    // Scanner to take user input from console
    private static Scanner scanner = new Scanner(System.in);

    // Main method
    public static void main(String[] args) {
        boolean running = true;

        // Display menu repeatedly until user chooses to exit
        while (running) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            // Read user's menu choice
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            // Perform action based on user's choice
            switch (choice) {
                case 1:
                    addStudent(); // Add student
                    break;
                case 2:
                    viewAllStudents(); // View all students
                    break;
                case 3:
                    searchStudent(); // Search student by ID
                    break;
                case 4:
                    deleteStudent(); // Delete student by ID
                    break;
                case 5:
                    running = false; // Exit program
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        // Final message and cleanup
        System.out.println("Program exited. Goodbye!");
        scanner.close();
    }

    // Method to add a new student
    private static void addStudent() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine(); // Read student ID

        System.out.print("Enter student name: ");
        String name = scanner.nextLine(); // Read student name

        System.out.print("Enter student age: ");
        int age = scanner.nextInt(); // Read student age
        scanner.nextLine(); // Consume newline

        System.out.print("Enter student grade: ");
        char grade = scanner.nextLine().charAt(0); // Read student grade (first character)

        // Create Student object and add to list
        Student student = new Student(id, name, age, grade);
        students.add(student);

        System.out.println("Student added successfully!");
    }

    // Method to view all students
    private static void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\nList of Students:");
        for (Student student : students) {
            System.out.println(student); // Calls student.toString()
        }
    }

    // Method to search for a student by ID
    private static void searchStudent() {
        System.out.print("Enter student ID to search: ");
        String id = scanner.nextLine();

        boolean found = false;
        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.println("Student found:");
                System.out.println(student);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    // Method to delete a student by ID
    private static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        String id = scanner.nextLine();

        // Remove student using lambda condition
        boolean removed = students.removeIf(student -> student.getId().equals(id));

        if (removed) {
            System.out.println("Student with ID " + id + " deleted successfully.");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }
}

// Student class to represent student details
class Student {
    private String id;
    private String name;
    private int age;
    private char grade;

    // Constructor
    public Student(String id, String name, int age, char grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    // Getter for ID (used for searching and deleting)
    public String getId() {
        return id;
    }

    // toString() method to display student details
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Grade: " + grade;
    }
}