package com.mypet.domain;

import java.util.Date;
import java.util.List;

public class OrderVO {	
	/*	for tbl_order	*/
	private Integer order_no;
	private String user_id_fk;
	private Date order_date;
	
	/*	for tbl_order_detail	*/
	private List<OrderDetailVO> detailList;

	/*	getter,setter,toString	*/
	public Integer getOrder_no() {
		return order_no;
	}

	public void setOrder_no(Integer order_no) {
		this.order_no = order_no;
	}

	public String getUser_id_fk() {
		return user_id_fk;
	}

	public void setUser_id_fk(String user_id_fk) {
		this.user_id_fk = user_id_fk;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public List<OrderDetailVO> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<OrderDetailVO> detailList) {
		this.detailList = detailList;
	}

	@Override
	public String toString() {
		return "OrderVO [order_no=" + order_no + ", user_id_fk=" + user_id_fk + ", order_date=" + order_date
				+ ", detailList=" + detailList + "]";
	}
}
