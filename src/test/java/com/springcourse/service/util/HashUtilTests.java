package com.springcourse.service.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.springcourse.Service.util.HashUtil;

@RunWith (SpringRunner.class)
public class HashUtilTests {

	@Test
	public void getSecureHashTest() {
		String hash = HashUtil.getSecureHash("123");
		
		System.out.println("-------------------- ");
		System.out.println("teste---> " + hash);
		System.out.println("-------------------- ");
		
		assertThat(hash.length()).isEqualTo(64);
	}
	
}

