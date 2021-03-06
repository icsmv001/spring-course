package com.springcourse.resource;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
import com.springcourse.dto.UserLoginResponsedto;
import com.springcourse.dto.UserSavedto;
import com.springcourse.dto.UserUpdateDto;
import com.springcourse.dto.UserUpdateRoleDto;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.security.AccessManager;
import com.springcourse.security.JwtManager;
@RestController
@RequestMapping(value = "users")
public class UserResorce {
	// intanciando o userservice para usar seus metodos.
	@Autowired private UserService userService;
	// instanca do requestService, para recupar lista de pedidos por id
	@Autowired private RequestService requestService;
	@Autowired private AuthenticationManager authManager;
	@Autowired private JwtManager jwtManager;
	@Autowired private AccessManager accessManager;
	
	
//	// metodo save sem tratamento de validacao de entrada
//	@PostMapping
//	public ResponseEntity<User> save(@RequestBody  User user){
//		User createdUser = userService.save(user);
//		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
//	}
		
	// ESTA NOTACAO FAZ COM QUE SOMENTE O ADMINISTRADOR POSSA USAR O METODO RESPONSEENTY<USER>,
	// atendendo ao item 1, de requisitos funcionais; adm pode registrar novo registro.
	@Secured({"ROLE_ADMINISTRATOR"})

	
	// metodo save com tratamento de validacao de entrada
	@PostMapping
	public ResponseEntity<User> save(@RequestBody  @Valid UserSavedto userdto){
		
	
		
		User userToSave = userdto.transformToUser();
		
		User createdUser = userService.save(userToSave);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	
	
	
//	// metodo update sem tratamento de validacao de entrada
//		@PutMapping("/{id}")
//		public ResponseEntity<User> update(@PathVariable(name="id") Long id, @RequestBody User user){
//			user.setId(id);
//			User updateUser = userService.save(user);
//			return ResponseEntity.ok(updateUser);
//			
//		}
//	
	
	// metodo para autorizar somente o mesmo usuario que faz a solicitacao, se for o mesmo que consta no token
    @PreAuthorize("@accessManager.isOwner(#id)")
	// metodo update com tratamento de validacao de entrada
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable(name="id") Long id, @RequestBody @Valid  UserUpdateDto userdto){
		User user = userdto.transformToUser();
		user.setId(id);
		User updateUser = userService.save(user);
		//System.out.println("UserResorce ate aqui ok5...");
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
		
	// metodo list sem paginacao 
	@GetMapping (value = "/login")
	public ResponseEntity<List<User>> listAll1()  {
		List <User> users = userService.listAll();
		return ResponseEntity.ok(users);
	}
		
	
	
	
	// metodo list com  paginacao 
	@RequestMapping(value = "/pages")
	public ResponseEntity<PageModel<User>> listAll(
		// parametros de entrada da requisicao // versao inicial 
		//@RequestParam(value = "page", defaultValue ="0" ) int page,
		//@RequestParam(value = "size", defaultValue ="10") int size)  {
		
		// parametros de entrada da requisicao - nova versao otimizada
		@RequestParam Map<String, String> params ){
		
	     
		// versao anterior
		//PageRequestModel pr = new PageRequestModel(page,size);
		
		//versao otimizada
		PageRequestModel pr = new PageRequestModel(params);
		
		
		PageModel<User> pm = userService.listAllOnLazyModel(pr);
	    return ResponseEntity.ok(pm);		
	}
		
	
	
	
	
	// metodo login 
	@PostMapping("/login")
	
	// metodo sem usar token
	//public ResponseEntity<User> login(@RequestBody @Valid UserLoginDto user){
		
	// metodo usando token
	public ResponseEntity<UserLoginResponsedto> login(@RequestBody @Valid UserLoginDto user){
				
		// System.out.println("ate aqui ok...");
		// remivendo o metodo userSevice usado para fazer login, e substituindo pelo metodo do sprint security
		// User loggedUser = userService.login(user.getEmail(),user.getPassword());
		// return ResponseEntity.ok(loggedUser);
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
	
		// chamada de metodo authenticate
		Authentication auth = authManager.authenticate(token);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		//BUSCANDO O USUARIO PARA MONTAR A CHAMADA DO TOKEN, por retornar um objeto apos o igual foi convertido com CAST
		org.springframework.security.core.userdetails.User userSprint = 
				(org.springframework.security.core.userdetails.User) auth.getPrincipal();
		
		// implementando os parametros 
		String email = userSprint.getUsername();
		List<String> roles = userSprint.getAuthorities()
				.stream()
				.map(authority -> authority.getAuthority())
				.collect(Collectors.toList());
		
		
		
		
		return ResponseEntity.ok(jwtManager.createToken(email, roles));
	}
		
//	// metodo logins -- sem uso de token --teste ok 
//	@PostMapping("/logins")
//	//public ResponseEntity<User> logins(@RequestBody @Valid UserLoginDto user){
//	  //   System.out.println("ate aqui ok...");
//		//User loggedUser = userService.login(user.getEmail(),user.getPassword());
//		//return ResponseEntity.ok(loggedUser);
//	//}
//	
//	// metodo usando token
//		public ResponseEntity<UserLoginResponsedto> logins(@RequestBody @Valid UserLoginDto user){
//					
//			// System.out.println("ate aqui ok...");
//			// remivendo o metodo userSevice usado para fazer login, e substituindo pelo metodo do sprint security
//			// User loggedUser = userService.login(user.getEmail(),user.getPassword());
//			// return ResponseEntity.ok(loggedUser);
//
//		
//		
//		
//			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
//		
//			// chamada de metodo authenticate
//			Authentication auth = authManager.authenticate(token);
//			
//			SecurityContextHolder.getContext().setAuthentication(auth);
//			
//			//BUSCANDO O USUARIO PARA MONTAR A CHAMADA DO TOKEN, por retornar um objeto apos o igual foi convertido com CAST
//			org.springframework.security.core.userdetails.User userSprint = 
//					(org.springframework.security.core.userdetails.User) auth.getPrincipal();
//			
//			// implementando os parametros 
//			String email = userSprint.getUsername();
//			List<String> roles = userSprint.getAuthorities()
//					.stream()
//					.map(authority -> authority.getAuthority())
//					.collect(Collectors.toList());
//			
//		 
//			
//			return ResponseEntity.ok(jwtManager.createToken(email, roles));
//		}
//		
	
	
	
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
//		// parametros de entrada da requisicao - versao anterior 
//		@RequestParam(value = "page", defaultValue ="0" ) int page,
//		@RequestParam(value = "size", defaultValue ="10") int size)  {
//		PageRequestModel pr = new PageRequestModel(page, size);
//		PageModel<Request> pm = requestService.listAllByOwnerIdOnLazyModel(id, pr);
//        return ResponseEntity.ok(pm);
		
		// parametros de entrada da requisicao - versao otimizada 
		@RequestParam Map<String, String> params ){
		PageRequestModel pr = new PageRequestModel(params);
		PageModel<Request> pm = requestService.listAllByOwnerIdOnLazyModel(id, pr);
        return ResponseEntity.ok(pm);
        
        
	}
		
		
	@Secured({"ROLE_ADMINISTRATOR"})
	// notacao que permite que somente o ADM, possa realizar alteracao no role, atendendo ao item 7 do documento de requisitos
	// funcionais do usuario
	//montar metodo para atualizar ROLE, chamando o metodo update da classe user service
	@PatchMapping("/role/{id}")
	public ResponseEntity<?> updateRole(@PathVariable(name="id") Long id,  @RequestBody @Valid UserUpdateRoleDto userdto) {
		User user = new User();
		user.setId(id);
		user.setRole(userdto.getRole());
		
		userService.updateRole(user);
		
	    return ResponseEntity.ok().build();
	    
	}
	
	
		
		
}
