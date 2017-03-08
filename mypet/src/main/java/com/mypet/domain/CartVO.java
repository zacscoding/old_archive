package com.mypet.domain;

import java.util.Date;

public class CartVO {
	//장바구니에 담길 상품 
	private int cart_seq;
	private int user_seq_fk;
	private int product_no_fk;
	private int count;
	private String is_complete;
	private Date reg_date;
	
	//상품 정보
	private String product_name;
	private int selling_price;
	private String fileMain;
	
	//getter, setter
	public int getCart_seq() {
		return cart_seq;
	}
	public void setCart_seq(int cart_seq) {
		this.cart_seq = cart_seq;
	}
	public int getUser_seq_fk() {
		return user_seq_fk;
	}
	public void setUser_seq_fk(int user_seq_fk) {
		this.user_seq_fk = user_seq_fk;
	}
	public int getProduct_no_fk() {
		return product_no_fk;
	}
	public void setProduct_no_fk(int product_no_fk) {
		this.product_no_fk = product_no_fk;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getIs_complete() {
		return is_complete;
	}
	public void setIs_complete(String is_complete) {
		this.is_complete = is_complete;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getSelling_price() {
		return selling_price;
	}
	public void setSelling_price(int selling_price) {
		this.selling_price = selling_price;
	}
	public String getFileMain() {
		return fileMain;
	}
	public void setFileMain(String fileMain) {
		this.fileMain = fileMain;
	}
	@Override
	public String toString() {
		return "CartVO [cart_seq=" + cart_seq + ", user_seq_fk=" + user_seq_fk + ", product_no_fk=" + product_no_fk
				+ ", count=" + count + ", is_complete=" + is_complete + ", reg_date=" + reg_date + ", product_name="
				+ product_name + ", selling_price=" + selling_price + ", fileMain=" + fileMain + "]";
	}
	
	
	
	
}
