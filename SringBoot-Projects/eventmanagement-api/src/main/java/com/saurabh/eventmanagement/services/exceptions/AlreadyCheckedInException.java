package com.saurabh.eventmanagement.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class AlreadyCheckedInException extends HttpServerErrorException {

	public AlreadyCheckedInException(String message) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, message);
	}
}
