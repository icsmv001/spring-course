package com.springcourse.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springcourse.Service.util.HashUtil;
import com.springcourse.domain.User;
import com.springcourse.exception.NotFoundException;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.repository.UserRepository;

@Service
// esta camada de servico, tem a resposavilidade de chamar os metodos do userRepository
public class UserService {
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
    	Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
    	Page<User> page =userRepository.findAll(pageable);
    	
    	PageModel<User> pm = new PageModel<User>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
    	return pm;
    	
    	
    }
    
    
    
    
    
    
    
    // metodo login
	public User login(String email, String password) {
		password = HashUtil.getSecureHash(password);
		
		Optional<User> result = userRepository.login(email,password);
		return result.get();
	}
	
	
	
	
}
