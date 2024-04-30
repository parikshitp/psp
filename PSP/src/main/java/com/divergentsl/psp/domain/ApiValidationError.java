package com.divergentsl.psp.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiValidationError<T extends Serializable> extends ApiSubError implements Serializable {

	private static final long serialVersionUID = 1L;
	private String object;
	private T field;
	private String code;
	private String message;

}
