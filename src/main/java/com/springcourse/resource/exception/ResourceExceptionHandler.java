package com.springcourse.resource.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
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
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ApiError> handleBadCredentialsException(BadCredentialsException ex){
		
		ApiError error = new ApiError(HttpStatus.UNAUTHORIZED.value(),ex.getMessage(),new Date());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}
	
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException ex){
		ApiError error = new ApiError(HttpStatus.FORBIDDEN.value(), ex.getMessage(),new Date());

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
		
	}
	
	// TRATAMENTO DE EXCESSO DO TAMANHO MAX DA SOMA DOS ARQUIVOS PARA ENVIO AO S3-BUCKET
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<ApiError> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex){
		String[] parts = ex.getMessage().split(":");
		String msg = parts[parts.length - 1].trim().toUpperCase();
	
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(),msg,new Date());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		// comando que pega o primeiro erro para listar no retorno
		// String defaultMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		
		// codigo abaixo gera lista com erros de validacao de entrada
		List<String> errors = new ArrayList<String>();
        ex.getBindingResult().getAllErrors().forEach(error ->{
		
        	 errors.add(error.getDefaultMessage());
        	
		 });
		
		
		//ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), defaultMessage, new Date());
		
        String defaultMessage = "Invalid Field(s)";
        ApiErrorList error = new ApiErrorList(HttpStatus.BAD_REQUEST.value(), defaultMessage, new Date(),errors);
        
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
		
				
		    
	}
	
	
	
	
	
	
}
