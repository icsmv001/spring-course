package com.springcourse.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.springcourse.domain.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=75, nullable = false)
	private String name;
	
	@Column(length=75, nullable = false, unique = true)
	private String email;
	
	@Column(length=75, nullable = false)
	private String password;
	
	// relacionamento com class Role (enums)
	@Column(length=20, nullable = false)
	@Enumerated(EnumType.STRING)  // notacao para linkar atributo com class enum respectiva
	private Role role;
	
	// um usuario pode ter varios pedidos
	// relaciona chava estrangeira da classe user com classe request
	@OneToMany(mappedBy= "user")
	private List<Request> request = new ArrayList<Request>();
	
	// um usuario pode ter varios pedidos
	// relaciona chava estrangeira da classe user com classe requestStage
	@OneToMany(mappedBy= "user")
	private List<RequestStage> stages = new ArrayList<RequestStage>();
	
	
	

	
	

}
