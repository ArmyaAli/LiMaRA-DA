package com.kingmushroom.main;
//Author: Ali Umar
import javax.servlet.ServletException;

public class BadRequestException extends ServletException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadRequestException() {
		// TODO Auto-generated constructor stub
	}

	public BadRequestException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BadRequestException(Throwable rootCause) {
		super(rootCause);
		// TODO Auto-generated constructor stub
	}

	public BadRequestException(String message, Throwable rootCause) {
		super(message, rootCause);
		// TODO Auto-generated constructor stub
	}

}
