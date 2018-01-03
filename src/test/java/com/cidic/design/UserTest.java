package com.cidic.design;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cidic.design.model.User;
import com.cidic.design.model.UserPageModel;
import com.cidic.design.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserTest {

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userServiceImpl;
	
	@Test
	public void userTest(){
		/*
		
		
		*/
	}
	
	//@Test
	public void updateUserValidTest(){
			
		userServiceImpl.updateValidSign("csboyty@163.com", 1);
	}
	
	//@Test
	public void getUserTest(){
			
		UserPageModel userPageModel = userServiceImpl.getUserByPage(0, 10);
		System.out.println(userPageModel.getCount());
	}
	//@Test
	public void buildRelationshipUserAndRole(){
		userServiceImpl.correlationRoles(1L, 3L,2L);
	} 
	
	//@Test
	public void unBuildRelationshipUserAndRole(){
		userServiceImpl.correlationRoles(1L, 3L,2L);
	}
}
