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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.Service.RequestService;
import com.springcourse.Service.UserService;
import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.dto.UserLoginDto;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;

@RestController
@RequestMapping(value = "users")
public class UserResorce {
	// intanciando o userservice para usar seus metodos.
	@Autowired private UserService userService;
	// instanca do requestService, para recupar lista de pedidos por id
	@Autowired private RequestService requestService;
	
	
	// metodo save
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user){
		User createdUser = userService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	
	
	// metodo update
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable(name="id") Long id, @RequestBody User user){
		user.setId(id);
		User updateUser = userService.save(user);
		return ResponseEntity.ok(updateUser);
		
	}
	
	
	// metodo get by id
	@GetMapping("/{id}") 
	public ResponseEntity<User> getById(@PathVariable("id") Long id) {
		User user = userService.getById(id);
		return ResponseEntity.ok(user);
		
	}
	
	
	// metodo list sem paginacao 
	@GetMapping 
	public ResponseEntity<List<User>> listAll()  {
		List <User> users = userService.listAll();
		return ResponseEntity.ok(users);
	}
		
	
	// metodo list com  paginacao 
	@RequestMapping(value = "/pages")
	public ResponseEntity<PageModel<User>> listAll(
		// parametros de entrada da requisicao
		@RequestParam(value = "page", defaultValue ="0" ) int page,
		@RequestParam(value = "size", defaultValue ="10") int size)  {
		
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<User> pm = userService.listAllOnLazyModel(pr);
	    return ResponseEntity.ok(pm);		
	}
		
	
	
	
	
	// metodo login 
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody UserLoginDto user){
		User loggedUser = userService.login(user.getEmail(),user.getPassword());
		return ResponseEntity.ok(loggedUser);
	}
	
	
	// metodo para pegar um usuario e listar todo os seus pedidos.
	// busca de pedidos por id do user
	// metodo sem paginação
	@GetMapping("/{id}/requests")
	public ResponseEntity<List<Request>> listAllRequestsById(@PathVariable(name="id") Long id) {
		List<Request> requests = requestService.listAllByOwnerId(id);
		return ResponseEntity.ok(requests);
	}
	
	
	// metodo para pegar um usuario e listar todo os seus pedidos.
	// metodo com paginação
	@GetMapping("/{id}/requestsPages")
	public ResponseEntity<PageModel<Request>> listAllRequestsById(
		@PathVariable(name="id") Long id,
		// parametros de entrada da requisicao
		@RequestParam(value = "page", defaultValue ="0" ) int page,
		@RequestParam(value = "size", defaultValue ="10") int size)  {
		
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<Request> pm = requestService.listAllByOwnerIdOnLazyModel(id, pr);
        return ResponseEntity.ok(pm);
		

	}
		
	
	
		
	
	
}
