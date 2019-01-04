package com.example.demo;

import javax.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.service.StudentService;
import com.example.demo.service.UserService;

@Configuration
public class WebServiceConfig {
	
	@Autowired
	private UserService userService;
	@Autowired
	private StudentService studentService;
	@Autowired 
	private SpringBus springBus;
	
	@Bean
	public ServletRegistrationBean dispatcherServlet() {
		return new ServletRegistrationBean(new CXFServlet(), "/service/*");// 发布服务名称
	}

	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}

	@Bean
	public Endpoint userEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus,userService);// 绑定要发布的服务
		endpoint.publish("/user"); // 显示要发布的名称
		return endpoint;
	}
	
	@Bean
	public Endpoint studentEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus,studentService);// 绑定要发布的服务
		endpoint.publish("/student"); // 显示要发布的名称
		return endpoint;
	}
}