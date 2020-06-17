package utility;

import model.EmployeePojo;

public class userValidation {

	public static boolean validateuser(EmployeePojo employeepojo) {

		String phoneregex = "(0/91)?[7-9][0-9]{9}";
		String emailregex = "^(.+)@(.+)$";

		String emp_mobilenum = employeepojo.getEmp_mobilenum();
		String emp_email = employeepojo.getEmp_email();
		if (emp_mobilenum.matches(phoneregex) && emp_email.matches(emailregex))
			return true;
		else
			return false;
	}
}
