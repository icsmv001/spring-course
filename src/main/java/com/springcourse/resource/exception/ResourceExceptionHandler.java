package com.springcourse.resource.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springcourse.exception.NotFoundException;

@ControllerAdvice
// notação especial, que houve se ocorre erro e avisa, ou seja envia notificação
// para criar mensagem customizadas, extender  a class ResourceExceptionHandler,
// para que possa ser acessada por outras classes no sistema
// a partir dai, podesse ir nos metodos que tratam dos objetos e fazer tratamento
// para retornar nos erros por exception, mensagens amigaveis.

public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ApiError> handleNotFoundException(NotFoundException ex){
		ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(),ex.getMessage(),new Date());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}

	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		
		String defaultMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), defaultMessage, new Date());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
		
		    
	}
	
	
	
	
	
	
}
