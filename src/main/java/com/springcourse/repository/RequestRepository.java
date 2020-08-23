package com.springcourse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springcourse.domain.Request;

 

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{
	
	// o metodo EXTENDS JPA, contem os metodos save, find e etc...
    //retorna lista de pedidos por id do usuario
	public List<Request> findAllOwnerid(Long id);
	
	
}
