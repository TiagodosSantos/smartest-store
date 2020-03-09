package com.smartest.store.exception;


/**
 * 
 * This class represents the exception thrown as a result of data or resource not found. 
 * 
 * @author Tiago Santos
 * @since   2020-03-10
 * 
 */
public class ResourceNotFoundException extends Exception {

	private static final long serialVersionUID = -9079454849611061074L;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(final String message) {
		super(message);
	}

}
