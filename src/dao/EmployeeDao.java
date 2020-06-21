package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.EmployeePojo;
import model.ItemPojo;
import model.VendorPojo;
import utility.ConnectionManager;


public abstract class EmployeeDao {

	// registering the employee(adding employee details into database)
	public abstract void AddEmployee(EmployeePojo employeepojo) throws ClassNotFoundException, SQLException;

	// validating the employee while login
	public abstract boolean validateRegisteredEmployee(EmployeePojo employeepojo)
			throws ClassNotFoundException, SQLException;

// viewing all the available stalls to the employee
	public static void viewStalls() throws ClassNotFoundException, SQLException {

		String sql = "select * from stall";

		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		System.out.format("%-25s%s%n", "StallID", "StallName");
		while (rs.next()) {
			int disp_stall_id = rs.getInt("id");
			String disp_stall_name = rs.getString("name");
			System.out.println(disp_stall_id + "\t\t\t" + disp_stall_name);
		}
		ConnectionManager.getConnection().close();
	}

// viewing items for the employee in a particular stall
	public static void viewItems(int emp_select_stall_id) throws ClassNotFoundException, SQLException {

		String sql = "select id,name,price from item where stall_id=?";

		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql);
		ps.setInt(1, emp_select_stall_id);
		ResultSet rs = ps.executeQuery();
		System.out.format("%-25s%s%40s%n", "Item ID", "Item Name", "Item Price");
		while (rs.next()) {
			int disp_item_id = rs.getInt("id");
			String disp_item_name = rs.getString("name");
			int disp_item_price = rs.getInt("price");
			System.out.format("%-25s%s%40s%n", disp_item_id, disp_item_name, disp_item_price);
		}
		ConnectionManager.getConnection().close();
	}

// Employee Ordering Food
	public static void orderFood(int emp_id, int emp_item_id,
			int emp_order_quantity)
			throws ClassNotFoundException, SQLException {
		ItemPojo itempojo = new ItemPojo(0, null, 0);
		VendorPojo vendorpojo = new VendorPojo(0, null, 0);
		String order_status = "ordered";

		String sql = "select * from item where id=?";

		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql);
		ps.setInt(1, emp_item_id);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int stall_id = rs.getInt("stall_id");
			int order_price = rs.getInt("price") * emp_order_quantity;
			vendorpojo.setStall_id(stall_id);
			itempojo.setItem_price(order_price);
		}
		int stall_id = vendorpojo.getStall_id();
		int order_price = itempojo.getItem_price();
		String sql1 = "insert into orders(id,employee_id,stall_id,item_id,order_price,order_quantity,order_status)VALUES(my_sequence.nextval,?,?,?,?,?,?)";

		PreparedStatement ps1 = ConnectionManager.getConnection().prepareStatement(sql1);

		ps1.setInt(1, emp_id);
		ps1.setInt(2, stall_id);
		ps1.setInt(3, emp_item_id);
		ps1.setInt(4, order_price);
		ps1.setInt(5, emp_order_quantity);
		ps1.setString(6, order_status);

		int rows_inserted = ps1.executeUpdate();

		if (rows_inserted > 0) {
			System.out.println("Your Order Had Been Placed Succesfully");
			ConnectionManager.getConnection().close();
		} else {
			System.err.println(
					"Sorry For The Inconvenience! The Order Is Not Placed Succesfully. Please Retry, Thank You For Your Patience");
			ConnectionManager.getConnection().close();
		}
	}

// For Viewing Queue for the employee and vendor
	public static void viewQueue(int stall_id) throws ClassNotFoundException, SQLException {

		String sql = "select id,employee_id,stall_id,item_id,order_status from orders where stall_id=?";

		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql);

		ps.setInt(1, stall_id);

		ResultSet rs = ps.executeQuery();
		System.out.println("\t\t" + "ORDER ID" +
				"\t\t" + "Employee ID" + "\t\t" + "STALL ID" + "\t\t" + "ITEM ID"
						+ "\t\t\t"
						+ "ORDER STATUS");
		while (rs.next()) {
			System.out.println(
					"\t\t\t" + rs.getInt("id") + "\t\t\t" + rs.getInt("employee_id") + "\t\t\t" + rs.getInt("stall_id")
							+ "\t\t\t"
					+ rs.getInt("item_id") + "\t\t\t" + rs.getString("order_status"));
		}
	}

	public static void filterItem(String item_name) throws ClassNotFoundException, SQLException {
		ResultSet rs = null;
		try {
		String sql = "select * from item where name=?";

		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql);

		ps.setString(1, item_name);

		rs = ps.executeQuery();


		int count = 0;
		while (rs.next()) {
			count++;

			if (count == 0) {
				System.err.println("Sorry This Item Doesnt Exists");
			} else {
				System.out.println("Item ID" + "     " + "ITEM NAME" + "     " + "ITEM PRICE" + "     " + "Stall ID");
			System.out.println(
					rs.getInt("id") + "     " + rs.getString("name") + "     " + rs.getInt("price") + "     "
							+ rs.getInt("stall_id"));
		}
	}

} catch (Exception e) {
		e.printStackTrace();
	}

	}

}
