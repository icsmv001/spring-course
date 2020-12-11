package com.springcourse.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class UserLoginDto {
	
	@Email(message = "Invalid email address")
	private String email;
	
	@NotBlank(message = "Password required")
	@Size(min=3,max=99, message = "Password must be between 3 and 99")
	private String password;
}

