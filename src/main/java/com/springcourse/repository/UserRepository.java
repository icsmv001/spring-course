package com.springcourse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springcourse.domain.User;
import com.springcourse.domain.enums.Role;
@Repository
public interface UserRepository extends JpaRepository<User, Long> , JpaSpecificationExecutor<User> {
 	
	
	@Query("SELECT u FROM user u WHERE email = ?1 AND password =?2")
	public Optional <User> login(String email, String password);
	
	// metodo para atualizar ROLE
	@Transactional(readOnly = false)  // não sera apenas para leitura
	@Modifying
	@Query("UPDATE user SET role = ?2 where id = ?1")
	public int updateRole(Long id, Role role) ;
	
		
	// metodo que busca os dados do usuario
	public Optional<User> findByEmail (String email); 
	
	
}

