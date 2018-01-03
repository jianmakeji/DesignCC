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

import com.cidic.design.dao.NewsDao;
import com.cidic.design.model.News;

@Repository
@Component
@Qualifier(value = "newsDaoImpl")
public class NewsDaoImpl implements NewsDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createNews(News news) {
		sessionFactory.getCurrentSession().save(news);
	}

	@Override
	public void deleteNews(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " delete from News n  where n.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@Override
	public void updateNews(News news) {
		sessionFactory.getCurrentSession().update(news);
	}

	@Override
	public Optional<News> findNewsById(int id) {
		News news = (News) sessionFactory.getCurrentSession().get(News.class, id);
		return Optional.ofNullable(news);
	}

	@Override
	public List<News> findNewsByPage(int offset, int limit) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from News order by publishTime desc";
		Query query = session.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.list();
	}

	@Override
	public int getCountNews() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(n) from News n";
		Query query = session.createQuery(hql); 
		
        return (int)((Long)query.uniqueResult()).longValue();
	}

	@Override
	public List<News> getTopThreeNews() {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from News order by publishTime desc";
		Query query = session.createQuery(hql);
		query.setMaxResults(3);
		return query.list();
	}

}
