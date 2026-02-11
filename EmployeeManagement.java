package com.project.employee;

import java.sql.*;
import java.util.Scanner;

public class EmployeeManagement {

    static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    static final String USER = "root";
    static final String PASSWORD = "1234";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n--- Employee Management System (JDBC) ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addEmployee();
                    break;

                case 2:
                    viewEmployees();
                    break;

                case 3:
                    updateEmployee();
                    break;

                case 4:
                    deleteEmployee();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // 🔹 Get Connection
    static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // 🔹 INSERT
    static void addEmployee() {

        try (Connection con = getConnection()) {

            String sql = "INSERT INTO employee (id, name, salary) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            System.out.print("Enter ID: ");
            int id = sc.nextInt();

            System.out.print("Enter Name: ");
            sc.nextLine();
            String name = sc.nextLine();

            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setDouble(3, salary);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Employee added successfully.");
            else
                System.out.println("Insert failed.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 SELECT
    static void viewEmployees() {

        try (Connection con = getConnection()) {

            String sql = "SELECT * FROM employee";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println(
                        "ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Salary: " + rs.getDouble("salary")
                );
            }

            if (!found)
                System.out.println("No employees found.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 UPDATE
    static void updateEmployee() {

        try (Connection con = getConnection()) {

            String sql = "UPDATE employee SET name=?, salary=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            System.out.print("Enter Employee ID to update: ");
            int id = sc.nextInt();

            System.out.print("Enter new Name: ");
            sc.nextLine();
            String name = sc.nextLine();

            System.out.print("Enter new Salary: ");
            double salary = sc.nextDouble();

            ps.setString(1, name);
            ps.setDouble(2, salary);
            ps.setInt(3, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Employee updated successfully.");
            else
                System.out.println("Employee not found.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 DELETE
    static void deleteEmployee() {

        try (Connection con = getConnection()) {

            String sql = "DELETE FROM employee WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            System.out.print("Enter Employee ID to delete: ");
            int id = sc.nextInt();

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Employee deleted successfully.");
            else
                System.out.println("Employee not found.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
