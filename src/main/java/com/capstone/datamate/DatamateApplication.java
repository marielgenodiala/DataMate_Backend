package com.capstone.datamate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.capstone.datamate.Service.FileService;

import jakarta.annotation.Resource;

@SpringBootApplication
public class DatamateApplication{
	@Resource
	FileService fileService;

	public static void main(String[] args){
		SpringApplication.run(DatamateApplication.class, args);
	}

}
