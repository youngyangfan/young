package com.young.domain;

public class Book {
	// ͼ��ID
	private Integer id;
	// ͼ������
	private String name;
	// ͼ��۸�
	private Float price;
	// ͼ��ͼƬ
	private String pic;
	// ͼ������
	private String description;

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", price=" + price + ", pic=" + pic + ", description=" + description + "]";
	}
	public Book() {
		
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
