package com.web.dto;

import java.util.List;

import com.web.utils.GsonUtil;

/**
 * 각 column 이름 + title을 담는 DTO 클래스
 * 
 * @author zaccoding
 * @date 2017. 8. 29.
 *
 */
public class DomainDTO {
	private String name;
	private List<String> columns;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getColumns() {
		return columns;
	}
	public void setColumns(List<String> columns) {
		this.columns = columns;
	}
	
	@Override
	public String toString() {
		return GsonUtil.getGsonForToString().toJson(this);
	}	
}
