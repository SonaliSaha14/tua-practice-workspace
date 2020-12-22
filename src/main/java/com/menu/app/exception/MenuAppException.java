package com.menu.app.exception;

public class MenuAppException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 836739361382939170L;
	
	public MenuAppException(String errorMessage){
		super(errorMessage);
	}

}
