package com.cidic.design.model;

public class RoundScoreBean {

	private String round;
	private float averageScore; //平均分
	private int scoreSum; //总分合计
	private int scoreNum; //打分人数
	
	public String getRound() {
		return round;
	}
	public void setRound(String round) {
		this.round = round;
	}
	public float getAverageScore() {
		return averageScore;
	}
	public void setAverageScore(float averageScore) {
		this.averageScore = averageScore;
	}
	public int getScoreSum() {
		return scoreSum;
	}
	public void setScoreSum(int scoreSum) {
		this.scoreSum = scoreSum;
	}
	public int getScoreNum() {
		return scoreNum;
	}
	public void setScoreNum(int scoreNum) {
		this.scoreNum = scoreNum;
	}
	
	
}
