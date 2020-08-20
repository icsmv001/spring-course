package com.springcourse.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class User {
	private Long id;
	private String name;
	private String password;
	private List<Request> request = new ArrayList<Request>();
	private List<RequestStage> stages = new ArrayList<RequestStage>();
	
	//construtor vazio
	public User() {
		// TODO Auto-generated constructor stub
	}


	
	

}
