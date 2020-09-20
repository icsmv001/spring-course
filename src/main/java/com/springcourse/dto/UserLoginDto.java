package com.springcourse.dto;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class UserLoginDto {
				
	// validacao
	@Email (message = "EMAIL INVALIDO")
	private String email;
	
	
	// validacao
	@NotBlank(message = "PASSWORD NOT IS BLANK !!")
	private String password;
	
	
	
}
