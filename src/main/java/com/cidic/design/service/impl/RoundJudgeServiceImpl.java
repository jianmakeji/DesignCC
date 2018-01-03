package com.cidic.design.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.dao.JudgeDao;
import com.cidic.design.dao.impl.RoundJudgeDaoImpl;
import com.cidic.design.model.Judge;
import com.cidic.design.model.RoundJudge;
import com.cidic.design.model.RoundJudgeListModel;
import com.cidic.design.service.RoundJudgeService;

@Service
@Component
@Qualifier(value = "roundJudgeServiceImpl")
@Transactional
public class RoundJudgeServiceImpl implements RoundJudgeService {

	@Autowired
	@Qualifier(value = "roundJudgeDaoImpl")
	private RoundJudgeDaoImpl roundJudgeDaoImpl;
	
	@Autowired
	@Qualifier(value = "judgeDaoImpl")
	private JudgeDao judgeDaoImpl;
	
	@Override
	public void createRoundJudge(RoundJudge roundJudge) {
		roundJudgeDaoImpl.createRoundJudge(roundJudge);
	}

	@Override
	public void updateRoundJudge(RoundJudge roundJudge) {
		roundJudgeDaoImpl.updateRoundJudge(roundJudge);
	}

	@Override
	public void deleteRoundJudge(int id) {
		roundJudgeDaoImpl.deleteRoundJudge(id);
	}

	@Override
	public RoundJudgeListModel getRoundJudgeByPage(int offset, int limit) {
		List<RoundJudge> rjList = roundJudgeDaoImpl.getRoundJudgeByPage(offset, limit);
		List<Judge> jList = judgeDaoImpl.getAllJudge();
		RoundJudgeListModel roundJudgeListModel = new RoundJudgeListModel();
		roundJudgeListModel.setjList(jList);
		roundJudgeListModel.setRjList(rjList);
		return roundJudgeListModel;
	}

	@Override
	public void bindingRoundJudge(int id, String judge) {
		roundJudgeDaoImpl.bindingRoundJudge(id, judge);
	}

	@Override
	public RoundJudge getRoundJudgeById(int id) {
		// TODO Auto-generated method stub
		return roundJudgeDaoImpl.getRoundJudgeById(id);
	}

}
