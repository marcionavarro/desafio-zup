package com.marcionavarro.desafiozup.services.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handBusiness(BusinessException ex, WebRequest request) {
		var status = HttpStatus.BAD_REQUEST;

		var problem = new Problem();

		problem.setStatus(status.value());
		problem.setTitle(ex.getMessage());
		problem.setDateHour(LocalDateTime.now());

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		var fields = new ArrayList<Problem.Field>();

		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String name = ((FieldError) error).getField();
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			fields.add(new Problem.Field(name, message));
		}

		var problem = new Problem();

		problem.setStatus(status.value());
		problem.setTitle("Um ou mais campos estão inválidos, faça o preenchiento correto e tente novamente");
		problem.setDateHour(LocalDateTime.now());
		problem.setFields(fields);

		return super.handleExceptionInternal(ex, problem, headers, status, request);
	}

}
