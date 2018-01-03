package com.cidic.design;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cidic.design.model.Production;
import com.cidic.design.service.ReviewService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ReviewTest {

	@Autowired
	@Qualifier(value = "reviewServiceImpl")
	private ReviewService reviewServiceImpl;
	
	@Test
	public void getReviewTest(){
		//List<Production> list1 = reviewServiceImpl.getReviewListByUserId(1, 0, 0, 10);
		//System.out.println("=====================1:"+list1.size());
		
		//List<Production> list2 = reviewServiceImpl.getReviewListByUserId(12, 1, 0, 10);
		//System.out.println("=====================2:"+list2.size());
		
		//List<Production> list3 = reviewServiceImpl.getReviewListByUserId(12, 2, 0, 10);
		//System.out.println("=====================3:"+list3.size());
		
		System.out.println("********************"+reviewServiceImpl.getScoreByCondition(2, 6, 3));
	}
	
	//@Test
	public void getSendReviewEmail(){
		List<String> list1 = reviewServiceImpl.getSendEmailByRound(0);
		System.out.println("=====================1:"+list1.size());
		
		for (String s : list1){
			System.out.println(s);
		}
	}
}
