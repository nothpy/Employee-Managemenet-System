package MyPackage;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		EmployeeOperation emp = new EmployeeOperation();

		try {
			while (true) {
				System.out.println("╔════════════════════════════════════╗");
				System.out.println("║    EMPLOYEE MANAGEMENT SYSTEM      ║");
				System.out.println("╠════════════════════════════════════╣");
				System.out.println("║ 1️  Add Employee                    ║");
				System.out.println("║ 2️  View All Employees              ║");
				System.out.println("║ 3️  Search Employee by ID           ║");
				System.out.println("║ 4️  Update Employee Salary          ║");
				System.out.println("║ 5️  Delete Employee                 ║");
				System.out.println("║ 6️  Exit                            ║");
				System.out.println("╚════════════════════════════════════╝");
				System.out.print("👉 Enter your choice: ");

				int choice = sc.nextInt();

				sc.nextLine();

				switch (choice) {
				case 1:
					emp.addEmployee();
					break;
				case 2:
					emp.viewEmployees();
					break;
				case 3:
					emp.searchEmployee();
					break;
				case 4:
					emp.updateSalary();
					break;
				case 5:
					emp.deleteEmployee();
					break;
				case 6:
					System.out.println("👋 Exiting... Thank you!");
					return;

				default:
					System.out.println("⚠️ Invalid Choice! Please try again.");
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("❌ Database Connection Failed: " + e.getMessage());
			e.printStackTrace();
			
		}

	}

}
