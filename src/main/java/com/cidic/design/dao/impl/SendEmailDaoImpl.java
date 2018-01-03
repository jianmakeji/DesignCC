package com.cidic.design.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.design.dao.SendEmailDao;
import com.cidic.design.model.SendEmail;

@Repository
@Component
@Qualifier(value = "sendEmailDaoImpl")
public class SendEmailDaoImpl implements SendEmailDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createSendEmail(SendEmail sendEmail) {
		sessionFactory.getCurrentSession().save(sendEmail);
	}

}
