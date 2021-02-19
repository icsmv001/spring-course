package com.springcourse.model;
// classe para requisita uma pagina com definição de qual numero da pagina 
// e do tamanho da pagina

import java.util.Map;

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
	
	
	//add contrutor para receber o map dos parametros 
	public PageRequestModel(Map <String,String > params ){
		//validar se parametros foram passados, caso nao usar valor default
		if ( params.containsKey("page")) page = Integer.parseInt(params.get("page"));
		if ( params.containsKey("size")) size = Integer.parseInt(params.get("size"));
	}
	
}
