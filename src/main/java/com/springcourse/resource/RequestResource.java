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
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.Service.RequestService;
import com.springcourse.domain.Request;
import com.springcourse.domain.enums.RequestState;

@RestController
@RequestMapping(value ="requests")
public class RequestResource {
	@Autowired private RequestService requestService;
	
	@PostMapping
	public ResponseEntity<Request> save (@RequestBody Request request ){
		Request createdRequest = requestService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest)	;
 	}
    	
	//metodos - update
	
	@PutMapping("/{id}")
	public ResponseEntity<Request> udpate (@PathVariable (name ="id") Long id,@RequestBody Request request){
		request.setId(id);
		Request updateRequest = requestService.update(request);
		return ResponseEntity.ok(updateRequest);
				
	}	
	
	//metodos - get by id
	@GetMapping("/{id}")
	public ResponseEntity<Request> getById(@PathVariable (name ="id") Long id){
		Request request = requestService.getById(id);
		return ResponseEntity.ok(request);
	}
	
	//metodos - list all
	@GetMapping
	public ResponseEntity<List<Request>> ListALL(){
		List<Request> requests = requestService.listAll();
		return ResponseEntity.ok(requests);
		
	}
			
	// metodos - List all by owner id
	// http://localhos:8080/users/requests
	// aqui seria um metodo, para pegar o id do usuario e listar todos os pedidos dele.
	// assim, como ja exist o metodo de busca por id de usuario, em userrecursy, vamos
	// adicionar uma chamada\metodo para buscar o id e listar os seus pedidos, dentro do
	// userrecourse.
	
	
	


}
