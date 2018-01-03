package com.cidic.design;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cidic.design.model.Role;
import com.cidic.design.service.RoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class RoleTest {

	@Autowired
	@Qualifier("roleServiceImpl")
	private RoleService roleServiceImpl;
	
	@Test
	public void roleTest(){
		Role role = new Role();
		role.setRolename("manager3");
		role.setCreatetime(new Date());
		roleServiceImpl.createRole(role);
	}
	
	
}
