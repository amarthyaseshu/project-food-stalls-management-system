package model;

public class ItemPojo {
	int item_id;
	String item_name;
	int item_price;

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public int getItem_price() {
		return item_price;
	}

	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}

	public ItemPojo(int item_id, String item_name, int item_price) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.item_price = item_price;
	}

}
