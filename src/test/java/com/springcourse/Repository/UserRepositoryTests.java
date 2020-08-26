package com.springcourse.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springcourse.domain.User;
import com.springcourse.domain.enums.Role;
import com.springcourse.repository.UserRepository;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class UserRepositoryTests {
	// intanciando o UserRepository, para poder usar os metodos dele, por dependencia
	@Autowired private UserRepository userRepository;
	
	
	@Test
	public void AsaveTest()   {
		User user = new User(null,"izael","izael@gmail.com","123",Role.ADMINISTRATOR,null, null);
		User createdUser = userRepository.save(user);
		assertThat(createdUser.getId()).isEqualTo(37L);
		
	}
	
	@Test 
	public void updateTest() { 
		User user = new User(37L, "izaelnew","izael@gmail.com", "123", Role.ADMINISTRATOR, null, null); 
		User updatedUser = userRepository.save(user);
		assertThat(updatedUser.getName()).isEqualTo("izaelnew"); 
	}
	 	
	@Test public void getByIdTest() { 
		Optional<User> result =	 userRepository.findById(37L); 
		User user = result.get();
	    assertThat(user.getPassword()).isEqualTo("123"); 
	    }
	  
	@Test 
	public void listTest() { 
		List<User> users = userRepository.findAll();
	    assertThat(users.size()).isEqualTo(1L); 
	    }
	  
		
	
	@Test
	public void loginTest() { 
		Optional<User> result = userRepository.login("izael@gmail.com", "123"); 
		User loggedUser =  result.get();
	    assertThat(loggedUser.getId()).isEqualTo(37L); 
	    }
	 
	
//	@Test
//    public void updateRoleTest() { 
//    	int affectedRows = userRepository.updateRole(4L, Role.ADMINISTRATOR);
//	   assertThat(affectedRows).isEqualTo(1); }
//	  
	  
	
	 
}
