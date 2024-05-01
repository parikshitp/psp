package com.divergentsl.psp.exception.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.divergentsl.psp.domain.ApiError;
import com.divergentsl.psp.domain.ApiSubError;
import com.divergentsl.psp.domain.ApiValidationError;
import com.divergentsl.psp.exception.EntityNotFoundException;
import com.divergentsl.psp.exception.GenericException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	private MessageSource messageSource;

	public GlobalExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * Handle generic exception
	 *
	 * @param locale
	 * @param ex
	 * @return ApiError
	 */
	@ExceptionHandler(GenericException.class)
	protected ResponseEntity<ApiError> handleGenericException(Locale locale, GenericException ex) {
		log.error("Inside Global Excepiton Handler handleGenericException method: {}", ex);
		return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST,
				messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale), ex), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle entity not found exception
	 * 
	 * @param locale
	 * @param ex
	 * @return ApiError
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<ApiError> handleEntityNotFoundException(Locale locale, EntityNotFoundException ex) {
		log.error("Inside Global Excepiton Handler handleEntityNotFoundException method: {}", ex);
		return new ResponseEntity<>(
				new ApiError(HttpStatus.NOT_FOUND, messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale), ex),
				HttpStatus.EXPECTATION_FAILED);
	}

	/**
	 * Handle No Resource Found Exception
	 * 
	 * @param ex
	 * @return ApiError
	 */
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<ApiError> handleNoResourceFoundException(NoResourceFoundException ex) {
		log.error("Inside Global Excepiton Handler handleNoResourceFoundException method: {}", ex);
		return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), ex), HttpStatus.NOT_FOUND);
	}

	/**
	 * Handle Method Argument Not Valid Exception
	 * 
	 * @param ex
	 * @return ApiError
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		log.error("Inside Global Excepiton Handler handleHttpMessageNotReadableException method: {}", ex);
		BindingResult bindingResult = ex.getBindingResult();
		List<ApiSubError> subErrors = new ArrayList<>();
		bindingResult.getAllErrors().forEach(oe -> subErrors.add(new ApiValidationError<Object[]>(oe.getObjectName(),
				oe.getArguments(), oe.getCode(), oe.getDefaultMessage())));
		return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), subErrors, ex),
				HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle Http Message Not Readable Exception
	 * 
	 * @param ex
	 * @return ApiError
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ApiError> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		log.error("Inside Global Excepiton Handler handleHttpMessageNotReadableException method: {}", ex);
		return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle Http Media Type Not Supported Exception
	 * 
	 * @param ex
	 * @return ApiError
	 */
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<ApiError> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
		log.error("Inside Global Excepiton Handler handleHttpMediaTypeNotSupportedException method: {}", ex);
		return new ResponseEntity<>(new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getMessage(), ex),
				HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	/**
	 * Handle Http Request Method Not Supported Exception
	 * 
	 * @param ex
	 * @return ApiError
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ApiError> handleHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException ex) {
		log.error("Inside Global Excepiton Handler handleHttpRequestMethodNotSupportedException method: {}", ex);
		return new ResponseEntity<>(new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getMessage(), ex),
				HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	/**
	 * Handle all exception
	 * 
	 * @param ex
	 * @return ApiError
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleException(Exception ex) {
		log.error("Inside Global Excepiton Handler handleException method: {}", ex);
		return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
