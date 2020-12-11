package com.springcourse.dto;
//classe criada para salvar os dados de um request = solicitacao

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class RequestSavedto {
	//declarar atibutos de um request e sua respectiva validação de entrada
	
	@NotBlank (message = "Subject required") // nao pode ser vazio 
	private String subject;
	private String description;
	@NotNull  (message = "Owner required")// não pode ser null
	private User owner;
	private List<RequestStage> stages = new ArrayList<RequestStage>();
	
	//metodo que transforma a class RequestSAveDto em um objeto Request
	public Request transformToRequest() {
		Request request = new Request(null, this.subject, this.description,null, null, this.owner, this.stages);
		return request;
	
	}
	
}
