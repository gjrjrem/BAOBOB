package spring.mvc.baobob.vo;

public class RestaurantVO {
	private int restaurant_index;	// �����ȣ
	private String restaurant_tel; // ��ȭ��ȣ
	private String restaurant_name; // �����
	
	public int getRestaurant_index() {
		return restaurant_index;
	}
	public void setRestaurant_index(int restaurant_index) {
		this.restaurant_index = restaurant_index;
	}
	public String getRestaurant_tel() {
		return restaurant_tel;
	}
	public void setRestaurant_tel(String restaurant_tel) {
		this.restaurant_tel = restaurant_tel;
	}
	public String getRestaurant_name() {
		return restaurant_name;
	}
	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}
}
