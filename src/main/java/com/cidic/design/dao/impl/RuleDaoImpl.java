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

import com.cidic.design.dao.RuleDao;
import com.cidic.design.model.Rule;

@Repository
@Component
@Qualifier(value = "ruleDaoImpl")
public class RuleDaoImpl implements RuleDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createRule(Rule rule) {
		sessionFactory.getCurrentSession().save(rule);
	}

	@Override
	public void updateRule(Rule rule) {
		sessionFactory.getCurrentSession().update(rule);
	}

	@Override
	public void deleteRule(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " delete from Rule r  where r.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@Override
	public List<Rule> getAllRule() {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Rule";
		Query query = session.createQuery(hql);
		return query.list();
	}

	@Override
	public Optional<Rule> getRuleById(int id) {
		Rule rule = (Rule) sessionFactory.getCurrentSession().get(Rule.class, id);
		return Optional.ofNullable(rule);
	}

}
