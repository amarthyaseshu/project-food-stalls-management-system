package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import dao.AdminDao;
import model.AdminPojo;
import model.VendorPojo;

public class AdminController {

	public static void adminController(AdminPojo adminpojo) throws IOException, Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int admin_category;

		do {
		System.out.println("Please select the below category");
		System.out.println("1.Add Vendors" + "\n" + " 2.Remove Vendors" + "\n" + "3.View Vendors" + "\n" + "4.Exit");

		admin_category = Integer.parseInt(br.readLine());

		switch (admin_category) {
		// to add vendors
		case 1:
//	remove		System.out.println("going to add vendors");
			VendorPojo vendorpojo = new VendorPojo(0, null, 0);
			AdminDao admindao = new AdminDao();
			int add_other_vendor;
			do {
			System.out.println("Please Enter The ID Of The Vendor");
			int vendor_id = Integer.parseInt(br.readLine());
			System.out.println("Please Enter the Name Of The Vendor");
			String vendor_name = br.readLine();
			vendorpojo.setVendor_id(vendor_id);
			vendorpojo.setVendor_name(vendor_name);
			admindao.addVendor(vendorpojo, adminpojo);
			do {
			System.out.println("Do You Want To Add Another Vendor" + "\n" + "Please press 1 for Yes or 2 For No");
			add_other_vendor = Integer.parseInt(br.readLine());
		} while (add_other_vendor != 1 && add_other_vendor != 2);
		if (add_other_vendor == 2)
			AdminController.adminController(adminpojo);
	} while (add_other_vendor == 1);
			break;
		// to remove vendors
		case 2:
//	remove		System.out.println("going to remove vendors");
			AdminDao admindao1 = new AdminDao();
			VendorPojo vendorpojo1 = new VendorPojo(0, null, 0);
			int remove_other_vendor;
			do {
			System.out.println("Please Enter The ID Of The Vendor You Want To Remove");
			int vendor_id = Integer.parseInt(br.readLine());

			vendorpojo1.setVendor_id(vendor_id);
			admindao1.removeVendor(vendorpojo1);
			do {
				System.out
						.println("Do You Want To Remove Another Vendor" + "\n" + "Please press 1 for Yes or 2 For No");
				remove_other_vendor = Integer.parseInt(br.readLine());
			} while (remove_other_vendor != 1 && remove_other_vendor != 2);
			if (remove_other_vendor == 2)
				AdminController.adminController(adminpojo);
		} while (remove_other_vendor == 1);

			break;
		// to view vendors
		case 3:
//	rem		System.out.println("going to view vendors");

			AdminDao admindao2 = new AdminDao();
			VendorPojo vendorpojo2 = new VendorPojo(0, null, 0);
			ArrayList<VendorPojo> viewing_vendor1 = new ArrayList<>();
			viewing_vendor1 = admindao2.viewVendor(vendorpojo2);
			System.out.println("Please note that the Stall ID will be zero if the vendor didnt set the stall id");
			System.out.format("%-25s%s%25s%n", "VendorID", "VendorName", "VendorsStallID");
			for (VendorPojo vendor_display : viewing_vendor1) {

				System.out.format(
						"%-25s%s%25s%n",
						vendor_display.getVendor_id(),
						vendor_display.getVendor_name(),
						vendor_display.getStall_id());
			}
					AdminController.adminController(adminpojo);
			break;
		// Exit
		case 4:
			Main.main(null);
			break;
		default:
			System.err.println("Please Select Category Correctly");
		}
	} while (admin_category != 1 && admin_category != 2 && admin_category != 3 && admin_category != 4);
	}
}
