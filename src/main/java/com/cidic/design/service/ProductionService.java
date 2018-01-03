package com.cidic.design.service;

import java.util.Optional;

import com.cidic.design.model.PUPageModel;
import com.cidic.design.model.Production;
import com.cidic.design.model.ProdutionPageModel;

public interface ProductionService {

	public void createProduction(Production production);
	
	public void updateProduction(Production production);
	
	public void deleteProduction(int id);
	
	/**
	 * 管理员权限下查看所有的投稿作品,可以根据组别查询
	 * @param offset
	 * @param limit
	 * @param groupId 0:查看所有，>0 根据组别查询
	 * @return
	 */
	public ProdutionPageModel getListProductionByPage(int offset, int limit, int groupId,int round,int status);
	
	/**
	 * 根据用户ID查看其投稿的所有作品,可以根据组别查询
	 * @param userId
	 * @param offset
	 * @param limit
	 * @param groupId 0:查看所有，>0 根据组别查询
	 * @return
	 */
	public ProdutionPageModel getListOnlyProductionInfoByPage(int offset, int limit, int groupId,int userId);
	
	/**
	 * 根据作品ID查看其详情
	 * @param id
	 * @return
	 */
	public Optional<Production> getProductionDetailById(int id);
	
	public void updateProductionScore(int round);
	
	public ProdutionPageModel getProductionPageByCondition(int groupId, int category, int status, int userId, int round,int limit,
			int offset);
	
	public void updateProductionStatus(int id, int status);
}
