package model;

public class EmployeePojo {

	private int emp_id;
	private String emp_name;
	private String emp_mobilenum;
	private String emp_email;

	public String getEmp_mobilenum() {
		return emp_mobilenum;
	}

	public void setEmp_mobilenum(String emp_mobilenum) {
		this.emp_mobilenum = emp_mobilenum;
	}

	String emp_address;
	String emp_password;

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}



	public String getEmp_email() {
		return emp_email;
	}

	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}

	public String getEmp_address() {
		return emp_address;
	}

	public void setEmp_address(String emp_address) {
		this.emp_address = emp_address;
	}

	public String getEmp_password() {
		return emp_password;
	}

	public void setEmp_password(String emp_password) {
		this.emp_password = emp_password;
	}

}
