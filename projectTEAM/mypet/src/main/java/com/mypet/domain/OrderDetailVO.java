package com.mypet.domain;

public class OrderDetailVO {
	//order에 담길 필드
	private Integer order_detail_no;
	private Integer order_no_fk;
	private Integer product_no_fk;
	private Integer quantity;
	private String mname;	//주문자
	
	//상품 정보
	private String product_name;	//상품명
	private int selling_price;		//상품 가격(단가)
	private String filesMain;		//상품이미지
	
	private String zipNum;	//우편번호
	private String address;	//주소
	private String phone;	//연락처
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
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
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
		return filesMain;
	}
	public void setFileMain(String fileMain) {
		this.filesMain = fileMain;
	}
	public String getZipNum() {
		return zipNum;
	}
	public void setZipNum(String zipNum) {
		this.zipNum = zipNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
				+ product_no_fk + ", quantity=" + quantity + ", mname=" + mname + ", product_name=" + product_name
				+ ", selling_price=" + selling_price + ", fileMain=" + filesMain + ", zipNum=" + zipNum + ", address="
				+ address + ", phone=" + phone + ", result=" + result + "]";
	}
	
	
}
