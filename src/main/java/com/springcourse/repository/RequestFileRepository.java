package com.springcourse.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestFile;
import com.springcourse.domain.User;

 

@Repository
public interface RequestFileRepository extends  JpaRepository <RequestFile, Long> , JpaSpecificationExecutor<RequestFile> {
	
	// metodo que faz upload
	
	
	//metodo para listar os ficheiros de um pedido
    public Page<RequestFile> findAllByrequestId(Long id, Pageable pageable);
	
	
	

}

