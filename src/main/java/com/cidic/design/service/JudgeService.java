package com.cidic.design.service;

import java.util.List;
import java.util.Optional;

import com.cidic.design.model.Judge;
import com.cidic.design.model.JudgePageModel;
import com.cidic.design.model.NewsPageModel;

public interface JudgeService {

	public int createJudge(Judge judge);
	
	public void deleteJudge(int id);
	
	public void updateJudge(Judge judge);
	
	public Optional<Judge> findJudgeById(int id);
	
	public Integer findJudgeIdByEmail(String email);
	
	public List<Judge> getAllJudge();
	
	public JudgePageModel findJudgeByPage(int offset, int limit);
	
	public String findJudgePwdByEmail(String email,String validCode);
	
	public void updateJudgeValidCodeByEmail(String email, String validCode);
}
