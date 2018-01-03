package com.cidic.design.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.design.dao.GroupDao;
import com.cidic.design.model.Group;


@Repository
@Component
@Qualifier(value = "groupDaoImpl")
public class GroupDaoImpl implements GroupDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createGroup(Group group) {
		sessionFactory.getCurrentSession().save(group);
	}

	@Override
	public void deleteGroup(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " delete from Group g  where g.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@Override
	public void updateGroup(Group group) {
		sessionFactory.getCurrentSession().update(group);
	}

	@Override
	public List<Group> getAllGroup() {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Group";
		Query query = session.createQuery(hql);
		return query.list();
	}

}
