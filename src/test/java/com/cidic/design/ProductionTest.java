package com.cidic.design;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cidic.design.service.ProductionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ProductionTest {

	@Autowired
	@Qualifier(value = "productionServiceImpl")
	private ProductionService productionServiceImpl;
	
	//@Test
	public void testGet(){
		productionServiceImpl.getListProductionByPage(0, 10, 0,1,1);
	}
	
	//@Test
	public void testGetByCondition(){
		//productionServiceImpl.getProductionPageByCondition(0, 0, 0, 0, 10, 0);
	}
	
	@Test 
	public void updateStatus(){
		productionServiceImpl.updateProductionStatus(1, 2);
	}
}
