package com.jdbcemployeepayroll;

import java.time.LocalDate;
import java.util.Scanner;

public class EmployeePayRoll {
	public static void main(String[] args) {
		EmployeePayRollService employeePayRollService = new EmployeePayRollService();
		Scanner scanner = new Scanner(System.in);

		final int EXIT = 10;
		int choice = 0;
		while (choice != EXIT) {
			System.out.println(
					"\"enter your choice\\n1. Get employee data\\n2. update basic pay\\n3. display employee roll\\n4. empdata range  \\n5. calculate \\n6. EXIT\n");
			choice = scanner.nextInt();
			switch (choice) {
			case 1:
				String query = "select * from employee_payroll";
				employeePayRollService.queryExecute(query);
				employeePayRollService.display();
				break;

			case 2:
				System.out.println("enter employee name");
				String empName = scanner.next();
				System.out.println("enter basic pay you want to update");
				double basicPay = scanner.nextDouble();
				employeePayRollService.updateBasicPay(empName, basicPay);
				break;

			case 3:
				employeePayRollService.display();
				break;

			case 4:
				System.out.println("enter initial date");
				LocalDate iDate = LocalDate.parse(scanner.next());
				System.out.println("enter final date");
				LocalDate eDate = LocalDate.parse(scanner.next());
				employeePayRollService.getEmployee(iDate, eDate);
				break;

			case 5:
				employeePayRollService.calculate();
				break;

			case 6:
				System.out.println("good bye");
				break;

			}
		}
	}
	

}
