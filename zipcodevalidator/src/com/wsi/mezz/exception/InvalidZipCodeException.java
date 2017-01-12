package com.wsi.mezz.exception;

public class InvalidZipCodeException extends Exception {
	
	public InvalidZipCodeException(String errMessage){
		super(errMessage);
	}
}
