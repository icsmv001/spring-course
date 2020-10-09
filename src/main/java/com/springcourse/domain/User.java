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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
	/**
	 * 
	 */	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=75, nullable = false)
	private String name;
	
	@Column(length=75, nullable = false, unique = true)
	private String email;
	
	//comando para que ao realizar uma consulta a deserialização seja ignorada e não retonro o valor da senha
	//mas no momento de um insert seja considerada a conversao	
	@Getter(onMethod = @__({@JsonIgnore}))
	@Setter(onMethod = @__({@JsonProperty}))
	@Column(length=100, nullable = false)
	private String password;
	
	
	// relacionamento com class Role (enums)
	@Column(length=20, nullable = false, updatable = false)
	@Enumerated(EnumType.STRING)  // notacao para linkar atributo com class enum respectiva
	private Role role;
	
	// um usuario pode ter varios pedidos
	// relaciona chava estrangeira da classe user com classe request. 
	// na tabela/classe request, exist um campo nom nome user, que é a relacao
	// entre as tabelas USER e REQUEST
	
	
	//comando para na serialização, quando ocorrer a busca por usuario
	// ignorar os metodos abaixo, pois o usuario\onwer é independente 
	// de ter ou não um pedido e de seus estados.
	// assim quando buscar somente o usuario, não retorna dados do relacionamento
	// com as demais tabelas 
	@Getter(onMethod = @__({@JsonIgnore}))
	

	@OneToMany(mappedBy= "owner")
	private List<Request> requests = new ArrayList<Request>();
	
	// um usuario pode ter varios pedidos
	// relaciona chava estrangeira da classe user com classe requestStage
	//comando para na serialização, quando ocorrer a busca por usuario
	// ignorar os metodos abaixo, pois o usuario\onwer é independente 
	// de ter ou não um pedido e de seus estados.
	// assim quando buscar somente o usuario, não retorna dados do relacionamento
	// com as demais tabelas 
	@Getter(onMethod = @__({@JsonIgnore}))
	@OneToMany(mappedBy= "owner")
	private List<RequestStage> stages = new ArrayList<RequestStage>();
	

}
