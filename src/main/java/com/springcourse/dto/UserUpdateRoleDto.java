package com.springcourse.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.springcourse.domain.enums.Role;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class UserUpdateRoleDto {
	@NotNull ( message = "Role required" )
	private Role role;
}



 