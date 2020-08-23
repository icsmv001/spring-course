package com.springcourse.Repository;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import com.springcourse.repository.UserRepository;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.Role;

//import static org.assertj.core.api.Assertions.assertThat;
//
//import org.junit.FixMethodOrder;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//

//import org.junit.FixMethodOrder;
//import org.junit.Test;




@RunWith(SpringRunner.class)
@SpringBootTest

 

public class UserRepositoryTests  {
	 
	@Autowired private UserRepository userRepository;
	
	
	public void saveTest() {
		User user = new User();
		 
		
	}


	
	
	
	
	
	
	
	
	
	
public UserRepositoryTests(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}











public void updateTest() {
	
}



public void getByIdTest() {
	
}


public void listTest() {
	
}

public void loginTest() {
	
}











}








// @FixMethodOrder(MethodSorters.NAME_ASCENDING)


//public class UserRepositoryTests {
//	// para teste com entidade USER, a mesma deve ser estanciada
//	// para que ocorra a utilização de sua dependencia
//	@Autowired private UserRepository userRepository;
//	
	// criando metodos para testes
//	@Test
//	public void AsaveTest() {
//		User user = new User(null, "IZAEL", "IZAEL@GMAIL","123",Role.ADMINISTRATOR, null,stages);
//		User createdUser = userRepository.save(user);
//		assertThat(createdUser.getId()).isEqualTo(1L);
//		
//		
//	}
//	
//	@Test
//	public void updateTest(){
//		
//		User user = new User(1L, "ROSE", "ROSE@GMAIL","123",Role.ADMINISTRATOR, null,stages);
//		User updatedUser = userRepository.save(user);
//		
//		assertThat(updatedUser.getName()).isEqualTo("ROSE");
//	
//		
//	}
//    
//	@Test
//	public void getByIdTest() {
//		Optional<User> result = userRepository.findById(1L);
//		User user = result.get();
//		assertThat(user.getPassword()).isEqualTo("123");
//	}
//	
//	@Test
//	public void listTest() {
//		List<User> users = userRepository.findAll();
//		assertThat(users.size()).isEqualTo(1);
//	}
//	
//	@Test
//	public void loginTest() {
//		Optional<User> result = userRepository.login("IZAEL@GMAIL","123");
//		User loggedUser = result.get();
//		
//		assertThat(loggedUser.getId()).isEqualTo(1L);
//		
//	}
//}
