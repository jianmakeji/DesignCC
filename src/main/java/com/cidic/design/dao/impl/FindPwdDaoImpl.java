package com.cidic.design.dao.impl;

import java.util.Optional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.design.dao.FindPwdDao;
import com.cidic.design.model.FindPwd;
import com.cidic.design.model.News;

@Repository
@Component
@Qualifier(value = "filePwdDaoImpl")
public class FindPwdDaoImpl implements FindPwdDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public int createFindPwd(FindPwd findPwd) {
		return (int)sessionFactory.getCurrentSession().save(findPwd);
	}

	@Override
	public Optional<FindPwd> getFindPwdByCondition(String email, String validCode) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from FindPwd where email = ? and validCode = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, email); 
        query.setParameter(1, validCode); 
        query.setMaxResults(1);
        query.setCacheable(true);
        Optional<FindPwd> optFeedback = Optional.ofNullable((FindPwd)query.uniqueResult());
        return optFeedback;
	}

	@Override
	public Optional<FindPwd> getFindPwdById(int id) {
		FindPwd findPwd = (FindPwd) sessionFactory.getCurrentSession().get(FindPwd.class, id);
		return Optional.ofNullable(findPwd);
	}

}
