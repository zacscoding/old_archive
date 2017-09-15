package com.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * JSP 뷰에서 보여 줄 컬럼 이름
 * e.g) Oracle11g 클래스의 stringCol 필드 == varchar2_col 로 체크박스
 * 		==> @Columns("varchar2_col") private String stringCol;
 * 
 * @author zaccoding
 * @date 2017. 8. 29.
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Columns {
	String value() default "";
}
