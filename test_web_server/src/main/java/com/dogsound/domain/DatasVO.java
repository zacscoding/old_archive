package com.dogsound.domain;

import java.math.BigDecimal;
import java.util.Date;

public class DatasVO {		
	private String stringCol;
	private Boolean booleanCol;
	private Short shortCol;
	private Integer integerCol;
	private Long longCol;
	private Float floatCol;	
	private Double doubleCol;
	private BigDecimal bigdecimalCol;
	private Date timestampCol;
	
	public String getStringCol() {
		return stringCol;
	}
	public void setStringCol(String stringCol) {
		this.stringCol = stringCol;
	}
	public Boolean getBooleanCol() {
		return booleanCol;
	}
	public void setBooleanCol(Boolean booleanCol) {
		this.booleanCol = booleanCol;
	}
	public Short getShortCol() {
		return shortCol;
	}
	public void setShortCol(Short shortCol) {
		this.shortCol = shortCol;
	}
	public Integer getIntegerCol() {
		return integerCol;
	}
	public void setIntegerCol(Integer integerCol) {
		this.integerCol = integerCol;
	}
	public Long getLongCol() {
		return longCol;
	}
	public void setLongCol(Long longCol) {
		this.longCol = longCol;
	}
	public Float getFloatCol() {
		return floatCol;
	}
	public void setFloatCol(Float floatCol) {
		this.floatCol = floatCol;
	}
	public Double getDoubleCol() {
		return doubleCol;
	}
	public void setDoubleCol(Double doubleCol) {
		this.doubleCol = doubleCol;
	}
	public BigDecimal getBigdecimalCol() {
		return bigdecimalCol;
	}
	public void setBigdecimalCol(BigDecimal bigdecimalCol) {
		this.bigdecimalCol = bigdecimalCol;
	}
	public Date getTimestampCol() {
		return timestampCol;
	}
	public void setTimestampCol(Date timestampCol) {
		this.timestampCol = timestampCol;
	}
	@Override
	public String toString() {
		return "DatasVO [stringCol=" + stringCol + ", booleanCol=" + booleanCol + ", shortCol=" + shortCol
				+ ", integerCol=" + integerCol + ", longCol=" + longCol + ", floatCol=" + floatCol + ", doubleCol="
				+ doubleCol + ", bigdecimalCol=" + bigdecimalCol + ", timestampCol=" + timestampCol + "]";
	}
}
