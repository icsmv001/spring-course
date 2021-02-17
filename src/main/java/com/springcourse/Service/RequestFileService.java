package com.springcourse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springcourse.domain.RequestFile;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.repository.RequestFileRepository;

@Service
public class RequestFileService {
	
	
	@Autowired
	private RequestFileRepository fileRepository;
	//upload do s3
	
	
	
	
	//metodo lista ficheiro por id do pedido s3
	public PageModel<RequestFile> listAllByRequestId(Long requestId, PageRequestModel prm) {
		Pageable pageable = PageRequest.of(prm.getPage(), prm.getSize());
    	Page<RequestFile> page = fileRepository.findAllByrequestId(requestId, pageable);
            	
    	PageModel<RequestFile> pm = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
    	return pm;
    	
		
	}
	

}
