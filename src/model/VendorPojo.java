package model;

public class VendorPojo {

	private int vendor_id;
	private String vendor_name;
	private int stall_id;
	private String vendor_password;
	private String stall_name;

	public String getStall_name() {
		return stall_name;
	}

	public void setStall_name(String stall_name) {
		this.stall_name = stall_name;
	}

	public String getVendor_password() {
		return vendor_password;
	}

	public void setVendor_password(String vendor_password) {
		this.vendor_password = vendor_password;
	}

	public int getVendor_id() {
		return vendor_id;
	}

	public void setVendor_id(int vendor_id) {
		this.vendor_id = vendor_id;
	}

	public String getVendor_name() {
		return vendor_name;
	}

	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
	}

	public int getStall_id() {
		return stall_id;
	}

	public void setStall_id(int stall_id) {
		this.stall_id = stall_id;
	}

	public VendorPojo(int vendor_id, String vendor_name, int stall_id) {
		super();
		this.vendor_id = vendor_id;
		this.vendor_name = vendor_name;
		this.stall_id = stall_id;
	}


}
