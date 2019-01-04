package com.example.demo.service.impl;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Student;
import com.example.demo.service.StudentService;

@Service
@WebService(targetNamespace="http://service.demo.example.com/",endpointInterface="com.example.demo.service.StudentService")
public class StudentServiceImpl implements StudentService{

	@Override
	public Student getStudent() {
		return new Student(1, "liuguang", 25);
	}
	
}
