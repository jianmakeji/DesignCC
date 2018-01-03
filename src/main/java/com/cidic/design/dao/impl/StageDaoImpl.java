package com.cidic.design.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.design.dao.StageDao;
import com.cidic.design.model.Stage;

@Repository
@Component
@Qualifier(value = "stageDaoImpl")
public class StageDaoImpl implements StageDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createStage(Stage stage) {
		sessionFactory.getCurrentSession().save(stage);
	}

	@Override
	public void updateStage(Stage stage) {
		sessionFactory.getCurrentSession().update(stage);
	}

	@Override
	public void deleteStage(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " delete from Stage  s  where s.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@Override
	public List<Stage> getAllStage() {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Stage ";
		Query query = session.createQuery(hql);
		return query.list();
	}

}
