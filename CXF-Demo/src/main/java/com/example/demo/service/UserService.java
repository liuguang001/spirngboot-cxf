package com.example.demo.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.example.demo.domain.User;


@WebService
public interface UserService {

	@WebMethod
	public User getUser();
}
