package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ItemPojo;
import model.VendorPojo;
import utility.ConnectionManager;

public class VendorDao {

// validating the vendor based on vendor id in starting stage for newly added vendor

public boolean validateNewVendor(VendorPojo vendorpojo) throws ClassNotFoundException, SQLException {
		int vendor_id = vendorpojo.getVendor_id();
		String sql = "select * from vendor";
		PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {

			if (vendor_id == rs.getInt("id")) {

				return true;
			}
		}
		return false;
	}

	// adding vendor primary details

	public void addVendorPrimaryDetails(VendorPojo vendorpojo) throws ClassNotFoundException, SQLException {

		int vendor_id = vendorpojo.getVendor_id();
		int stall_id = vendorpojo.getStall_id();
		String vendor_password = vendorpojo.getVendor_password();
		String stall_name = vendorpojo.getStall_name();
		String sql1 = "insert into stall (id,name,vendor_id)values(?,?,?)";

		PreparedStatement ps1 = ConnectionManager.getConnection().prepareStatement(sql1);
		ps1.setInt(1, stall_id);
		ps1.setString(2, stall_name);
		ps1.setInt(3, vendor_id);
		ps1.executeUpdate();

		String sql = "update vendor set STALL_ID=?,PASSWORD=? WHERE ID=? ";

		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql);
		ps.setInt(1, stall_id);
		ps.setString(2, vendor_password);
		ps.setInt(3, vendor_id);
		ps.executeUpdate();
		ConnectionManager.getConnection().close();

	}

	// validating the Existing Vendor

	public boolean validateExistingVendor(VendorPojo vendorpojo) throws ClassNotFoundException, SQLException {
		int vendor_id = vendorpojo.getVendor_id();
		String vendor_password = vendorpojo.getVendor_password();
		String sql = "select * from vendor";
		PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {

			if (vendor_id == rs.getInt("id") && vendor_password.equalsIgnoreCase(rs.getString("password"))) {

				return true;
			}
		}
		return false;
	}

	// adding items by vendor

	public void addItems(VendorPojo vendorpojo, ItemPojo itempojo) throws ClassNotFoundException, SQLException {

		int stall_id = vendorpojo.getStall_id();
		int item_id = itempojo.getItem_id();
		String item_name = itempojo.getItem_name();
		int item_price = itempojo.getItem_price();

		String sql = "insert into item(ID,NAME,STALL_ID,PRICE)VALUES(?,?,?,?)";

		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql);
		ps.setInt(1, item_id);
		ps.setString(2, item_name);
		ps.setInt(3, stall_id);
		ps.setInt(4, item_price);
		ps.executeUpdate();
		ConnectionManager.getConnection().close();
	}

	// removing items by the vendor

	public void removeItems(ItemPojo itempojo) throws ClassNotFoundException, SQLException {

		int remove_item_id = itempojo.getItem_id();

		String sql = "delete from item where id=?";

		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql);

		ps.setInt(1, remove_item_id);

		ps.executeUpdate();

		ConnectionManager.getConnection().close();
	}

	// updating items by the vendor

	public void updateItems(ItemPojo itempojo) throws ClassNotFoundException, SQLException {
		int item_id = itempojo.getItem_id();
		String item_name = itempojo.getItem_name();
		int item_price = itempojo.getItem_price();
		String sql = "update item set NAME=?,PRICE=? WHERE ID=? ";

		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql);
		ps.setString(1, item_name);
		ps.setInt(2, item_price);
		ps.setInt(3, item_id);
		ps.executeUpdate();

		ConnectionManager.getConnection().close();
	}

	// view items by the vendor

	public ArrayList<ItemPojo> viewItems(VendorPojo vendorpojo) throws ClassNotFoundException, SQLException {
		ArrayList<ItemPojo> viewing_items = new ArrayList<>();
		int stall_id = vendorpojo.getStall_id();

		String sql = "select * from item where stall_id=?";

		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql);

		ps.setInt(1, stall_id);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int disp_item_id = rs.getInt("id");
			String disp_item_name = rs.getString("name");
			int disp_item_price = rs.getInt("price");
			viewing_items.add(new ItemPojo(disp_item_id, disp_item_name, disp_item_price));
		}
		return viewing_items;
	}


}
