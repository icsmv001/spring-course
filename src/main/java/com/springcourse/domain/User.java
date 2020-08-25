package com.springcourse.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;

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
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter

@Entity(name = "user")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=75, nullable = false)
	private String name;
	
	@Column(length=75, nullable = false, unique = true)
	private String email;
	
	@Column(length=100, nullable = false)
	private String password;
	
	
	// relacionamento com class Role (enums)
	@Column(length=20, nullable = false)
	@Enumerated(EnumType.STRING)  // notacao para linkar atributo com class enum respectiva
	private Role role;
	
	// um usuario pode ter varios pedidos
	// relaciona chava estrangeira da classe user com classe request. 
	// na tabela/classe request, exist um campo nom nome user, que é a relacao
	// entre as tabelas USER e REQUEST
	@OneToMany(mappedBy= "owner")
	private List<Request> requests = new ArrayList<Request>();
	
	// um usuario pode ter varios pedidos
	// relaciona chava estrangeira da classe user com classe requestStage
	@OneToMany(mappedBy= "owner")
	private List<RequestStage> stages = new ArrayList<RequestStage>();
	

}
