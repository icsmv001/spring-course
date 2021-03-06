package com.springcourse.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.IntPredicate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springcourse.domain.enums.RequestState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity (name = "request")

public class Request implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="subject",length=75, nullable = false)
	private String subject;
	
	@Column(name="description",columnDefinition = "text")
	private String description;
	
		
	@Column(name="creation_date",nullable = false, updatable = false)
	@Temporal (TemporalType.TIMESTAMP)
	private Date creationDate;
	
	
	// relacionamento com class Role (enums)
	@Column(length=12, nullable = false)
	@Enumerated(EnumType.STRING)  // notacao para linkar atributo com class enum respectiva
	private RequestState state;
	
	
	
	@ManyToOne // notacao de varios pedidos para um usuario
	@JoinColumn(name="owner_id", nullable=false) // nome da chave estrangeira do usuario
	private User owner;
	
	
	@Getter(onMethod = @__({@JsonIgnore})) // notacao para que no momento da serializacao do request, seja ignorado o relacionamento com o stages, que tem um campo id_request tambem.
	@OneToMany(mappedBy = "request")
	private List<RequestStage> stages = new ArrayList<RequestStage>();
	
	// adicionado atributo files, que recebe os dados de ficheiro para upload e grava no banco de dados
	@Getter(onMethod = @__({@JsonIgnore})) // notacao para que no momento da serializacao do request, seja ignorado o relacionamento com o stages, que tem um campo id_request tambem.
	@OneToMany(mappedBy = "request")
	private List<RequestFile> files = new ArrayList<RequestFile>();
	
	
	
}
