package com.cidic.design;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cidic.design.model.RoundJudge;
import com.cidic.design.model.RoundJudgeListModel;
import com.cidic.design.service.RoundJudgeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class RoundJudgeTest {

	@Autowired
	@Qualifier(value = "roundJudgeServiceImpl")
	private RoundJudgeService roundJudgeServiceImpl;
	
	//@Test
	public void create(){
		RoundJudge roundJudge = new RoundJudge();
		roundJudge.setRoundName("阶段二");
		roundJudge.setDescribes("描述.....xxx");
		roundJudgeServiceImpl.createRoundJudge(roundJudge);
	}
	
	//@Test
	public void update(){
		RoundJudge roundJudge = new RoundJudge();
		roundJudge.setId(2);
		roundJudge.setRoundName("阶段二");
		roundJudge.setJudge("1,2,3,4,5,6,12,13,15,16,17,18,19,252");
		roundJudge.setDescribes("描述.....xxx");
		roundJudgeServiceImpl.updateRoundJudge(roundJudge);
	}
	
	//@Test
	public void updateBinding(){
		
		roundJudgeServiceImpl.bindingRoundJudge(1, "13,15,16,17,18,19,21,22,23,25,26");
	}
	
	public void delete(){
		
	}
	
	@Test
	public void getData(){
		RoundJudgeListModel roundJudgeListModel = roundJudgeServiceImpl.getRoundJudgeByPage(0, 10);
		System.out.println(roundJudgeListModel.getRjList().size());
		System.out.println(roundJudgeListModel.getjList().size());
		
	}
}
