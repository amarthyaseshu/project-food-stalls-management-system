package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import dao.EmployeeDao;
import dao.PDFGenerationQueue;
import model.EmployeePojo;

public class EmployeeController {

	public static void employeeController(EmployeePojo employeepojo) throws Exception {
		int emp_id = employeepojo.getEmp_id();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int emp_select_stall_cat;
		do {
		System.out.println("Available Stalls In Which You Can Order Your Food");
		EmployeeDao.viewStalls();
		System.out.println("Please Provide The Stall ID In Which You Want To Order Your Food");
		int emp_select_stall_id = Integer.parseInt(br.readLine());
		System.out
				.println("1. View Queue" + "\n" + "2. View Items" + "\n" + "3.Search Item By Name" + "\n" + "4. Exit");
		emp_select_stall_cat = Integer.parseInt(br.readLine());
		if (emp_select_stall_cat == 1) {
			// queue for the selected stall will be viewed for the employee
			EmployeeDao.viewQueue(emp_select_stall_id);
			PDFGenerationQueue.generateQueue(emp_select_stall_id);
			emp_select_stall_cat = 2;
		}
		if (emp_select_stall_cat == 2) {
			// employee can view items
			EmployeeDao.viewItems(emp_select_stall_id);
			int emp_q_or_o;
			do {
				System.out.println("Select 1 To Order Items  Or 2 To Exit");
				emp_q_or_o = Integer.parseInt(br.readLine());
				if (emp_q_or_o == 1) {
					// Employee ordering items
					int emp_still_order;
					do {
					System.out.println("Please Provide The Item ID For The Food");
					int emp_item_id = Integer.parseInt(br.readLine());
					System.out.println(
							"Please Select The Number of Plates You Wish To Order.Please Note That Your Order Will Only Be Accepted If You Order A Minimum Of 1 Plate, and A Maximum of 5 Plates is Recommended");
					int emp_order_quantity = Integer.parseInt(br.readLine());

					EmployeeDao.orderFood(emp_id, emp_item_id, emp_order_quantity);
					System.out.println(
							"Do You Wish To Order Other Items From This Stall. Please Press 1 For Yes And 2 For No");
					emp_still_order = Integer.parseInt(br.readLine());
					if (emp_still_order != 1) {

						emp_select_stall_cat = 5;
					}
				} while (emp_still_order == 1);

			} else if (emp_q_or_o == 2) {
				emp_select_stall_cat = 5;
				}
			} while (emp_q_or_o != 1 && emp_q_or_o != 2);
		} else if (emp_select_stall_cat == 3) {

			System.out.println("Please Enter The Item Name You Wish To Order");
			String item_name = br.readLine();
			EmployeeDao.filterItem(item_name);
			emp_select_stall_cat = 5;
		} else if (emp_select_stall_cat == 4) {
			// employee exits
			Main.main(null);
		}
	} while (emp_select_stall_cat != 1 && emp_select_stall_cat != 2 && emp_select_stall_cat != 3
			&& emp_select_stall_cat != 4);

	}
}
