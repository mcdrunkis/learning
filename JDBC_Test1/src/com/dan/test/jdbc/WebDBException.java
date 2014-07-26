package com.dan.test.jdbc;

public class WebDBException extends Exception {
	public static final long serialVersionUID = 1L;
	
	public WebDBException(String message) {
		super(message);
	}
	
	public WebDBException(String message,Throwable cause) { 
		super(message,cause);
	}
}
