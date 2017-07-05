package com.dogsound.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * data type ref : 
 * http://nowonbun.tistory.com/147
 * https://docs.microsoft.com/en-us/sql/connect/jdbc/using-basic-data-types
 * http://chozzahacker.blogspot.kr/2014/05/TypeConvert.html 
 */

public class DBDataTypeUtil {	
	public static final List<String> dataList = new ArrayList<String>(); 		
	public static final String[] dataTypes = {
			"varchar2_col",
			"boolean_col",
			"short_col",
			"integer_col",
			"long_col",
			"float_col",			
			"double_col",
			"bigdecimal_col",
			"timestamp_col",			
	};
	
	public static final Object[] dataValues = {
			"varchar2 records",
			new Boolean(true),
			new Short((short)1),
			Integer.valueOf(11),
			Long.valueOf(22L),
			Float.valueOf(0.123f),
			Double.valueOf(0.3344),
			new BigDecimal("0.123456"),
			new Date()			
	};	
	
	static {
		for( String dataType : dataTypes ) {
			dataList.add(dataType);
		}		
	}
	
	public static List<String> getDataList() {		
		return dataList;
	}

}
