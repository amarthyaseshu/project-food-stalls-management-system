package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.EmployeePojo;
import utility.ConnectionManager;

public class EmployeeDaoImpl extends EmployeeDao {

	// registering the employee(adding employee details into database)
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

			int rows_inserted = ps.executeUpdate();

			if (rows_inserted > 0) {
				System.out.println("Employee Registration  Succesfull");
				ConnectionManager.getConnection().close();
			} else {
				System.err.println(
						"Sorry For The Inconvenience! Registration Failed,Please Give Valid Details And Try Again. Thank You For Your Patience");
				ConnectionManager.getConnection().close();
			}

	}

// validating the employee while login
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

}
