package com.springcourse.model;
// classe para requisita uma pagina com definição de qual numero da pagina 
// e do tamanho da pagina

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class PageRequestModel {

	private int page;
	private int size;
	
}
