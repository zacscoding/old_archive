package com.mypet.domain;

import java.util.Arrays;
import java.util.Date;

public class ProductVO {
	
	private Integer product_no;
	private String product_name;
	private Integer category_no_fk;
	private Integer cost_price;
	private Integer selling_price;
	private Integer profit;
	private Integer qty;
	private Integer hit;
	private Character is_best;
	private String filesMain;
	private Date reg_date;
	private String[] files;
	private String content;
	
	public Integer getProduct_no() {
		return product_no;
	}
	public void setProduct_no(Integer product_no) {
		this.product_no = product_no;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public Integer getCategory_no_fk() {
		return category_no_fk;
	}
	public void setCategory_no_fk(Integer category_no_fk) {
		this.category_no_fk = category_no_fk;
	}
	public Integer getCost_price() {
		return cost_price;
	}
	public void setCost_price(Integer cost_price) {
		this.cost_price = cost_price;
	}
	public Integer getSelling_price() {
		return selling_price;
	}
	public void setSelling_price(Integer selling_price) {
		this.selling_price = selling_price;
	}
	public Integer getProfit() {
		return profit;
	}
	public void setProfit(Integer profit) {
		this.profit = profit;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Integer getHit() {
		return hit;
	}
	public void setHit(Integer hit) {
		this.hit = hit;
	}
	public Character getIs_best() {
		return is_best;
	}
	public void setIs_best(Character is_best) {
		this.is_best = is_best;
	}
	public String getFilesMain() {
		return filesMain;
	}
	public void setFilesMain(String filesMain) {
		this.filesMain = filesMain;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public String[] getFiles() {
		return files;
	}
	public void setFiles(String[] files) {
		this.files = files;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "ProductVO [product_no=" + product_no + ", product_name=" + product_name + ", category_no_fk="
				+ category_no_fk + ", cost_price=" + cost_price + ", selling_price=" + selling_price + ", profit="
				+ profit + ", qty=" + qty + ", hit=" + hit + ", is_best=" + is_best + ", filesMain=" + filesMain
				+ ", reg_date=" + reg_date + ", files=" + Arrays.toString(files) + ", content=" + content + "]";
	}
	
	
	
}
