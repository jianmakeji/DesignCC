package com.cidic.design;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cidic.design.model.Permission;
import com.cidic.design.service.PermissionsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class PermissionTest {

	@Autowired
	@Qualifier(value = "permissionServiceImpl")
	private PermissionsService permissionServiceImpl;
	
	@Test
	public void createPermission(){
		
		Permission permission1 = new Permission();
		permission1.setPermissionName("system:user:admin");
		permission1.setCreatetime(new Date());
		permissionServiceImpl.createPermission(permission1);
		
		Permission permission2 = new Permission();
		permission2.setPermissionName("system:file:operation");
		permission2.setCreatetime(new Date());
		permissionServiceImpl.createPermission(permission2);
		
		Permission permission3 = new Permission();
		permission3.setPermissionName("system:user:operation");
		permission3.setCreatetime(new Date());
		permissionServiceImpl.createPermission(permission3);
		
		Permission permission4 = new Permission();
		permission4.setPermissionName("system:production:judge");
		permission4.setCreatetime(new Date());
		permissionServiceImpl.createPermission(permission4);
		
		Permission permission5 = new Permission();
		permission5.setPermissionName("system:production:view");
		permission5.setCreatetime(new Date());
		permissionServiceImpl.createPermission(permission5);
	}
	
}
