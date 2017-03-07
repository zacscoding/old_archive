package com.mypet.domain;

public class OrderDetailVO {
	
	private Integer order_detail_no;
	private Integer order_no_fk;
	private Integer product_no_fk;
	private Integer quantity;
	private String result;
	
	
	/*	getter,setter,toString	*/
	
	public Integer getOrder_detail_no() {
		return order_detail_no;
	}
	public void setOrder_detail_no(Integer order_detail_no) {
		this.order_detail_no = order_detail_no;
	}
	public Integer getOrder_no_fk() {
		return order_no_fk;
	}
	public void setOrder_no_fk(Integer order_no_fk) {
		this.order_no_fk = order_no_fk;
	}
	public Integer getProduct_no_fk() {
		return product_no_fk;
	}
	public void setProduct_no_fk(Integer product_no_fk) {
		this.product_no_fk = product_no_fk;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "OrderDetailVO [order_detail_no=" + order_detail_no + ", order_no_fk=" + order_no_fk + ", product_no_fk="
				+ product_no_fk + ", quantity=" + quantity + ", result=" + result + "]";
	}
	
	
	
	
	

}
