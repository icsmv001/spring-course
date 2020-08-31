package com.springcourse.resource;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.Service.RequestService;
import com.springcourse.Service.RequestStageService;
import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.enums.RequestState;

@RestController
@RequestMapping(value ="request-stages")
public class RequestStageResource {
	@Autowired private RequestStageService stageService;
	
	//metodo save
	@PostMapping
	public ResponseEntity<RequestStage> save (@RequestBody RequestStage requestStage) {
		RequestStage createdRequestStage = stageService.save(requestStage);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRequestStage);
	}
	
	
	//metodo ge by id
	@GetMapping("/{id}")
	public ResponseEntity<RequestStage> getById(@PathVariable(name="id")Long id) {
		RequestStage stage = stageService.getById(id);
		return ResponseEntity.ok(stage);
	}
	
	
	// metodo list all by request id
	// metodo que lista estagios por id do pedido.
	// ficara dentro do RequestResource, 
	// ou seja buscar um pedido e dentro dele pegar os seus stagiso
	// seria htt://localhost:8080/requests/id/request-stages
	
	
	
	
	

}
