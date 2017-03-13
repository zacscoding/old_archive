package com.mypet.domain;

import java.util.Date;

public class CartVO {
	//장바구니에 담길 상품 
	private Integer cart_seq;
	private Integer user_no_fk;
	private Integer product_no_fk;
	private Integer count;
	private String is_complete;
	private Date reg_date;
	
	//상품 정보
	private String product_name;
	private Integer product_no;
	private Integer selling_price;
	private String filesMain;

	public Integer getCart_seq() {
		return cart_seq;
	}

	public void setCart_seq(Integer cart_seq) {
		this.cart_seq = cart_seq;
	}

	public Integer getUser_no_fk() {
		return user_no_fk;
	}

	public void setUser_no_fk(Integer user_no_fk) {
		this.user_no_fk = user_no_fk;
	}

	public Integer getProduct_no_fk() {
		return product_no_fk;
	}

	public void setProduct_no_fk(Integer product_no_fk) {
		this.product_no_fk = product_no_fk;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
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

	public Integer getProduct_no() {
		return product_no;
	}

	public void setProduct_no(Integer product_no) {
		this.product_no = product_no;
	}

	public Integer getSelling_price() {
		return selling_price;
	}

	public void setSelling_price(Integer selling_price) {
		this.selling_price = selling_price;
	}

	public String getFilesMain() {
		return filesMain;
	}

	public void setFilesMain(String filesMain) {
		this.filesMain = filesMain;
	}

	@Override
	public String toString() {
		return "CartVO [cart_seq=" + cart_seq + ", user_no_fk=" + user_no_fk + ", product_no_fk=" + product_no_fk
				+ ", count=" + count + ", is_complete=" + is_complete + ", reg_date=" + reg_date + ", product_name="
				+ product_name + ", product_no=" + product_no + ", selling_price=" + selling_price + ", filesMain="
				+ filesMain + "]";
	}


}
