package br.com.ficr.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.ficr.exceptions.error.StandardError;

@ControllerAdvice
public class ControllerExceptionHandler {

	private static final String REQUISICAO_INVALIDA = "Requisição inválida";
	private static final String BAD_REQUEST = "Bad Request";
	private static final String CONFLICT = "Conflict";
	private static final String NOT_FOUND = "Not Found";
	private static final String FORBIDDEN = "Forbidden";

	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity<StandardError> catchAlreadyExistsException(AlreadyExistsException e,
			HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.CONFLICT.value());
		err.setError(CONFLICT);
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(err.getStatus()).body(err);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> catchResourceNotFoundException(ResourceNotFoundException e,
			HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError(NOT_FOUND);
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(err.getStatus()).body(err);
	}

}
