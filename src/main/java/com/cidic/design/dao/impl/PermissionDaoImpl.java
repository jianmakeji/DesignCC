package com.cidic.design.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.design.dao.PermissionDao;
import com.cidic.design.model.Permission;

@Repository
@Component
@Qualifier(value = "permissionDaoImpl")
public class PermissionDaoImpl implements PermissionDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createPermission(Permission permission) {
		sessionFactory.getCurrentSession().save(permission);
	}

	@Override
	public void deletePermission(Long permissionId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " delete from Permission p where p.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, permissionId);
		query.executeUpdate();
				
	}

}
