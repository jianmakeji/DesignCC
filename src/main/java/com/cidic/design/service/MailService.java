package com.cidic.design.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.cidic.design.model.MailBean;

public interface MailService {

	public void sendMail(MailBean mailBean) throws MessagingException, UnsupportedEncodingException;
	 
}
