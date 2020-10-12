package com.myBatis;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.myBatis.mappers.StudentMapper;
import com.myBatis.model.Student;

@SpringBootApplication
@MapperScan("com.myBatis.mappers")
public class MyBatisApplication implements CommandLineRunner, ApplicationRunner   {

	@Autowired
	StudentMapper mapper;

	public static void main(String[] args) {
		System.out.println("main");
		SpringApplication.run(MyBatisApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception { //CommandLineRunner
		System.out.println("CommandLineRunner run method being calld");
		List<Student> data = mapper.findAll();
		data.forEach(System.out::println);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception { //ApplicationRunner
		System.out.println("ApplicationRunner run method being calld");
		List<Student> data = mapper.findAll();
		data.forEach(System.out::println);
	}

	

}
