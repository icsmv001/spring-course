package com.springcourse.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcourse.domain.Request;
import com.springcourse.domain.enums.RequestState;
import com.springcourse.exception.NotFoundException;
import com.springcourse.repository.RequestRepository;

@Service
public class RequestService {
	@Autowired private RequestRepository requestRepository;
	
	//save
	public Request save(Request request) {
		// o estado do Novo pedido na abertura deve ser OPEN
		// a data do Novo pedido e a data corrente
		request.setState(RequestState.OPEN);
		request.setCreationDate(new Date());
		
		Request createdRequest = requestRepository.save(request);
		return createdRequest;
	}
		
	// update
	public Request update(Request request) {

		Request updateRequest = requestRepository.save(request);
		return updateRequest;
	}
	
	// get
	public Request getById (long id) {
		Optional<Request> result = requestRepository.findById(id);
		// return result.get(); substituida pela linha abaixo.
		
		return result.orElseThrow(()-> new NotFoundException("The are not request with id = " + id));
    	// exception, caso não localize o item, retorna mensagem de erro por exception e codigo digito.
    	
	}
	
	
	//list
	public List<Request> listAll () {
		List<Request> requests = requestRepository.findAll();
		return requests;
	}
	
	
	//list by owner  -- lista de pedidos por usuario
	public List<Request> listAllByOwnerId (Long owneId) {
		List<Request> requests = requestRepository.findAllByOwnerId(owneId);
		return requests;
	}
		
 	
}
