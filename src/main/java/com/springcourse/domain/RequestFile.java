package com.springcourse.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springcourse.domain.enums.RequestState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity (name = "request_file")
public class RequestFile implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column( length=100, nullable = false)
	private String name;
	
	
	@Column(columnDefinition = "text", nullable = false)
	private String location;


	@Getter(onMethod = @__({@JsonIgnore})) // notacao para que no momento da serializacao do request, seja ignorado o relacionamento com o stages, que tem um campo id_request tambem.
	@ManyToOne
	@JoinColumn(name="request_id",  nullable = false)
	private Request request;
	
}
