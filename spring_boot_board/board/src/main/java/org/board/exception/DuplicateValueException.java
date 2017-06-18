package org.board.exception;

/**
 * 동일한 값 예외 클래스
 * 
 * @author 	:	Zaccoding
 * @date 	: 	2017. 4. 16.
 */
public class DuplicateValueException extends Exception {	
	private static final long serialVersionUID = 1L;
	
	public DuplicateValueException(String message) {
		super(message);
	}

}
