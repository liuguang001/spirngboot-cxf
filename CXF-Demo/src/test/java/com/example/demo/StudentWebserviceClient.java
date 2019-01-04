package com.example.demo;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import com.example.demo.domain.Student;
import com.example.demo.service.StudentService;

public class StudentWebserviceClient {

    //动态调用
    public static void main(String[] args) throws Exception {
        JaxWsDynamicClientFactory dcflient=JaxWsDynamicClientFactory.newInstance();
        Client client=dcflient.createClient("http://localhost:8080/service/student?wsdl");
        Object[] objects=client.invoke("getStudent");
        System.out.println("*******"+objects[0].toString());
        main3(args);
    }


    //调用方式二，通过接口协议获取数据类型
    public static void main2(String[] args) throws Exception {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean=new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setAddress("http://localhost:8080/service/student?wsdl");
        jaxWsProxyFactoryBean.setServiceClass(StudentService.class);
        StudentService studentService=(StudentService)jaxWsProxyFactoryBean.create();
        Student student = studentService.getStudent();
        System.out.println("UserName:"+student.getName());
    }


    //调用方式三，通过接口协议获取数据类型,设置链接超时和响应时间
    public static void main3(String[] args) throws Exception {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean=new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setAddress("http://localhost:8080/service/student?wsdl");
        jaxWsProxyFactoryBean.setServiceClass(StudentService.class);
        StudentService studentService = (StudentService) jaxWsProxyFactoryBean.create(); // 创建客户端对象
        Client proxy= ClientProxy.getClient(studentService);
        HTTPConduit conduit=(HTTPConduit)proxy.getConduit();
        HTTPClientPolicy policy=new HTTPClientPolicy();
        policy.setConnectionTimeout(1000);
        policy.setReceiveTimeout(1000);
        conduit.setClient(policy);
        Student student = studentService.getStudent();
        System.out.println("UserName:"+student.getName());
    }
}