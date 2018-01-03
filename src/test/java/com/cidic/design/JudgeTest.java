package com.cidic.design;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cidic.design.dao.JudgeDao;
import com.cidic.design.service.JudgeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class JudgeTest {

	@Autowired
	@Qualifier(value = "judgeServiceImpl")
	private JudgeService judgeServiceImpl;
	
	@Test
	public void testJudge(){
		String password = judgeServiceImpl.findJudgePwdByEmail("liling@cidic.cn", "7b4e5b54b49f3772d1fa943e8458d79d");
		System.out.println(password);
		
		judgeServiceImpl.updateJudgeValidCodeByEmail("liling@cidic.cn", "7b4e5b54b49f3772d1fa943e8458dewky");
	}
}
