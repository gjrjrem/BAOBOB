package spring.mvc.baobob.vo;

public class MenuVO {
	private int index;		// 레스토랑_인덱스
	private String img;		// 이미지
	private String name;	// 메뉴명
	private String content;	// 메뉴 설명
	private int price;		// 가격

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
