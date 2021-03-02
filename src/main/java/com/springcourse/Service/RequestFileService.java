package com.springcourse.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springcourse.Service.s3.S3Service;
import com.springcourse.domain.Request;
import com.springcourse.domain.RequestFile;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.model.UploadedFileModel;
import com.springcourse.repository.RequestFileRepository;

@Service
public class RequestFileService {
	
	
	@Autowired
	private RequestFileRepository fileRepository;
	
	
	//upload do s3 -- ligacao do servico do S3Service com o Servico do RequestFileService
	@Autowired
	private S3Service s3Service;
	
	
	//metodo -- que salva proprietades de nome e localidade dos ficheiros carretados
	public List<RequestFile> upload(Long requesId, MultipartFile[] files ) {
		List<UploadedFileModel> uploadedFiles = s3Service.upload(files);
		
	//para salvar nomes e localizao na BD.
	List<RequestFile> requestFiles = new ArrayList<RequestFile> ();
	
	uploadedFiles.forEach(uploadedFile ->  {
		RequestFile file = new RequestFile();
		file.setName(uploadedFile.getName());
		file.setLocation(uploadedFile.getLocation());

		//montagem setar requisicao de chamada
		Request request = new Request();
		request.setId(requesId);
		
		file.setRequest(request);
		
		requestFiles.add(file);
		
	});
		
	 return fileRepository.saveAll(requestFiles);
	}
	
	
	
	//metodo lista ficheiro por id do pedido s3
	public PageModel<RequestFile> listAllByRequestId(Long requestId, PageRequestModel prm) {
		Pageable pageable = prm.toSpringPageRequest();
    	Page<RequestFile> page = fileRepository.findAllByrequestId(requestId, pageable);
            	
    	PageModel<RequestFile> pm = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
    	return pm;
    			
	}
	

}
