package com.springcourse.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springcourse.domain.Request;
import com.springcourse.domain.enums.RequestState;


@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{
	
	// o metodo EXTENDS JPA, contem os metodos save, find e etc...
    //retorna lista de pedidos por id do usuario
	
	//public List<Request> findAllUserId(Long id);
	
	
 
	public List<Request> findAllByOwnerId(Long id);
	// metodo, para query que deve pegar alteracao do estado do pedido
	@Query("UPDATE request SET state = ?2 WHERE id = ?1")
	public int updateStatus(Long id, RequestState state);
	
	
}

