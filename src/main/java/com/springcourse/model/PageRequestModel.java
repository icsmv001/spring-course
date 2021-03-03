package com.springcourse.model;
// classe para requisita uma pagina com definição de qual numero da pagina 
// e do tamanho da pagina

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class PageRequestModel {

	private int page=0;
	private int size=10;
	private String sort="";
	
	
	//add contrutor para receber o map dos parametros 
	public PageRequestModel(Map <String,String > params ){
		//validar se parametros foram passados, caso nao usar valor default
		if ( params.containsKey("page")) page = Integer.parseInt(params.get("page"));
		if ( params.containsKey("size")) size = Integer.parseInt(params.get("size"));
		if ( params.containsKey("sort")) sort = params.get("sort");
		
	}
	
	// metodo que retorna um page request do spring, dado os parametros do pagerequest model
	public PageRequest toSpringPageRequest (){
       // declarar lista de ordenacao
       List<Order> orders = new ArrayList<>();
       
       String[] properties = sort.split(",");
       for (String prop : properties) {
    	   if (prop.trim().length() > 0) {
    		   String column = prop.trim();
    		   
    		   if (column.startsWith("-")) {
    		       column = column.replace("-","").trim();
    		       orders.add(Order.desc(column));
    		   }
    		   else orders.add(Order.asc(column));
    	   }
    	   
       } 

		
		return PageRequest.of(page, size, Sort.by(orders));
		
	}
	
}
