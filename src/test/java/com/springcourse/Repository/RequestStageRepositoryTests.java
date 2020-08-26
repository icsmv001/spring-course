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
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.RequestState;
import com.springcourse.repository.RequestStageRepository;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest

public class RequestStageRepositoryTests {
	@Autowired private RequestStageRepository requestStageRepository;
	
		
	@Test
	public void AsaveTest() {
		User owner = new User();
		owner.setId(37L);
		
		Request request = new Request();
		request.setId(6L);
		
	//  RequestStage stage = new RequestStage(id, description                                                   , realizationDate, state, request, owner)
		RequestStage stage = new RequestStage(null, "Foi comprado um novo laptop de marca HD e com 16 GB de RAM", new Date(), RequestState.CLOSED, request, owner);
	
		RequestStage createdStage = requestStageRepository.save(stage);
		
		assertThat(createdStage.getId()).isEqualTo(1L);
	}
	
	
	@Test
	// metodo buscar
	public void getByIdTest() {
		Optional<RequestStage> result = requestStageRepository.findById(1L);
		RequestStage stage = result.get();
		
		assertThat(stage.getDescription()).isEqualTo("Foi comprado um novo laptop de marca HD e com 16 GB de RAM");
	}
	
	@Test
	//metodo listar
	public void listByRequestIdTest() {
		List<RequestStage> stages = requestStageRepository.findAllByRequestId(1L);
		assertThat(stages.size()).isEqualTo(1);
	}

}
