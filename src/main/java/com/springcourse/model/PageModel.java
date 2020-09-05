package com.springcourse.model;
/*
 * trazer elementos na forma de pagina 
 * 
 */

import java.io.Serializable;
import java.util.List;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class PageModel<T> implements Serializable {
	private int totalElements;
	private int pageSize;
	private int totalPages;
	// lista de elementos genericos.
	private List<T> elements;

}
