package com.cidic.design.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.design.dao.ReviewDao;
import com.cidic.design.model.Production;
import com.cidic.design.model.Review;
import com.cidic.design.model.RoundScoreBean;
import com.cidic.design.model.ScoreBean;

@Repository
@Component
@Qualifier(value = "reviewDaoImpl")
public class ReviewDaoImpl implements ReviewDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createReview(Review review) {
		sessionFactory.getCurrentSession().save(review);
	}

	@Override
	public void updateReview(Review review) {
		sessionFactory.getCurrentSession().update(review);
	}

	@Override
	public void deleteReview(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " delete from Review r  where r.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@Override
	public List<Review> getReviewListByProductionId(int productionId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Review where productionId = ? order by createtime desc";
		Query query = session.createQuery(hql);
		query.setParameter(0, productionId);
		return query.list();
	}

	@Override
	public List<Production> getReviewListByUserId(int userId, int scoreSign, int round, int offset, int limit) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "";
		if (scoreSign == 0){ //所有评分的作品
			hql = " select p.id,p.title,p.groupId,p.userId,p.content,p.attachFile,p.createTime,p.thumb,p.pimage,p.category,r.score "
					+ " from Review r, Production p where r.userId = ? and r.productionId = p.id and r.round = ? order by createtime desc";
		}
		else if (scoreSign == 1){ //已评分的作品
			hql = " select p.id,p.title,p.groupId,p.userId,p.content,p.attachFile,p.createTime,p.thumb,p.pimage,p.category,r.score "
					+ " from Review r, Production p where r.userId = ? and r.productionId = p.id and r.round = ? and r.score > 0 order by createtime desc";
		}
		else if (scoreSign == 2){//未评分的作品
			hql = " select p.id,p.title,p.groupId,p.userId,p.content,p.attachFile,p.createTime,p.thumb,p.pimage,p.category,r.score "
					+ " from Review r, Production p where r.userId = ? and r.productionId = p.id and r.round = ? and r.score = 0 order by createtime desc";
		}
		
		Query query = session.createQuery(hql);
		query.setParameter(0, userId);
		query.setParameter(1, (byte)round);
		
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		List list =  query.list();
		
		List<Production> pList = new  ArrayList<Production>(10);
		for(int i=0; i<list.size(); i++)
        {
			Production production = new Production();
            Object []o = (Object[])list.get(i);
            int pId = ((Number)o[0]).intValue();
            String title = (String)o[1];
            int gId = ((Number)o[2]).intValue();
            int uId = (Integer)o[3];
            String content = (String)o[4];
            String attachFile = (String)o[5];
            Date createTime = (Date)o[6];
            String thumb = (String)o[7];
            String pimage = (String)o[8];
            byte category = (Byte)o[9];
            float score = ((Number)o[10]).floatValue();
            production.setId(pId);
            production.setTitle(title);
            production.setGroupId(gId);
            production.setUserId(uId);
            production.setContent(content);
            production.setAttachFile(attachFile);
            production.setCreateTime(createTime);
            production.setThumb(thumb);
            production.setPimage(pimage);
            production.setCategory(category);
            production.setScore(score);
            pList.add(production);
        }
		return pList;
	}

	public int getCountReviewByUserId(int userId, int scoreSign, int round){
		Session session = sessionFactory.getCurrentSession();
		String hql = "";
		if (scoreSign == 0){ //所有评分的作品
			hql = " select count(*) from Review r, Production p where r.userId = ? and r.productionId = p.id and r.round = ?";
		}
		else if (scoreSign == 1){ //已评分的作品
			hql = " select count(*) from Review r, Production p where r.userId = ? and r.productionId = p.id and r.round = ? and r.score > 0 ";
		}
		else if (scoreSign == 2){//未评分的作品
			hql = " select count(*) from Review r, Production p where r.userId = ? and r.productionId = p.id and r.round = ? and r.score = 0 ";
		}
		
		Query query = session.createQuery(hql);
		query.setParameter(0, userId);
		query.setParameter(1, (byte)round);
		return (int)((Long)query.uniqueResult()).longValue();
		
	}
	
	@Override
	public ScoreBean getScoreByProductionId(int productionId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " select productionId, sum(score), count(*) from Review where productionId = ? group by productionId ";
		Query query = session.createQuery(hql);
		query.setParameter(0, productionId);
		
        @SuppressWarnings("unchecked")
		List list = query.list();
        
        ScoreBean scoreBean = null;
        for(int i=0; i<list.size(); i++)
        {
        	scoreBean = new ScoreBean();
            Object []o = (Object[])list.get(i);
            int pId = (Integer)o[0];
            int scoreSum = (Integer)o[1];
            int gradeSum = (Integer)o[2];
            float averageScore = (float)scoreSum/gradeSum;
            
            scoreBean.setProductionId(pId);
            scoreBean.setGradeSum(gradeSum);
            scoreBean.setScoreSum(scoreSum);
            scoreBean.setAverageScore(averageScore);
            
        }
        return scoreBean;
	}

	@Override
	public List<ScoreBean> getAllReviewResult(int round) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " select productionId, sum(score), count(*) from Review where round = ? group by productionId ";
		Query query = session.createQuery(hql);
		query.setParameter(0, (byte)round);
        @SuppressWarnings("unchecked")
		List list = query.list();
        
        List<ScoreBean> scoreList = new ArrayList<ScoreBean>(10);
        ScoreBean scoreBean = null;
        for(int i=0; i<list.size(); i++)
        {
        	scoreBean = new ScoreBean();
            Object []o = (Object[])list.get(i);
            int productionId = (Integer)o[0];
            int scoreSum = ((Number)o[1]).intValue();
            int gradeSum = ((Number)o[2]).intValue();
            float averageScore = (float)scoreSum/gradeSum;
            
            scoreBean.setProductionId(productionId);
            scoreBean.setGradeSum(gradeSum);
            scoreBean.setScoreSum(scoreSum);
            scoreBean.setAverageScore(averageScore);
            
            scoreList.add(scoreBean);
        }
        return scoreList;
	}

	@Override
	public void updateReviewScore(int id, int score) {
		Session session = sessionFactory.getCurrentSession();
		String sql = " update from review set score = ? where id = ? ";
		Query query = session.createSQLQuery(sql);
		query.setParameter(0, score);
		query.setParameter(1, id);
		query.executeUpdate();
	}

	@Override
	public List<String> getSendEmailByRound(int round) {
		Session session = sessionFactory.getCurrentSession();
		String sql = " select distinct j.email from Review r,Judge j where j.id = r.userId and round = ? ";
		Query query = session.createQuery(sql);
		query.setParameter(0, round);
		
		List list = query.list();
        
        List<String> resultList = new ArrayList<>();
        for(int i=0; i<list.size(); i++)
        {
            String email = (String)list.get(i);
            
            resultList.add(email);
            
        }
        return resultList;
	}

	@Override
	public void updateReviewScoreByCondition(int productionId, int userId, int round, int score) {
		Session session = sessionFactory.getCurrentSession();
		String sql = " update review set score = ? where productionId = ? and userId = ? and round = ? ";
		Query query = session.createSQLQuery(sql);
		query.setParameter(0, score);
		query.setParameter(1, productionId);
		query.setParameter(2, userId);
		query.setParameter(3, round);
		query.executeUpdate();
	}

	@Override
	public int getScoreByCondition(int productionId, int userId, int round) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = " select score from Review where productionId = ? and  userId = ? and round = ? ";
		Query query = session.createQuery(hql); 
		query.setParameter(0, productionId);
		query.setParameter(1, userId);
		query.setParameter(2, (byte)round);
		return ((Byte)query.uniqueResult()).intValue();
		
	}

	@Override
	public List<RoundScoreBean> getRoundScoreBean(int productionId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " select j.roundName, sum(v.score), count(*) from Review v, RoundJudge j where v.productionId = ? and j.id=v.round group by j.roundName ";
		Query query = session.createQuery(hql);
		query.setParameter(0, productionId);
		
        @SuppressWarnings("unchecked")
		List list = query.list();
        
        List<RoundScoreBean> listResult = new ArrayList<>();
        RoundScoreBean roundScoreBean = null;
        for(int i=0; i<list.size(); i++)
        {
        	roundScoreBean = new RoundScoreBean();
            Object []o = (Object[])list.get(i);
            String round = o[0].toString();
            int scoreSum =((Number)o[1]).intValue();
            int gradeSum = ((Number)o[2]).intValue();
            float averageScore = (float)scoreSum/gradeSum;
            averageScore = (float)(Math.round(averageScore*100))/100;
            roundScoreBean.setRound(round);
            roundScoreBean.setScoreNum(gradeSum);
            roundScoreBean.setScoreSum(scoreSum);
            roundScoreBean.setAverageScore(averageScore);
            listResult.add(roundScoreBean);
        }
        return listResult;
	}

}
