package com.springcourse.Repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.RequestState;
import com.springcourse.repository.RequestRepository;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class RequestRepositoryTests {
	@Autowired private RequestRepository requestRepository;
	
		
//	@Test
//	public void AsaveTest() {
//		User owner = new User();
//		owner.setId(37L);
//		
//	//	Request request = new Request(id,  subject, description, creationDate, state, owner, stages);
//		// adicionado ao final dos parametros, mais um atributo null, para representar o atributo files do dto.request
//		Request request = new Request(null, "Novo Laptop HP", "Pretendo obter um laptop HP", new Date(), RequestState.OPEN,  owner, null, null);
//	
//		Request createdRequest = requestRepository.save(request);
//		
//		assertThat(createdRequest.getId()).isEqualTo(6L);
//	}
//		
//	
//	
//	
//	@Test
//	public void updateTest() {
//		User owner = new User();
//		owner.setId(37L);
//		// adicionado ao final dos parametros, mais um atributo null, para representar o atributo files do dto.request
//		Request request = new Request(6L, "Novo Laptop HP", "Pretendo obter um laptop HP, de RAM 16 GB", null, RequestState.OPEN, owner, null, null);
//	
//		Request updatedRequest = requestRepository.save(request);
//		
//		assertThat(updatedRequest.getDescription()).isEqualTo("Pretendo obter um laptop HP, de RAM 16 GB");
//	}
//	
//	@Test
//	public void getByIdTest() {
//		Optional<Request> result = requestRepository.findById(6L);
//		Request request = result.get();
//		
//		assertThat(request.getSubject()).isEqualTo("Novo Laptop HP");
//	}
//	
//	@Test
//	public void listTest() {
//		List<Request> requests = requestRepository.findAll();
//		assertThat(requests.size()).isEqualTo(1);
//	}
//	
//	@Test
//	public void listByOwnerIdTest() {
//		List<Request> requests = requestRepository.findAllByOwnerId(37L);
//		assertThat(requests.size()).isEqualTo(1);
//	}
//	
//	@Test
//	public void updateStatusTest() {
//		int affectedRows = requestRepository.updateStatus(6L, RequestState.IN_PROGRESS);
//		assertThat(affectedRows).isEqualTo(1);
//	}

}
