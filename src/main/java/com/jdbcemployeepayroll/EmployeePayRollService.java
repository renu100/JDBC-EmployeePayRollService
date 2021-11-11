package com.jdbcemployeepayroll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayRollService {

	public ArrayList<Employee> empList;
	PreparedStatement preparedStatement;
	Connection connection = EmployeeConfig.getConfig();

	public List<Employee> queryExecute(String query) {
		empList = new ArrayList<Employee>();
		try {
			preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Employee employee = new Employee();

				employee.setID(resultSet.getInt("ID"));
				employee.setName(resultSet.getString("Name"));
				employee.setPhoneNumber(resultSet.getInt("PhoneNumber"));
				employee.setAddress(resultSet.getString("Address"));
				employee.setDepartment(resultSet.getString("Department"));
				employee.setStart(resultSet.getString("Start"));
				employee.setBasicPay(resultSet.getDouble("BasicPay"));
				employee.setDeductions(resultSet.getDouble("Deductions"));
				employee.setTaxablePay(resultSet.getDouble("TaxablePay"));
				employee.setIncomeTax(resultSet.getDouble("IncomeTax"));
				employee.setNetPay(resultSet.getDouble("NetPay"));
				empList.add(employee);
			}
		} catch (SQLException e) {
			throw new EmployeeException("invalid column label");
		}
		return empList;
	}

	public void display() {
		for (Employee i : empList) {
			System.out.println(i.toString());
		}
	}

	public double updateBasicPay(String Name, double basicPay) {
		String UPDATE = "UPDATE employee_payroll SET BasicPay = ? WHERE Name = ?";
		try {
			preparedStatement = connection.prepareStatement(UPDATE);
			preparedStatement.setDouble(1, basicPay);
			preparedStatement.setString(2, Name);
			preparedStatement.executeUpdate();
			System.out.println("update successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql = "SELECT * FROM employee_payroll";
		queryExecute(sql);
		for (Employee employee : empList) {
			if (employee.getName().equals(Name)) {
				return employee.getBasicPay();
			}
		}
		return 0.0;
	}

	public void getEmployee(LocalDate start, LocalDate end) {
		ArrayList<Employee> empSelected = new ArrayList<>();
		String select = "SELECT * FROM employee_payroll WHERE EmpStart BETWEEN ? AND ?";
		String sDate = String.valueOf(start);
		String eDate = String.valueOf(end);
		try {
			preparedStatement = connection.prepareStatement(select);
			preparedStatement.setString(1, sDate);
			preparedStatement.setString(2, eDate);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Employee employee = new Employee();

				employee.setID(resultSet.getInt("ID"));
				employee.setName(resultSet.getString("Name"));
				employee.setPhoneNumber(resultSet.getInt("PhoneNumber"));
				employee.setAddress(resultSet.getString("Address"));
				employee.setDepartment(resultSet.getString("Department"));
				employee.setStart(resultSet.getString("Start"));
				employee.setBasicPay(resultSet.getDouble("BasicPay"));
				employee.setDeductions(resultSet.getDouble("Deductions"));
				employee.setTaxablePay(resultSet.getDouble("TaxablePay"));
				employee.setIncomeTax(resultSet.getDouble("IncomeTax"));
				employee.setNetPay(resultSet.getDouble("NetPay"));

				empSelected.add(employee);
			}
			for (Employee employee : empSelected) {
				System.out.println(employee);
			}

		} catch (SQLException e) {
			throw new EmployeeException("Invalid date");
		}
	}

}
