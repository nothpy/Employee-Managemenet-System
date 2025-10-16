package MyPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class EmployeeOperation {

	// Add new employee
	public void addEmployee() {
		Scanner sc = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/employee_db_management";
		String userName = "root";
		String password = "abcd1234";

		try {
			Connection conn = DriverManager.getConnection(url, userName, password);
			Statement st = conn.createStatement();

			System.out.print("Enter Employee Name: ");
			String name = sc.nextLine();

			System.out.print("Enter Department: ");
			String dept = sc.nextLine();

			System.out.print("Enter Salary: ");
			double salary = sc.nextDouble();

			System.out.print("Enter mobile number: ");
			double mobile = sc.nextDouble();

			String query = "INSERT INTO employees (name, department, salary, mobile) VALUES (?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);

			ps.setString(1, name);
			ps.setString(2, dept);
			ps.setDouble(3, salary);
			ps.setDouble(4, mobile);
			ps.executeUpdate();

			System.out.println("✅ Employee Added Successfully!");
		} catch (Exception e) {
			System.out.println("❌ Error: ");
			e.printStackTrace();
		}
	}

	// View all employees
	public void viewEmployees() {
		Scanner sc = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/employee_db_management";
		String userName = "root";
		String password = "abcd1234";

		try {
			Connection conn = DriverManager.getConnection(url, userName, password);
			Statement st = conn.createStatement();

			String query = "SELECT * FROM employees";

			ResultSet rs = st.executeQuery(query);

			System.out.println("╔════════════════════════════════════════════════════╗");
			System.out.println("║                  👥 EMPLOYEE LIST                  ║");
			System.out.println("╚════════════════════════════════════════════════════╝");
			System.out.printf("%-5s %-20s %-20s %-10s%n", "ID", "Name", "Department", "Salary", "Mobile");
			System.out.println("-------------------------------------------------------");

			while (rs.next()) {
				System.out.printf("%-5d %-20s %-20s %-10.2f%n", rs.getInt("id"), rs.getString("name"),
						rs.getString("department"), rs.getDouble("salary"), rs.getDouble("mobile"));
			}

		} catch (Exception e) {
			System.out.println("❌ Error: " + e.getMessage());
		}
	}

	// Search employee by ID

	public void searchEmployee() {

		Scanner sc = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/employee_db_management";
		String userName = "root";
		String password = "abcd1234";

		try {
			Connection conn = DriverManager.getConnection(url, userName, password);
			Statement st = conn.createStatement();

			System.out.print("Enter Employee ID: ");
			int id = sc.nextInt();

			String query = "SELECT * FROM employees WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(query);

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				System.out.println("\n🔍 Employee Found:");
				System.out.println("ID: " + rs.getInt("id"));
				System.out.println("Name: " + rs.getString("name"));
				System.out.println("Department: " + rs.getString("department"));
				System.out.println("Salary: ₹" + rs.getDouble("salary"));

			} else {
				System.out.println("⚠️ No employee found with ID: " + id);
			}
		} catch (Exception e) {
			System.out.println("❌ Error: " + e.getMessage());
		}
	}

	// Update employee salary
	public void updateSalary() {

		Scanner sc = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/employee_db_management";
		String userName = "root";
		String password = "abcd1234";

		try {
			Connection conn = DriverManager.getConnection(url, userName, password);
			Statement st = conn.createStatement();

			System.out.print("Enter Employee ID: ");
			int id = sc.nextInt();

			System.out.print("Enter New Salary: ");
			double newSalary = sc.nextDouble();

			String query = "UPDATE employees SET salary = ? WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDouble(1, newSalary);
			ps.setInt(2, id);
			int updated = ps.executeUpdate();

			if (updated > 0)
				System.out.println("💰 Salary Updated Successfully!");
			else
				System.out.println("⚠️ Employee Not Found!");
		} catch (Exception e) {
			System.out.println("❌ Error: " + e.getMessage());
		}
	}

	// Delete employee
	public void deleteEmployee() {

		Scanner sc = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/employee_db_management";
		String userName = "root";
		String password = "abcd1234";

		try {

			Connection conn = DriverManager.getConnection(url, userName, password);
			Statement st = conn.createStatement();
			System.out.print("Enter Employee ID to Delete: ");
			int id = sc.nextInt();

			String query = "DELETE FROM employees WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			int deleted = ps.executeUpdate();

			if (deleted > 0)
				System.out.println("🗑️ Employee Deleted Successfully!");
			else
				System.out.println("⚠️ No Employee Found with ID: " + id);
		} catch (Exception e) {
			System.out.println("❌ Error: " + e.getMessage());
		}
	}

}
