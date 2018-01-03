package com.cidic.design.model;

public class ScoreBean {
	
	private int productionId;
	private int scoreSum; //总分合计
	private int gradeSum; //评分人数合计
	private float averageScore; //平均分
	
	public int getProductionId() {
		return productionId;
	}
	public void setProductionId(int productionId) {
		this.productionId = productionId;
	}
	public int getScoreSum() {
		return scoreSum;
	}
	public void setScoreSum(int scoreSum) {
		this.scoreSum = scoreSum;
	}
	public int getGradeSum() {
		return gradeSum;
	}
	public void setGradeSum(int gradeSum) {
		this.gradeSum = gradeSum;
	}
	public float getAverageScore() {
		return averageScore;
	}
	public void setAverageScore(float averageScore) {
		this.averageScore = averageScore;
	}
	
	
}
