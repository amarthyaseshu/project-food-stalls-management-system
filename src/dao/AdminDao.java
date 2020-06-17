
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.AdminPojo;
import model.VendorPojo;
import utility.ConnectionManager;

public class AdminDao {
// For validating the admin
	public boolean validateAdmin(AdminPojo adminpojo) throws ClassNotFoundException, SQLException {

		int admin_id = adminpojo.getId();
		String admin_password = adminpojo.getPassword();

		String sql = "select * from admin";
		Statement st = ConnectionManager.getConnection().createStatement();

		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			if (admin_id == rs.getInt("id") && admin_password.equalsIgnoreCase(rs.getString("password"))) {
				ConnectionManager.getConnection().close();
				return true;
			} else {
				ConnectionManager.getConnection().close();
				return false;
			}
		}
		return false;
	}

// for adding vendors by admin
	public void addVendor(VendorPojo vendorpojo, AdminPojo adminpojo) throws ClassNotFoundException, SQLException {

		int vendor_id = vendorpojo.getVendor_id();
		String vendor_name = vendorpojo.getVendor_name();
		
		int admin_id = adminpojo.getId();
		String admin_name = "amarthya";
		String admin_password = adminpojo.getPassword();
		String sql = "insert into vendor(ID,NAME)VALUES(?,?)";

		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql);
		ps.setInt(1, vendor_id);
		ps.setString(2, vendor_name);
		ps.executeUpdate();


		String sql1 = "insert into admin(ID,NAME,VENDOR_ID,PASSWORD)VALUES(?,?,?,?)";

		PreparedStatement ps1 = ConnectionManager.getConnection().prepareStatement(sql1);

		ps1.setInt(1, admin_id);
		ps1.setString(2, admin_name);
		ps1.setInt(3, vendor_id);
		ps1.setString(4, admin_password);

		ps1.executeUpdate();

		ConnectionManager.getConnection().close();

	}

	// for removing vendors by admin
	public void removeVendor(VendorPojo vendorpojo1) throws ClassNotFoundException, SQLException {
		int vendor_id = vendorpojo1.getVendor_id();


		String sql1 = "delete from admin where vendor_id=?";

		PreparedStatement ps1 = ConnectionManager.getConnection().prepareStatement(sql1);

		ps1.setInt(1, vendor_id);

		ps1.executeUpdate();

		String sql = "delete from vendor where id=?";

		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql);

		ps.setInt(1, vendor_id);
		ps.executeUpdate();
		ConnectionManager.getConnection().close();

	}

// for viewing vendors by admin
	public ArrayList<VendorPojo> viewVendor(VendorPojo vendorpojo2) throws ClassNotFoundException, SQLException {
		ArrayList<VendorPojo> viewing_vendor = new ArrayList<>();
		String sql = "select * from vendor";

		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int disp_vendor_id = rs.getInt("id");
			String disp_vendor_name = rs.getString("name");
			int disp_stall_id = rs.getInt("stall_id");
			viewing_vendor.add(new VendorPojo(disp_vendor_id, disp_vendor_name, disp_stall_id));
		}
		return viewing_vendor;

	}

}
