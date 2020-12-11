package com.springcourse.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter

public class UserSavedto {
	
	@NotBlank(message = "Nome requerido")
	private String name;
	
	@Email(message = "Invalido endereço de email")
	private String email;
	
	@Size(min=3,max=99, message = "Password must be between 3 and 99")
	private String password;
	
	@NotNull(message="Role required")
 	private Role   role;
	
	private List<Request> requests = new ArrayList<Request>();
	private List<RequestStage> stages = new ArrayList<RequestStage>();
	

	//metodo que transforma esta classe UserSavedto na classe User.
	public User transformToUser () {
		 User user = new  User(null,this.name,this.email,this.password,this.role,this.requests,this.stages);
		 return user;	 
	}
	
}
