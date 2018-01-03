package com.cidic.design.dao;

import java.util.List;
import java.util.Optional;

import com.cidic.design.model.ProductUserModel;
import com.cidic.design.model.Production;
import com.cidic.design.model.ScoreBean;

public interface ProductionDao {

	public void createProduction(Production production);
	
	public void updateProduction(Production production);
	
	public void deleteProduction(int id);
	
	/**
	 * 管理员权限下查看所有的投稿作品,可以根据组别查询，与注册用户表关联
	 * @param offset
	 * @param limit
	 * @param groupId 0:查看所有，>0 根据组别查询
	 * @return
	 */
	public List<Production> getListProductionByPage(int offset, int limit, int groupId,int round, int status);
	
	public int getCountProduction(int groupId,int round,int status);
	
	/**
	 * 管理员权限下查看所有的投稿作品,可以根据组别查询，与注册用户表关联
	 * @param offset
	 * @param limit
	 * @param groupId 0:查看所有，>0 根据组别查询
	 * @return
	 */
	public List<ProductUserModel> getListProductionByPageRelationRegisterUser(int offset, int limit, int groupId);
	
	public int getCountProductionRelationRegisterUser(int groupId);
	/**
	 * 根据用户ID查看其投稿的所有作品,可以根据组别查询
	 * @param userId
	 * @param offset
	 * @param limit
	 * @param groupId 0:查看所有，>0 根据组别查询
	 * @return
	 */
	public List<Production> getListProductionByPageAndUserId(int offset, int limit, int groupId, int userId);
	
	public int getCountProductionByUserId(int groupId, int userId);
	
	/**
	 * 根据作品ID查看其详情
	 * @param id
	 * @return
	 */
	public Optional<Production> getProductionDetailById(int id);
	
	/**
	 * 最终打分结果更新到对应的作品
	 * @param productionId
	 * @param averageScore
	 */
	public void updateProductionScore(int productionId, float averageScore, int round);
	
	/**
	 * 批量更新最后得分
	 * @param list
	 */
	public void batchUpdateProductionScore(List<ScoreBean> list);
	
	/**
	 * 根据条件进行搜索
	 * @param groupId
	 * @param category
	 * @param status
	 * @param limit
	 * @param offset
	 * @param userId
	 * @return
	 */
	public List<Production> getProductionByCondition(int groupId, int category, int status, int userId, int round, int limit, int offset);
	
	/**
	 * 根据条件获取投稿产品总数
	 * @param groupId
	 * @param category
	 * @param status
	 * @param userId
	 * @return
	 */
	public int getProductionCountByCondition(int groupId, int category, int status, int userId, int round );
	
	
	/**
	 * 修改投稿作品状态
	 * @param id
	 * @param status
	 */
	public void updateProductionStatus(int id, int status);
	
	/**
	 * 根据ID更新轮次
	 * @param productionId
	 */
	public void updateRoundById(int productionId,int round);
	
	/**
	 * 获取当前ID作品的所属状态
	 * @param id
	 * @return
	 */
	public int getProductionStatus(int id);
	
}
