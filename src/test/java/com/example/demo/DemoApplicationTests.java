package com.example.demo;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	StudentRepository studentRepository;

	@Test
	void contextLoads() {
		List<Student> list = studentRepository.findAll();
		System.out.println(list.get(0).getSecondName());
	}

}
