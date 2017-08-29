package com.web.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.web.annotation.Columns;
import com.web.utils.GsonUtil;

public class Oracle11g {
	@Columns("varchar2_col")
	private String stringCol;
	@Columns("boolean_col")
	private Boolean booleanCol;
	@Columns("short_col")
	private Short shortCol;
	@Columns("integer_col")
	private Integer integerCol;
	@Columns("long_col")
	private Long longCol;
	@Columns("float_col")
	private Float floatCol;
	@Columns("double_col")
	private Double doubleCol;
	@Columns("bigdecimal_col")
	private BigDecimal bigDecimalCol;
	@Columns("timestamp_col")
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
	public BigDecimal getBigDecimalCol() {
		return bigDecimalCol;
	}
	public void setBigDecimalCol(BigDecimal bigDecimalCol) {
		this.bigDecimalCol = bigDecimalCol;
	}
	public Date getTimestampCol() {
		return timestampCol;
	}
	public void setTimestampCol(Date timestampCol) {
		this.timestampCol = timestampCol;
	}
	
	@Override
	public String toString() {
		return GsonUtil.getGsonForToString().toJson(this);
	}
}
