package com.springcourse.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello-word")

public class MyController {

	@Value("${response.welcome.message}")
	private String responseMessage;   // = "HEllO WORD !!";
	
	@GetMapping
	public ResponseEntity getMassage() {
		return new ResponseEntity(responseMessage, HttpStatus.ACCEPTED);
	}
	
	
	
}
