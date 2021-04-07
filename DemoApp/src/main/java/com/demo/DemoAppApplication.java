package com.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.model.PropertiesReader;

@SpringBootApplication
public class DemoAppApplication implements ApplicationRunner {

	@Autowired
	PropertiesReader reader;

	public static void main(String[] args) {
		SpringApplication.run(DemoAppApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("reading key 1:- " + reader.getKey1());

	}

}
