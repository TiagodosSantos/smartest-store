package com.smartest.store.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * This class is used to configure the exceptions thrown by the application
 * 
 * @author Tiago Santos
 * @since   2020-03-10
 * 
 */
@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionDetails handleResourceNotFound(final ResourceNotFoundException exception,
			final HttpServletRequest request) {
		return new ExceptionDetails(exception.getMessage(), request.getRequestURI());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ExceptionDetails handleException(final Exception exception,
			final HttpServletRequest request) {
		return new ExceptionDetails(exception.getMessage(), request.getRequestURI());
	}

}
