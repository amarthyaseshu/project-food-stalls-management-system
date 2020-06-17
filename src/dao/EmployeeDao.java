package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.EmployeePojo;
import model.VendorPojo;
import utility.ConnectionManager;


public abstract class EmployeeDao {


	public abstract void AddEmployee(EmployeePojo employeepojo) throws ClassNotFoundException, SQLException;

	public abstract boolean validateRegisteredEmployee(EmployeePojo employeepojo)
			throws ClassNotFoundException, SQLException;

	public static void viewStalls() throws ClassNotFoundException, SQLException {
		ArrayList<VendorPojo> viewing_stalls = new ArrayList<VendorPojo>();
		String sql = "select * from stall";

		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		System.out.format("%-25s%s%n", "StallID", "StallName");
		while (rs.next()) {
			int disp_stall_id = rs.getInt("id");
			String disp_stall_name = rs.getString("name");
			System.out.println(disp_stall_id + "\t\t\t" + disp_stall_name);
			viewing_stalls.add(new VendorPojo(disp_stall_id, disp_stall_name, 0));

		}
		System.out.format("%-25s%s%n", "StallID", "StallName");
		for (VendorPojo stall_display : viewing_stalls) {

			System.out.format("%-25s%s%n", stall_display.getStall_id(), stall_display.getStall_name());
		}
	}
}
