package com.springcourse.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springcourse.Service.util.HashUtil;
import com.springcourse.domain.User;
import com.springcourse.exception.NotFoundException;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.repository.UserRepository;

@Service
// esta camada de servico, tem a resposavilidade de chamar os metodos do userRepository
public class UserService implements UserDetailsService {
    // instanciar o UserRepository, para ter acesso a seus metodos
	@Autowired private UserRepository userRepository;
	
	// metodo save
	public User save (User user) {
		//captura senha e converte para texto tipo hash
		String hash = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(hash);
		//grava senha criptografaca com metodo hash
		User createdUser = userRepository.save(user);
		return createdUser;
	}
	
	// metodo update
    public User update (User user ) {
    	//captura senha e converte para texto tipo hash
    	String hash = HashUtil.getSecureHash(user.getPassword());
    	user.setPassword(hash);
    	//grava senha criptografaca com metodo hash
    	User updateUser = userRepository.save(user);
    	return updateUser;
    }
	
	
	// metodo get  - filtrar por id
    public User getById (Long id) {
    	Optional<User> result =	 userRepository.findById(id); 
		//return result.get();  -- substituido pela linha abaixo, com tratamento de exception.
    	
    	
    	return result.orElseThrow(()-> new NotFoundException("The are not user with id = " + id));
    	// exception, caso não localize o item, retorna mensagem de erro por exception e codigo digito.
    	
	  
    }
	
    // metodo list
    public List<User> listAll() {
		List<User> users = userRepository.findAll();
        return users;
             
        
        
        
    }
    
    //metodo para carregar lista sob demanda
    public PageModel<User> listAllOnLazyModel(PageRequestModel pr) {
    	
    	Pageable pageable = pr.toSpringPageRequest();
    	
    	Page<User> page =userRepository.findAll(pageable);
    	
    	PageModel<User> pm = new PageModel<User>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
    	return pm;
    	
    	
    }
    
     
    // metodo login
	public User login(String email, String password) {
		password = HashUtil.getSecureHash(password);
		
		Optional<User> result = userRepository.login(email, password);
	
		
		
		//Optional<User> result = userRepository.login(email,password);
		
//		
//		System.out.println("-----------------------------" );
//		System.out.println("ate aqui ok. Email:.  " + email );
//		System.out.println("ate aqui ok..Password:" + password );
//		System.out.println("ate aqui ok..result:" + result );
//		System.out.println("-----------------------------" );
//		
		//return result.orElseThrow(()-> new NotFoundException("teste erro valor null "));
		
		return result.get();
	}


	//Metodo para atualizar o ROLE, com base no modelo usado no Test
    public int updateRole(User user) {
	    return  userRepository.updateRole(user.getId(), user.getRole());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// neste metodo o username, recebe o parametro do email do usuario para validar acesso via jwt
		
        Optional<User> result = userRepository.findByEmail(username);
        // teste se usuario existe ou nao
        if(!result.isPresent()) throw new UsernameNotFoundException("Dosen't exist user with email = " + username);
        User user = result.get();
              
        List<GrantedAuthority>  authorities = Arrays.asList(new  SimpleGrantedAuthority("ROLE_" +  user.getRole().name()));
        
        // montando user do sprint security
        org.springframework.security.core.userdetails.User userSpring = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
               
		
		return userSpring;
		
	}
			
	
}
