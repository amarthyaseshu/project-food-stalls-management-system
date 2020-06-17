package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.EmployeePojo;
import utility.ConnectionManager;

public class EmployeeDaoImpl extends EmployeeDao {

	@Override
	public void AddEmployee(EmployeePojo employeepojo) throws ClassNotFoundException, SQLException {

			int emp_id = employeepojo.getEmp_id();
			String emp_name = employeepojo.getEmp_name();
			String emp_mobilenum = employeepojo.getEmp_mobilenum();
			String emp_email = employeepojo.getEmp_email();
			String emp_address = employeepojo.getEmp_address();
			String emp_password = employeepojo.getEmp_password();

			String sql = "insert into employee(ID,NAME,PHONE_NUMBER,EMAIL_ID,ADDRESS,PASSWORD)VALUES(?,?,?,?,?,?)";

			PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql);

			ps.setInt(1, emp_id);
			ps.setString(2, emp_name);
			ps.setString(3, emp_mobilenum);
			ps.setString(4, emp_email);
			ps.setString(5, emp_address);
			ps.setString(6, emp_password);

			ps.executeUpdate();

			ConnectionManager.getConnection().close();

	}

	@Override
	public boolean validateRegisteredEmployee(EmployeePojo employeepojo) throws ClassNotFoundException, SQLException {

		int emp_id = employeepojo.getEmp_id();
		String emp_pass = employeepojo.getEmp_password();

		String sql = "select * from employee";
		PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {

			if (emp_id == rs.getInt("id") && emp_pass.equalsIgnoreCase(rs.getString("password"))) {

				return true;
			}
		}

		return false;
	}

//	@Override
//	public void viewStalls(ArrayList<VendorPojo> viewing_stalls) throws ClassNotFoundException, SQLException {
//
////	
//		String sql = "select * from stall";
//
//		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql);
//
//		ResultSet rs = ps.executeQuery();
//		System.out.format("%-25s%s%n", "StallID", "StallName");
//		while (rs.next()) {
//			int disp_stall_id = rs.getInt("id");
//			String disp_stall_name = rs.getString("name");
//			System.out.println(disp_stall_id + "\t\t\t" + disp_stall_name);
//			viewing_stalls.add(new VendorPojo(disp_stall_id, disp_stall_name, 0));
//
//		}
//		System.out.format("%-25s%s%n", "StallID", "StallName");
//		for (VendorPojo stall_display : viewing_stalls) {
//
//			System.out.format("%-25s%s%n", stall_display.getStall_id(), stall_display.getStall_name());
//		}
//	}



}
