package com.productcatalog.exception;

public class ResourceNotFoundException extends CustomException {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param msg
	 * Resource not found custom exception class
	 */
	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
