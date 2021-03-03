package com.springcourse.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;


@Repository
public interface RequestStageRepository extends JpaRepository<RequestStage, Long> , JpaSpecificationExecutor<RequestStage> {

    //metodo lista de estagio do pedido	
	public List<RequestStage> findAllByRequestId(Long id);
	
	
	//metodo lista de estagio do pedido, por paginacao	
	public Page<RequestStage> findAllByRequestId(Long id, Pageable pageable);
	
	
}
