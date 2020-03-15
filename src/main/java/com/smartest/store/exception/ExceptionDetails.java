package com.smartest.store.exception;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * This class represents the details of the exceptions thrown by the application
 * 
 * @author Tiago Santos
 * @since   2020-03-10
 * 
 */
public class ExceptionDetails {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
	private Calendar errorDate;
	private String errorMessage;
	private String requestedURI;
	private String errorField;
	
	

	public ExceptionDetails(String errorMessage, String requestedURI) {
		this.errorDate = Calendar.getInstance();
		this.errorMessage = errorMessage;
		this.requestedURI = requestedURI;
	}
	
	public ExceptionDetails(String errorMessage, String errorField, String requestedURI) {
		this.errorDate = Calendar.getInstance();
		this.errorMessage = errorMessage;
		this.errorField = errorField;
		this.requestedURI = requestedURI;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getRequestedURI() {
		return requestedURI;
	}

	public void callerURL(final String requestedURI) {
		this.requestedURI = requestedURI;
	}
	
	public Calendar getErrorDate() {
		return errorDate;
	}
	
	public void setErrorDate(Calendar errorDate) {
		this.errorDate = errorDate;
	}
	
	public String getErrorField() {
		return errorField;
	}
	
	public void setErrorField(String errorField) {
		this.errorField = errorField;
	}
	
}
