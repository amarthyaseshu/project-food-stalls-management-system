package controller;

import java.sql.SQLException;

import dao.EmployeeDao;

public class EmployeeController {

	public static void employeeController() throws ClassNotFoundException, SQLException {

		System.out.println("Please Select A Stall In Which You Want To Order Your Food");

		EmployeeDao.viewStalls();
	}
}
