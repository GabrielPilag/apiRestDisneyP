package com.disney.apiRest2.handlers;

public class NotFoundException extends RuntimeException{

	public NotFoundException(String s) {
		super(s);
	}
}
