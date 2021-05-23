package com.douzone.bookmall.vo;

public class CartVo {
	private String name;
	private String title;
	private int count;
	private int price;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "CartVo [name=" + name + ", title=" + title + ", count=" + count + ", price=" + price + "]";
	}

}
