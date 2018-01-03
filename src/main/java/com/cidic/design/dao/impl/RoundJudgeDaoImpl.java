package com.cidic.design.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.design.dao.RoundJudgeDao;
import com.cidic.design.model.RoundJudge;

@Repository
@Component
@Qualifier(value = "roundJudgeDaoImpl")
public class RoundJudgeDaoImpl implements RoundJudgeDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createRoundJudge(RoundJudge roundJudge) {
		sessionFactory.getCurrentSession().save(roundJudge);
	}

	@Override
	public void updateRoundJudge(RoundJudge roundJudge) {
		sessionFactory.getCurrentSession().update(roundJudge);
	}

	@Override
	public void deleteRoundJudge(int id) {
		RoundJudge roundJudge = new RoundJudge();
		roundJudge.setId(id);
		sessionFactory.getCurrentSession().delete(roundJudge);
	}

	@Override
	public List<RoundJudge> getRoundJudgeByPage(int offset, int limit) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from RoundJudge ";
		Query query = session.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.list();
	}

	@Override
	public void bindingRoundJudge(int id, String judge) {
		Session session = sessionFactory.getCurrentSession();
		String sql = " update round_judge set judge = ? where id = ? ";
		Query query = session.createSQLQuery(sql);
		query.setParameter(0, judge);
		query.setParameter(1, id);
		query.executeUpdate();
	}

	@Override
	public RoundJudge getRoundJudgeById(int id) {
		return (RoundJudge) sessionFactory.getCurrentSession().get(RoundJudge.class, id);
	}

}
