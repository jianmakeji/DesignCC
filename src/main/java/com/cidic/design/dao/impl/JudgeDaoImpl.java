package com.cidic.design.dao.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.design.dao.JudgeDao;
import com.cidic.design.model.Judge;

@Repository
@Component
@Qualifier(value = "judgeDaoImpl")
public class JudgeDaoImpl implements JudgeDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createJudge(Judge judge) {
		sessionFactory.getCurrentSession().save(judge);
	}

	@Override
	public void deleteJudge(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " delete from Judge j  where j.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@Override
	public void updateJudge(Judge judge) {
		sessionFactory.getCurrentSession().update(judge);
	}

	@Override
	public Optional<Judge> findJudgeById(int id) {
		Judge judge = (Judge) sessionFactory.getCurrentSession().get(Judge.class, id);
		return Optional.ofNullable(judge);
	}

	@Override
	public List<Judge> getAllJudge() {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Judge order by sequence asc";
		Query query = session.createQuery(hql);
		return query.list();
	}

	@Override
	public List<Judge> findJudgeByPage(int offset, int limit) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Judge order by sequence asc";
		Query query = session.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.list();
	}

	@Override
	public int getCountJudge() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(j) from Judge j";
		Query query = session.createQuery(hql); 
		
        return (int)((Long)query.uniqueResult()).longValue();
	}

	@Override
	public String findJudgePwdByEmail(String email,String validCode) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select password from Judge where email = ? and validCode = ?";
		Query query = session.createQuery(hql); 
		query.setParameter(0, email);
		query.setParameter(1, validCode);
		return (String)query.uniqueResult();
	}

	@Override
	public void updateJudgeValidCodeByEmail(String email, String validCode) {
		Session session = sessionFactory.getCurrentSession();
		String sql = " update judge set validCode = ? where email = ? ";
		Query query = session.createSQLQuery(sql); 
		query.setParameter(0, validCode);
		query.setParameter(1, email);
		query.executeUpdate();
	}

	@Override
	public Integer findJudgeIdByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " select id from Judge where email = ?  ";
		Query query = session.createQuery(hql); 
		query.setParameter(0, email);
		return (Integer)query.uniqueResult();
	}

	@Override
	public String getJudgeValidCodeByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " select validCode from Judge where email = ?  ";
		Query query = session.createQuery(hql); 
		query.setParameter(0, email);
		return (String)query.uniqueResult();
	}

}
