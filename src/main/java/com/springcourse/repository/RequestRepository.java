package com.springcourse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.springcourse.domain.Request;
import com.springcourse.domain.enums.RequestState;

 

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{
	
	// o metodo EXTENDS JPA, contem os metodos save, find e etc...
    //retorna lista de pedidos por id do usuario
	public List<Request> findAllByOnUser_id(Long id);
	
	// metodo, para query que deve pegar alteracao do estado do pedido
	@Query("UPDATE request SET state=?2 WHERE id=?1")
	public Request updateStatus (Long id, RequestState state);
	
}
