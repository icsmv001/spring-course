package com.springcourse.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter

public class UserUpdatedto {
	
	@NotBlank (message = "Nome requerido")
	private String name;
	@Email (message = "Invalido endereço de email")
	private String email;
	
	@Size(min=3,max=9, message = "Password must be between 3 and 99")
	private String password;
	

	private List<Request> requests = new ArrayList<Request>();
	private List<RequestStage> stages = new ArrayList<RequestStage>();
	
	//metodo que transforma esta classe UserSavedto na classe User.
	public User transformToUser () {
		 User user = new  User(null,this.name,this.email,this.password,null,this.requests,this.stages);
		 return user;	 
	}
	

}
