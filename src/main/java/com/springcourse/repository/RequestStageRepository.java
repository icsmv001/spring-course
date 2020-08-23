package com.springcourse.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springcourse.domain.RequestStage;

@Repository
public interface RequestStageRepository extends JpaRepository<RequestStage, Long>{

    //metodo lista de estagio do pedido	
	
	public List<RequestStage> findAllByRequestId(Long id);
	
//	// metodo, para query que deve pegar alteracao do estado do pedido
//	@Query("update Request set state=?2 where id=?1")
//	public Request updateStatus (Long id, RequestState state);
//	
	
}
