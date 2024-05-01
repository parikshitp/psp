package com.divergentsl.psp.exception.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.divergentsl.psp.domain.ApiError;
import com.divergentsl.psp.domain.ApiValidationError;
import com.divergentsl.psp.exception.EntityNotFoundException;
import com.divergentsl.psp.exception.GenericException;

public class GlobalExceptionHandlerTest {

	@Test
	public void testHandleEntityNotFoundException() {
		MessageSource messageSource = mock(MessageSource.class);
		GlobalExceptionHandler handler = new GlobalExceptionHandler(messageSource);
		EntityNotFoundException exception = new EntityNotFoundException("error.entity.not.found", null);

		when(messageSource.getMessage(anyString(), any(Object[].class), any(Locale.class)))
				.thenReturn("Localized Message");

		ResponseEntity<ApiError> response = handler.handleEntityNotFoundException(Locale.getDefault(), exception);

		assertEquals(HttpStatus.EXPECTATION_FAILED, response.getStatusCode());
		assertEquals(null, response.getBody().getMessage());
	}

	@Test
	public void testHandleNoResourceFoundException() {
		GlobalExceptionHandler handler = new GlobalExceptionHandler(null);
		NoResourceFoundException exception = new NoResourceFoundException(null, "Resource not found");

		ResponseEntity<ApiError> response = handler.handleNoResourceFoundException(exception);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("No static resource Resource not found.", response.getBody().getMessage());
	}

	@Test
	public void testHandleMethodArgumentNotValidException() {
		// Mock the MethodArgumentNotValidException and BindingResult
		MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
		BindingResult bindingResult = mock(BindingResult.class);

		// Mock behavior of the MethodArgumentNotValidException
		when(exception.getBindingResult()).thenReturn(bindingResult);

		// Mock behavior of the BindingResult
		List<ObjectError> errors = new ArrayList<>();
		errors.add(new ObjectError("objectName", "Validation Error"));
		when(bindingResult.getAllErrors()).thenReturn(errors);

		// Invoke the method under test
		GlobalExceptionHandler handler = new GlobalExceptionHandler(null);
		ResponseEntity<ApiError> response = handler.handleMethodArgumentNotValidException(exception);

		// Verify the response
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Validation Error",
				((ApiValidationError<?>) response.getBody().getSubErrors().get(0)).getMessage());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testHandleHttpMessageNotReadableException() {
		GlobalExceptionHandler handler = new GlobalExceptionHandler(null);
		HttpMessageNotReadableException exception = new HttpMessageNotReadableException("Message not readable");

		ResponseEntity<ApiError> response = handler.handleHttpMessageNotReadableException(exception);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Message not readable", response.getBody().getMessage());
	}

	@Test
	public void testHandleHttpMediaTypeNotSupportedException() {
		GlobalExceptionHandler handler = new GlobalExceptionHandler(null);
		HttpMediaTypeNotSupportedException exception = new HttpMediaTypeNotSupportedException(
				"Media type not supported");

		ResponseEntity<ApiError> response = handler.handleHttpMediaTypeNotSupportedException(exception);

		assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, response.getStatusCode());
		assertEquals("Media type not supported", response.getBody().getMessage());
	}

	@Test
	public void testHandleHttpRequestMethodNotSupportedException() {
		GlobalExceptionHandler handler = new GlobalExceptionHandler(null);
		HttpRequestMethodNotSupportedException exception = new HttpRequestMethodNotSupportedException(
				"Request method not supported");

		ResponseEntity<ApiError> response = handler.handleHttpRequestMethodNotSupportedException(exception);

		assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, response.getStatusCode());
		assertEquals("Request method 'Request method not supported' is not supported", response.getBody().getMessage());
	}

	@Test
	public void testHandleGenericException() {
		// Mock the dependencies
		MessageSource messageSource = mock(MessageSource.class);
		GlobalExceptionHandler handler = new GlobalExceptionHandler(messageSource);
		GenericException exception = new GenericException("error.generic", new Object[] { "arg1", "arg2" });

		// Mock behavior of the MessageSource
		when(messageSource.getMessage(anyString(), any(Object[].class), any(Locale.class)))
				.thenReturn("Localized Message");

		// Invoke the method under test
		ResponseEntity<ApiError> response = handler.handleGenericException(Locale.getDefault(), exception);

		// Verify the response
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Localized Message", response.getBody().getMessage());
	}
}
