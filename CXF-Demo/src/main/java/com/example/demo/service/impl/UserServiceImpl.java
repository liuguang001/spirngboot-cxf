package com.example.demo.service.impl;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;

@Service
@WebService(targetNamespace="http://service.demo.example.com/",endpointInterface="com.example.demo.service.UserService")
public class UserServiceImpl implements UserService{

	@Override
	public User getUser() {
		return new User(1, "liuguang", 25);
	}

}
