package com.learning.exception;

import lombok.ToString;

@ToString(callSuper = true)
public class IdNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7470843321441582798L;

	public IdNotFoundException(String message) {
		super(message);
	}

}
