package com.cidic.design.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.dao.FindPwdDao;
import com.cidic.design.dao.UserDao;
import com.cidic.design.model.FindPwd;
import com.cidic.design.model.MailBean;
import com.cidic.design.model.User;
import com.cidic.design.service.FindPwdService;
import com.cidic.design.service.MailService;
import com.cidic.design.util.ConfigInfo;
import com.cidic.design.util.PasswordHelper;
import com.cidic.design.util.ResponseCodeUtil;

@Service
@Component
@Qualifier(value = "findPwdServiceImpl")
@Transactional
public class FindPwdServiceImpl implements FindPwdService {

	@Autowired
	@Qualifier(value = "findPwdDaoImpl")
	private FindPwdDao findPwdDaoImpl;

	@Autowired
	@Qualifier(value = "configInfo")
	private ConfigInfo configInfo;

	@Autowired
	@Qualifier(value = "mailServiceImpl")
	private MailService mailServiceImpl;

	@Autowired
	@Qualifier(value = "userDaoImpl")
	private UserDao userDaoImpl;
	
	@Override
	public int createFindPwd(FindPwd findPwd) {
		try {
			Optional<User> user = userDaoImpl.findByEmail(findPwd.getEmail());
			if (user.isPresent()){
				// 生成密钥
				String secretKey = UUID.randomUUID().toString();
				
				String key = findPwd.getEmail() + "$" + secretKey;

				String digitalSignature = PasswordHelper.getMD5(key);// 数字签名

				findPwd.setOutDate(new Date());
				findPwd.setValidCode(secretKey);
				int id = findPwdDaoImpl.createFindPwd(findPwd);

				StringBuffer sBuilder = new StringBuffer("请勿回复本邮件.点击下面的链接,重设密码,本邮件超过30分钟,链接将会失效，需要重新申请找回密码！</br>");
				sBuilder.append("<a href=\"" + configInfo.email_active_url + "/user/getFindPwdByCondition?email=");
				sBuilder.append(findPwd.getEmail());
				sBuilder.append("&validCode=");
				sBuilder.append(digitalSignature);
				sBuilder.append("&id=");
				sBuilder.append(id);
				sBuilder.append("\">激活账号：" + findPwd.getEmail());
				sBuilder.append("</a>");

				MailBean mailBean = new MailBean();
				mailBean.setContext(sBuilder.toString());
				mailBean.setFrom(configInfo.email_active_from);
				mailBean.setFromName(configInfo.email_active_from_name);
				mailBean.setSubject(configInfo.email_active_subject);
				mailBean.setToEmails(new String[] { findPwd.getEmail() });
				mailServiceImpl.sendMail(mailBean);
				return ResponseCodeUtil.UESR_OPERATION_SUCESS;
			}
			else{
				return ResponseCodeUtil.UESR_OPERATION_USER_IS_NOT_EXISTS;
			}
			
		} catch (

		Exception e) {
			e.printStackTrace();
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
	}

	@Override
	public int getFindPwdByCondition(String email, String validCode, int id) {
		Optional<FindPwd> findPwd = findPwdDaoImpl.getFindPwdById(id);
		int result = ResponseCodeUtil.USER_FINDPWD_SUCESS;
		if (findPwd.isPresent()) {
			FindPwd obj = findPwd.get();
	
			long date = obj.getOutDate().getTime() + 30 * 60 * 1000;
			
			if (System.currentTimeMillis() >= date) {
				result = ResponseCodeUtil.USER_FINDPWD_LINK_OUT_TIME;
			} else {
				String key = obj.getEmail() + "$" + obj.getValidCode();// 数字签名

				String digitalSignature = PasswordHelper.getMD5(key);// 数字签名

				if (!digitalSignature.equalsIgnoreCase(validCode)) {
					result = ResponseCodeUtil.USER_FINDPWD_LINK_VALID_ERROR;
				} else {
					result = ResponseCodeUtil.USER_FINDPWD_SUCESS;
				}
			}

		} else {
			result = ResponseCodeUtil.USER_FINDPWD_SUCESS;
		}
		return result;
	}

}
