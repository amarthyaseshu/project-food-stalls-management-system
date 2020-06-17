package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dao.AdminDao;
import dao.EmployeeDaoImpl;
import dao.VendorDao;
import model.AdminPojo;
import model.EmployeePojo;
import model.VendorPojo;
import utility.userValidation;

public class Main {

	public static void main(String[] args) throws Exception, IOException {

		System.out.println("Food Stalls Management System" + "\n" + "(Eat More Wait Less!!!)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int category_select;
// for selecting the category by the user and moving into their respective interface
		do {
			System.out.println("Please select the category you belong to");
			System.out.println("1. Admin" + "\n" + "2. Vendor" + "\n" + "3.Employee");
			category_select = Integer.parseInt(br.readLine());
			// moving to the category depending upon user selection
			switch (category_select) {

			case 1:
				// moving to Admin interface
//	rem		System.out.println("Going to admin test");
				AdminPojo adminpojo = new AdminPojo();
				AdminDao admindao = new AdminDao();
				System.out.println("Please Enter Your ID");
				int admin_id = Integer.parseInt(br.readLine());
				System.out.println("Please Enter Your Password");
				String admin_password = br.readLine();
				adminpojo.setId(admin_id);
				adminpojo.setPassword(admin_password);
				// validating admin
				if (admindao.validateAdmin(adminpojo)) {
					System.out.println("Admin Login Succesful");
					// navigating to admin controller for admin operations
					AdminController.adminController(adminpojo);
				} else {
					System.err.println("Incorrect Username/Password");
					Main.main(null);
				}
				break;
			case 2:
				// moving to Vendor interface
//	rem			System.out.println("Going to vendor test");
				int vendor_status;
// After the vendor is added by Admin he should set his stall id,name and password and then only he can perform his operations
				System.out.println("Are You AN Existing Vendor OR A Newly Added Vendor. Please select Below");
				do {
				System.out.println("1.Existing Vendor" + "\n" + "2.Newly Added Vendor" + "\n" + "3.Exit");
				vendor_status = Integer.parseInt(br.readLine());
				if (vendor_status == 1) {
//	rem				System.out.println("Moving to existing vendor");
VendorPojo vendorpojo = new VendorPojo(0, null, 0);
VendorDao vendordao = new VendorDao();
System.out.println("Please Enter Your ID");
int vendor_id = Integer.parseInt(br.readLine());
System.out.println("Please Enter Your Password");
String vendor_password = br.readLine();
vendorpojo.setVendor_id(vendor_id);
vendorpojo.setVendor_password(vendor_password);
if (vendordao.validateExistingVendor(vendorpojo) == true) {
	System.out.println("Login Succesful");
	VendorController.vendorController();
} else {
	System.err.println(
			"OOPS! Your Credentials Are Wrong If You Are A Newly Added Vendor Please Select Option Newly Added Vendor");
	vendor_status = 4;
}
				} else if (vendor_status == 2) {
//	rem				System.out.println("moving to newly added vendor");
					VendorPojo vendorpojo=new VendorPojo(0, null, 0);
					VendorDao vendordao = new VendorDao();
					System.out.println("Please Enter Your vendor_id");
					int vendor_id = Integer.parseInt(br.readLine());
					vendorpojo.setVendor_id(vendor_id);
					if (vendordao.validateNewVendor(vendorpojo) == true) {
						System.out.println("Please Set Your Stall ID");
						int stall_id = Integer.parseInt(br.readLine());
						vendorpojo.setStall_id(stall_id);
						System.out.println("Please Set Your Stall Name");
						String stall_name = br.readLine();
						vendorpojo.setStall_name(stall_name);
						System.out.println("Please Set Your Password");
						String vendor_password = br.readLine();
						vendorpojo.setVendor_password(vendor_password);
						vendordao.addVendorPrimaryDetails(vendorpojo);
						System.out.println("You are now an Existing Vendor");
						vendor_status = 4;
					} else {
						System.err.println("Sorry, You Entered Wrong Vendor ID");
						vendor_status = 4;
					}
				} else if (vendor_status == 3) {
					Main.main(null);
				}
			} while (vendor_status != 1 && vendor_status != 2 && vendor_status != 3);
				break;
			case 3:
				// moving to Employee interface
//	rem			System.out.println("Going to employee test");
				System.out.println("If You Are A New User Please Sign Up");
				int emp_select;
				do {
					System.out.println("1.Sign Up" + "\n" + "2.Login" + "\n" + "3.Exit");
					emp_select = Integer.parseInt(br.readLine());
					if (emp_select == 1) {

						EmployeePojo employeepojo = new EmployeePojo();
						EmployeeDaoImpl employeedao = new EmployeeDaoImpl();
						System.out.println("Please Enter Your Employee ID");
						int emp_id = Integer.parseInt(br.readLine());
						System.out.println("Please Enter Your Name");
						String emp_name = br.readLine();
						System.out.println("Please Enter Your Phone Number");
						String emp_mobilenum = br.readLine();
						System.out.println("Please Enter Your Email");
						String emp_email = br.readLine();
						System.out.println("Please Enter Your Address");
						String emp_address = br.readLine();
						System.out.println("Please Enter Your Password");
						String emp_password = br.readLine();

						employeepojo.setEmp_id(emp_id);
						employeepojo.setEmp_name(emp_name);
						employeepojo.setEmp_mobilenum(emp_mobilenum);
						employeepojo.setEmp_email(emp_email);
						employeepojo.setEmp_address(emp_address);
						employeepojo.setEmp_password(emp_password);

						if (userValidation.validateuser(employeepojo)) {
							employeedao.AddEmployee(employeepojo);
							System.out.println("Your SignUp Succesful Please Login To Continue");
							emp_select = 4;
						}
						else {
							System.err.println("Please Enter A Valid Mobile Number And Email");
							emp_select = 4;
						}

					} else if (emp_select == 2) {

						EmployeePojo employeepojo = new EmployeePojo();
						EmployeeDaoImpl employeedao = new EmployeeDaoImpl();

						System.out.println("Please Enter Your Employee ID");
						int emp_id = Integer.parseInt(br.readLine());
						System.out.println("Please Enter Your Password");
						String emp_pass = br.readLine();

						employeepojo.setEmp_id(emp_id);
						employeepojo.setEmp_password(emp_pass);

						if (employeedao.validateRegisteredEmployee(employeepojo)) {
							System.out.println("Login Succesful");
							EmployeeController.employeeController();
						} else {
							System.err
									.println(
									"Please Enter Your Credentails Correctly. Incase You Didnt SignUp Please SignUp First");
							emp_select = 4;
						}

					} else if (emp_select == 3) {
						Main.main(null);
					}
				} while (emp_select != 1 && emp_select != 2 && emp_select != 3);

				break;
			default:
				System.err.println("please select among 1,2 and 3 options");
			}
		}
		// If user selected faulty option
		while (category_select != 1 && category_select != 2 && category_select != 3);

	}
	}


