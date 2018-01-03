package com.cidic.design.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.dao.ProductionDao;
import com.cidic.design.dao.ReviewDao;
import com.cidic.design.model.Production;
import com.cidic.design.model.ProdutionPageModel;
import com.cidic.design.model.ScoreBean;
import com.cidic.design.service.ProductionService;

@Service
@Component
@Qualifier(value = "productionServiceImpl")
@Transactional
public class ProductionServiceImpl implements ProductionService {

	@Autowired
	@Qualifier(value = "productionDaoImpl")
	private ProductionDao productionDaoImpl;
	
	@Autowired
	@Qualifier(value = "reviewDaoImpl")
	private ReviewDao reviewDaoImpl;
	
	@Override
	public void createProduction(Production production) {
		productionDaoImpl.createProduction(production);
	}

	@Override
	public void updateProduction(Production production) {
		productionDaoImpl.updateProduction(production);
	}

	@Override
	public void deleteProduction(int id) {
		productionDaoImpl.deleteProduction(id);
	}

	@Override
	public ProdutionPageModel getListProductionByPage(int offset, int limit, int groupId,int round,int status) {
		List<Production> list = productionDaoImpl.getListProductionByPage(offset, limit, groupId,round,status);
		int count = productionDaoImpl.getCountProduction(groupId,round,status);
		ProdutionPageModel pPageModel = new ProdutionPageModel();
		pPageModel.setList(list);
		pPageModel.setCount(count);
		return  pPageModel;
	}

	@Override
	public ProdutionPageModel getListOnlyProductionInfoByPage(int offset, int limit, int groupId,int userId) {
		
		List<Production> list = productionDaoImpl.getListProductionByPageAndUserId(offset, limit, groupId,userId);
		int count = productionDaoImpl.getCountProductionByUserId(groupId,userId);
		ProdutionPageModel produtionPageModel = new ProdutionPageModel();
		produtionPageModel.setList(list);
		produtionPageModel.setCount(count);
		return  produtionPageModel;
	}

	@Override
	public Optional<Production> getProductionDetailById(int id) {
		
		return productionDaoImpl.getProductionDetailById(id);
	}

	@Override
	public void updateProductionScore(int round) {
		List<ScoreBean> list = reviewDaoImpl.getAllReviewResult(round);
		
		for (ScoreBean scoreBean : list){
			productionDaoImpl.updateProductionScore(scoreBean.getProductionId(), scoreBean.getAverageScore(),round);
		}
	}

	@Override
	public ProdutionPageModel getProductionPageByCondition(int groupId, int category, int status, int userId, int round, int limit,
			int offset) {
		List<Production> list = productionDaoImpl.getProductionByCondition(groupId, category, status, userId, round, limit, offset);
		int count = productionDaoImpl.getProductionCountByCondition(groupId, category, status, userId,round);
		ProdutionPageModel produtionPageModel = new ProdutionPageModel();
		produtionPageModel.setList(list);
		produtionPageModel.setCount(count);
		return  produtionPageModel;
	}

	@Override
	public void updateProductionStatus(int id, int status) {
		productionDaoImpl.updateProductionStatus(id, status);
	}

}
