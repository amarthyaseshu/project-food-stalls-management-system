package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import dao.VendorDao;
import model.ItemPojo;
import model.VendorPojo;

public class VendorController {

	public static void vendorController() throws Exception, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int vendor_choice;
		do {
			System.out.println("1.Manage Stall Items" + "\n" + "2.Manage Orders" + "\n" + "3.Exit");
		vendor_choice = Integer.parseInt(br.readLine());
		switch (vendor_choice) {
		// when vendor performs crud operations related to managing stall items
		case 1: 
//	rem		System.out.println("Going To Manage Stall Items");
			int vendor_choice1;
			do {
				System.out.println("1.Add items" + "\n" + "2.Update Items" + "\n" + "3.Remove Items" + "\n"
						+ "4.View Items" + "\n" + "5.Exit");
			vendor_choice1 = Integer.parseInt(br.readLine());
			if(vendor_choice1==1) {
				// adding items by the vendor
//	rem			System.out.println("Going to add items");
				ItemPojo itempojo = new ItemPojo(0, null, 0);
VendorPojo vendorpojo = new VendorPojo(0, null, 0);
VendorDao vendordao = new VendorDao();
int add_other_item;
System.out.println("Please Provide Your Stall ID");
int stall_id = Integer.parseInt(br.readLine());
vendorpojo.setStall_id(stall_id);
do {
System.out.println("Please Provide The Item ID");
int item_id = Integer.parseInt(br.readLine());
System.out.println("Please Provide The Item Name");
String item_name = br.readLine();
System.out.println("Please Provide The Price Of The Item");
int item_price = Integer.parseInt(br.readLine());
itempojo.setItem_id(item_id);
itempojo.setItem_name(item_name);
itempojo.setItem_price(item_price);
vendordao.addItems(vendorpojo, itempojo);
do {
	System.out.println("Do You Want To Add Other Items. Please press 1 for Yes Or 2 for No");
add_other_item = Integer.parseInt(br.readLine());
} while (add_other_item != 1 && add_other_item != 2);
if (add_other_item == 2) {
	vendor_choice1 = 6;
}
} while (add_other_item == 1);
			}
			
			else if(vendor_choice1==2) {
				// update items by the vendor
//	rem			System.out.println("Going to update items");

				ItemPojo itempojo = new ItemPojo(0, null, 0);
				VendorDao vendordao = new VendorDao();
				int update_other_item;

				do {
					System.out.println("Please Provide The Item ID For Which You Need An Update");
					int item_id = Integer.parseInt(br.readLine());
					System.out.println("Please Provide The Item Name");
					String item_name = br.readLine();
					System.out.println("Please Provide The Price Of The Item");
					int item_price = Integer.parseInt(br.readLine());
					itempojo.setItem_id(item_id);
					itempojo.setItem_name(item_name);
					itempojo.setItem_price(item_price);
					vendordao.updateItems(itempojo);
					do {
						System.out.println("Do You Want To Update Other Items. Please press 1 for Yes Or 2 for No");
						update_other_item = Integer.parseInt(br.readLine());
					} while (update_other_item != 1 && update_other_item != 2);
					if (update_other_item == 2) {
						vendor_choice1 = 6;
					}
				} while (update_other_item == 1);

			}
			else if (vendor_choice1 == 3) {
				// remove items by the vendor
//	rem			System.out.println("Going to remove items");
int remove_other_item;
ItemPojo itempojo = new ItemPojo(0, null, 0);
VendorDao vendordao = new VendorDao();
do {
System.out.println("Please Enter The ID Of The Item You Want To Remove");
int remove_item_id = Integer.parseInt(br.readLine());
itempojo.setItem_id(remove_item_id);
vendordao.removeItems(itempojo);
do {
	System.out.println("Do You Want To Remove Other Items. Please press 1 for Yes Or 2 for No");
	remove_other_item = Integer.parseInt(br.readLine());
} while (remove_other_item != 1 && remove_other_item != 2);
if (remove_other_item == 2) {
	vendor_choice1 = 6;
}
} while (remove_other_item == 1);
			} else if (vendor_choice1 == 4) {
				// going to view items for the vendor
//		rem		System.out.println("Going to view items");
				VendorPojo vendorpojo = new VendorPojo(0, null, 0);
				VendorDao vendordao = new VendorDao();
				System.out.println("Please Enter Your Stall ID");

				int stall_id = Integer.parseInt(br.readLine());
				vendorpojo.setStall_id(stall_id);
//	rem			vendordao.viewItems(vendorpojo);
				ArrayList<ItemPojo> viewing_items1 = new ArrayList<>();
				viewing_items1 = vendordao.viewItems(vendorpojo);
				System.out.format("%-25s%s%25s%n", "Item ID", "Item Name", "Item Price");
				for (ItemPojo items_display : viewing_items1) {

					System.out.format("%-25s%s%25s%n", items_display.getItem_id(), items_display.getItem_name(),
							items_display.getItem_price());
				}
				vendor_choice1 = 6;
			}
			else if (vendor_choice1 == 5) {
				// If vendor choose to exit
				VendorController.vendorController();
			}
		} while (vendor_choice1 != 1 && vendor_choice1 != 2 && vendor_choice1 != 3 && vendor_choice1 != 4
				&& vendor_choice1 != 5);

			break;

		case 2: 
//	rem		System.out.println("Going To Manage Orders");
break;
		
		case 3: 
			Main.main(null);
			break;

		default: 
			System.err.println("Please Enter The Option Correctly");
			vendor_choice = 4;
		
		}
		
	} while (vendor_choice != 1 && vendor_choice != 2 && vendor_choice != 3);
	}
}
