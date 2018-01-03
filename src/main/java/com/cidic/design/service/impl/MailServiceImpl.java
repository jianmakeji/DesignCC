package com.cidic.design.service.impl;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cidic.design.model.MailBean;
import com.cidic.design.service.MailService;

@Service
@Component
@Qualifier(value = "mailServiceImpl")
public class MailServiceImpl implements MailService {

	@Autowired
	@Qualifier(value = "javaMailSenderImpl")
	private JavaMailSenderImpl javaMailSenderImpl;

	@Override
	public void sendMail(MailBean mailBean) throws MessagingException, UnsupportedEncodingException{
		
		MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		messageHelper.setFrom(mailBean.getFrom(), mailBean.getFromName());
		messageHelper.setSubject(mailBean.getSubject());
		messageHelper.setTo(mailBean.getToEmails());
		messageHelper.setText(mailBean.getContext(), true);
		javaMailSenderImpl.send(mimeMessage);
		
	}

}
