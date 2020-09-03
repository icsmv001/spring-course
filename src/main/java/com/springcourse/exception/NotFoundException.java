package com.springcourse.exception;

public class NotFoundException extends RuntimeException {
	
	/**
	 * criado classe de exception, para trafegar mensagems de erro por excetio
	 * usando um construtor para envio das msg
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// construtura para montar mensagems
	public NotFoundException(String msg) {
		super(msg);
		
	}
}
