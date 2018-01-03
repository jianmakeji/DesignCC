package com.cidic.design.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.design.dao.FileInfoDao;
import com.cidic.design.model.FileInfo;

@Repository
@Component
@Qualifier(value = "fileInfoDaoImpl")
public class FileInfoDaoImpl implements FileInfoDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createFile(FileInfo file) {
		sessionFactory.getCurrentSession().save(file);
	}

	@Override
	public void deleteFile(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " delete from FileInfo f  where f.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@Override
	public void updateFile(FileInfo file) {
		sessionFactory.getCurrentSession().update(file);
	}

	@Override
	public List<FileInfo> getAllFile() {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from FileInfo";
		Query query = session.createQuery(hql);
		return query.list();
	}

	@Override
	public List<FileInfo> getFileByType(int type) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from FileInfo where fileType = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, type);
		return query.list();
	}

}
