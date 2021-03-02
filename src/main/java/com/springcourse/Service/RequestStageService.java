package com.springcourse.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springcourse.domain.RequestStage;
import com.springcourse.domain.enums.RequestState;
import com.springcourse.exception.NotFoundException;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
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
		// return result.get(); substituido pela linha abaixo
		
		return result.orElseThrow(()-> new NotFoundException("The are not Request Stage with id = " + id));
    	// exception, caso não localize o item, retorna mensagem de erro por exception e codigo digito.
    	
		
		
	}
	
	
	
	//get by request id -- lista de estado por id
	public List<RequestStage> listAllByRequestId(Long requestId){
		   List<RequestStage> stages = requestStageRepository.findAllByRequestId(requestId);
		return stages;
	}
	
	
	// metodo para carregar lista sob demanda de paginacao passando o id do usuario
		public PageModel<RequestStage> listAllByRequestIdOnLazyModel(Long requestId, PageRequestModel pr) {
			
	    	Pageable pageable = pr.toSpringPageRequest();
	    	
	    	Page<RequestStage> page = requestStageRepository.findAllByRequestId(requestId, pageable);
	            	
	    	PageModel<RequestStage> pm = new PageModel<RequestStage>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
	    	return pm;
	    	
	    	
	    }
	    
	
	
	
	
	
	
}
