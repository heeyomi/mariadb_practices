package com.douzone.bookmall.vo;

public class OrderVo {
	private int orderNo; //주문 번호
	private String orderer;
	private int payPrice;
	private String shipDestination;
	
	private int orderBookNo; //주문도서 번호
	private String title; 
	private int quantity;
	private int bookNo; //도서 번호
	
	
	public int getOrderBookNo() {
		return orderBookNo;
	}
	public void setOrderBookNo(int orderBookNo) {
		this.orderBookNo = orderBookNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getPayPrice() {
		return payPrice;
	}
	public void setPayPrice(int payPrice) {
		this.payPrice = payPrice;
	}
	public String getShipDestination() {
		return shipDestination;
	}
	public void setShipDestination(String shipDestination) {
		this.shipDestination = shipDestination;
	}
	
	public String getOrderer() {
		return orderer;
	}
	public void setOrderer(String orderer) {
		this.orderer = orderer;
	}
	
	
	@Override
	public String toString() {
		return "OrderVo [orderNo=" + orderNo + ", orderer=" + orderer + ", payPrice=" + payPrice + ", shipDestination="
				+ shipDestination + "]";
	}
	

}
