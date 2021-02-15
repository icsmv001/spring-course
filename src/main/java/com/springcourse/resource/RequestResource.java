package com.springcourse.resource;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.enums.RequestState;
import com.springcourse.dto.RequestSavedto;
import com.springcourse.dto.RequestUpdateDto;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.security.AccessManager;
import com.springcourse.Service.RequestService;
import com.springcourse.Service.RequestStageService;




@RestController
@RequestMapping(value ="requests")
public class RequestResource {
	@Autowired private RequestService requestService;
	@Autowired private RequestStageService stageService;
	@Autowired private AccessManager accessManager;
	

	
	@PostMapping
	public ResponseEntity<Request> save (@RequestBody @Valid RequestSavedto requestdto ){
		
		Request  request = requestdto.transformToRequest();
		
		
		
		Request createdRequest = requestService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest)	;
 	}
    	
	
	// metodo para autorizar somente o mesmo usuario que faz a solicitacao, se for o mesmo que consta no token
	// se authoriza for OK, indica que o usuario que consta no token igual ao que esta tentando modificar os dados.
	@PreAuthorize("@accessManager.isRequestOwner(#id)")
	//metodos - update
	@PutMapping("/{id}")
	public ResponseEntity<Request> udpate (@PathVariable (name ="id") Long id,@RequestBody @Valid RequestUpdateDto requestdto){
		Request request = requestdto.transformToRequest();
		
    	
//		 System.out.println("ate aqui ok...");
//		 System.out.println("ate aqui ok...");
//		 System.out.println("ate aqui ok...");
//		 System.out.println("ate aqui ok...");
//		 System.out.println("ate aqui ok...");
//		 System.out.println("ate aqui ok...");
//		 System.out.println("ate aqui ok...");
//		 System.out.println("ate aqui ok...");
//		 System.out.println("ate aqui ok...");
//		 System.out.println("ate aqui ok...");
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
	
//	//metodos - list all
//	@GetMapping
//	public ResponseEntity<List<Request>> ListALL(){
//		List<Request> requests = requestService.listAll();
//		return ResponseEntity.ok(requests);
//		
//	}
		
	// metodo para pegar todo os  pedidos.
	// metodo com paginação
	@GetMapping
	public ResponseEntity<PageModel<Request>> listAll(
		// parametros de entrada da requisicao
		@RequestParam(value = "page", defaultValue ="0" ) int page,
		@RequestParam(value = "size", defaultValue ="10") int size)  {
			
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<Request> pm = requestService.listAllOnLazyModel(pr);
	    return ResponseEntity.ok(pm);
	}
	
	
	// metodos - List all by owner id
	// http://localhos:8080/users/requests
	// aqui seria um metodo, para pegar o id do usuario e listar todos os pedidos dele.
	// assim, como ja exist o metodo de busca por id de usuario, em userrecursy, vamos
	// adicionar uma chamada\metodo para buscar o id e listar os seus pedidos, dentro do
	// userrecourse.
	
	
	//metodo para listar um pedido e pegar os seus estados
	// seria htt://localhost:8080/requests/id/request-stages
	// sem paginacao
	@GetMapping("/{id}/request-stages")
	public ResponseEntity<List<RequestStage>> listAllStagesById(
			@PathVariable (name ="id") Long id)  {
		List<RequestStage> stages = stageService.listAllByRequestId(id);
		return ResponseEntity.ok(stages);
		
	}
	
    
	//metodo para listar um pedido e pegar os seus estados
    // COM  paginacao  
	@GetMapping("/{id}/request-stages-pages")
	public ResponseEntity<PageModel<RequestStage>> listAllStagesById(
		@PathVariable (name ="id") Long id,
		// parametros de entrada da requisicao
		// parametros de entrada da requisicao
		@RequestParam(value = "page", defaultValue ="0" ) int page,
		@RequestParam(value = "size", defaultValue ="10") int size)  {
			 
        PageRequestModel pr = new PageRequestModel(page,size);
		
		PageModel<RequestStage> pm = stageService.listAllByRequestIdOnLazyModel(id, pr);
		return ResponseEntity.ok(pm);
			
		}
		
	
	
	

}
