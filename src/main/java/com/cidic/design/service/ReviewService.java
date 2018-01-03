package com.cidic.design.service;

import java.util.List;

import com.cidic.design.model.Production;
import com.cidic.design.model.ProdutionPageModel;
import com.cidic.design.model.Review;
import com.cidic.design.model.RoundScoreBean;

public interface ReviewService {

	public void createReview(Review review);
	
	public void updateReview(Review review);
	
	public void deleteReview(int id);
	
	public List<Review> getReviewListByProductionId(int productionId);
	
	public ProdutionPageModel getReviewListByUserId(int userId, int scoreSign, int round, int offset, int limit);
	
	public void createReviews(int userId, String productIds,int round);
	
	public void updateReviewScore(int id, int score);
	
	public void updateReviewScoreByCondition(int productionId,int userId, int round, int score);
	
	public List<String> getSendEmailByRound(int round);
	
	public void sendReviewEmail(int round, String emailContent, String testEmail);
	
	public int bindProductAndRound(int productionId, int round);
	
	public int getScoreByCondition(int productionId,int userId, int round);
	
	public List<RoundScoreBean> getRoundScoreBean(int productionId);
}
