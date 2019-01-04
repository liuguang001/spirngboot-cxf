package com.example.demo.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.example.demo.domain.Student;

@WebService
public interface StudentService {

	@WebMethod
	public Student getStudent();
}
