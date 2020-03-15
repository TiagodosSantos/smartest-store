package com.smartest.store.exception;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	@Autowired
	private MessageSource messageSource;

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
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public List<ExceptionDetails> handle(MethodArgumentNotValidException exception) {
		List<ExceptionDetails> listError = new ArrayList<>();
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ExceptionDetails erro = new ExceptionDetails(message, e.getField(), null);
			listError.add(erro);
		});
		
		return listError;
	}

}
