package org.board.domain;

/**
 * 게시글 카테고리 Domain 클래스
 * 
 * @author zaccoding
 * @date 2017. 5. 20.
 */
public class CategoryVO {
	
	/*=================================
	 * Member Fields
	 *================================= */	
	/**
	 * 카테고리 시퀀스
	 */
	private int cateNo;
	/**
	 * 카테고리 이름
	 */
	private String name;
	/**
	 * 부모 시퀀스
	 */
	private int parentNo;
	
	
	/*=================================
	 * Setters , Getters, toString
	 *================================= */
	public int getCateNo() {
		return cateNo;
	}
	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentNo() {
		return parentNo;
	}
	public void setParentNo(int parentNo) {
		this.parentNo = parentNo;
	}
	@Override
	public String toString() {
		return "CategoryVO [cateNo=" + cateNo + ", name=" + name + ", parentNo=" + parentNo + "]";
	}
	
	
	

}
