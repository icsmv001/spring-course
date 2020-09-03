package com.springcourse.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcourse.domain.RequestStage;
import com.springcourse.domain.enums.RequestState;
import com.springcourse.repository.RequestRepository;
import com.springcourse.repository.RequestStageRepository;

@Service
public class RequestStageService {
	@Autowired private RequestStageRepository requestStageRepository;
	@Autowired private RequestRepository requestRepository;
	
	
	//save
	public RequestStage save (RequestStage stage) {
		//data de inclusão de novo estado inicial sera a data corrente
		stage.setRealizationDate(new Date());
				
		RequestStage createdStage = requestStageRepository.save(stage);
		
		// chamar metodo para atualizar estado do pedido, por isto foi instanciado o requestRepository tambem.
		// atualizar estado do request
		Long requestId = stage.getRequest().getId();
		RequestState state = stage.getState();
		
		requestRepository.updateStatus(requestId, state);
				
		return createdStage;
		
		
	}
	
	
	
	//get busca um estagio por id
	public RequestStage getById(Long id) {
		Optional<RequestStage> result = requestStageRepository.findById(id);
		return result.get();
		
	}
	
	
	
	//get by request id -- lista de estado por id
	public List<RequestStage> listAllByRequestId(Long requestId){
		   List<RequestStage> stages = requestStageRepository.findAllByRequestId(requestId);
		return stages;
	}
	
	
	
	
	
}
