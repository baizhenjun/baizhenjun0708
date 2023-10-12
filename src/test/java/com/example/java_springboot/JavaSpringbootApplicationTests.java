package com.example.java_springboot;

import com.example.java_springboot.entity.user.User;
import com.example.java_springboot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class JavaSpringbootApplicationTests {
	@Resource
	UserService userService;
	@Test
	void contextLoads() {
		List<User> list = userService.list();
		System.out.println("list = " + list);
	}

}
