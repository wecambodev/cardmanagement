package com.mbanq.cardmanagement.exception;

import com.mbanq.cardmanagement.model.Role;
import com.mbanq.cardmanagement.model.RoleName;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

//	@Autowired
//	@Qualifier("databaseMessageSourceService")
//	private MessageSourceService messageSource;
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ InvalidRequestException.class })
	protected ResponseEntity<Object> handleInvalidRequest(RuntimeException e, WebRequest request) {
		InvalidRequestException ire = (InvalidRequestException) e;
		List<FieldErrorResource> fieldErrorResources = new ArrayList<>();

		List<FieldError> fieldErrors = ire.getErrors().getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			FieldErrorResource fieldErrorResource = new FieldErrorResource();
			fieldErrorResource.setResource(fieldError.getObjectName());
			fieldErrorResource.setField(fieldError.getField());
			fieldErrorResource.setCode(fieldError.getCode().toLowerCase()+"."+fieldError.getObjectName().toLowerCase()+"."+fieldError.getField().toLowerCase());
			fieldErrorResource.setMessage(fieldError.getDefaultMessage());
			fieldErrorResources.add(fieldErrorResource);
		}

		ErrorResource error = new ErrorResource("InvalidRequest", ire.getMessage());
		error.setFieldErrors(fieldErrorResources);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
		//return handleExceptionInternal(e, error, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
		//return handleExceptionInternal(e, error, headers, HttpStatus.BAD_REQUEST, request);

	}

//	@ResponseStatus(HttpStatus.FORBIDDEN)
//	@ExceptionHandler({ AccessDeniedException.class })
//	protected ResponseEntity<Object> handleAccessDenied(RuntimeException e, WebRequest request) {
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		return new ResponseEntity<Object>("Access Denied", HttpStatus.FORBIDDEN);
//
//	}

	@ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
		Role role = new Role(1L);
		role.setId(1L);
		role.setName(RoleName.ROLE_ADMIN);
        return new ResponseEntity<Object>(role
          , new HttpHeaders(), HttpStatus.FORBIDDEN);
    }


	@Bean
	public ErrorAttributes errorAttributes() {
		// Hide exception field in the return object
		return new DefaultErrorAttributes() {


		};
	}

	@ExceptionHandler(CustomException.class)
	public void handleCustomException(HttpServletResponse res, CustomException ex) throws IOException {
		res.sendError(ex.getHttpStatus().value(), ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(HttpServletResponse res) throws IOException {
		Role role = new Role(1L);
		role.setId(1L);
		role.setName(RoleName.ROLE_ADMIN);
		return new ResponseEntity<Object>(role
				, new HttpHeaders(), HttpStatus.FORBIDDEN);
	}
}
