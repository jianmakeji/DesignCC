package com.cidic.design.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.dao.JudgeDao;
import com.cidic.design.dao.ProductionDao;
import com.cidic.design.dao.ReviewDao;
import com.cidic.design.dao.SendEmailDao;
import com.cidic.design.dao.impl.RoundJudgeDaoImpl;
import com.cidic.design.model.MailBean;
import com.cidic.design.model.Production;
import com.cidic.design.model.ProdutionPageModel;
import com.cidic.design.model.Review;
import com.cidic.design.model.RoundJudge;
import com.cidic.design.model.RoundScoreBean;
import com.cidic.design.model.SendEmail;
import com.cidic.design.service.MailService;
import com.cidic.design.service.ReviewService;
import com.cidic.design.util.ConfigInfo;
import com.cidic.design.util.ResponseCodeUtil;
import com.sun.mail.smtp.SMTPSendFailedException;


@Service
@Component
@Qualifier(value = "reviewServiceImpl")
@Transactional
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	@Qualifier(value = "reviewDaoImpl")
	private ReviewDao reviewDaoImpl;
	
	@Autowired
	@Qualifier(value = "judgeDaoImpl")
	private JudgeDao judgeDaoImpl;
	
	@Autowired
	@Qualifier(value = "configInfo")
	private ConfigInfo configInfo;
	
	@Autowired
	@Qualifier(value = "mailServiceImpl")
	private MailService mailServiceImpl;
	
	@Autowired
	@Qualifier(value = "roundJudgeDaoImpl")
	private RoundJudgeDaoImpl roundJudgeDaoImpl;
	
	@Autowired
	@Qualifier(value = "productionDaoImpl")
	private ProductionDao productionDaoImpl;
	
	@Autowired
	@Qualifier(value = "sendEmailDaoImpl")
	private SendEmailDao sendEmailDaoImpl;
	
	@Override
	public void createReview(Review review) {
		reviewDaoImpl.createReview(review);
	}

	@Override
	public void updateReview(Review review) {
		reviewDaoImpl.updateReview(review);
	}

	@Override
	public void deleteReview(int id) {
		reviewDaoImpl.deleteReview(id);
	}

	@Override
	public List<Review> getReviewListByProductionId(int productionId) {
		return reviewDaoImpl.getReviewListByProductionId(productionId);
	}

	@Override
	public ProdutionPageModel getReviewListByUserId(int userId, int scoreSign, int round, int offset, int limit) {
		ProdutionPageModel produtionPageModel = new ProdutionPageModel();
		produtionPageModel.setList(reviewDaoImpl.getReviewListByUserId(userId, scoreSign, round, offset, limit));
		produtionPageModel.setCount(reviewDaoImpl.getCountReviewByUserId(userId, scoreSign, round));
		return produtionPageModel;
	}

	@Override
	public void createReviews(int userId, String productIds, int round) {
		String[] productStrIds = productIds.split("\\,");
		for (String pId : productStrIds){
			Review review = new Review();
			review.setCreatetime(new Date());
			review.setRound((byte)round);
			review.setUserId(userId);
			review.setProductionId(Integer.parseInt(pId));
			reviewDaoImpl.createReview(review);
		}
	}

	@Override
	public void updateReviewScore(int id, int score) {
		reviewDaoImpl.updateReviewScore(id, score);
	}

	@Override
	public List<String> getSendEmailByRound(int round) {
		
		return reviewDaoImpl.getSendEmailByRound(round);
	}

	@Override
	public void sendReviewEmail(int round, String emailContent,String testEmail) {
		List<String> emailList = reviewDaoImpl.getSendEmailByRound(round);	
		
		if (testEmail != null && !testEmail.equals("")){
			emailList.add(testEmail);
		}
		for (String email : emailList){
			String validCode = judgeDaoImpl.getJudgeValidCodeByEmail(email);
			if (validCode == null || validCode.equals("")){
				validCode = UUID.randomUUID().toString();
				judgeDaoImpl.updateJudgeValidCodeByEmail(email, validCode);
			}
			
			StringBuffer sBuilder = new StringBuffer(emailContent);
			sBuilder.append("<a style=\"color:#B03532\" href=\"" + configInfo.email_active_url + "/reviewLogin?email=");
			sBuilder.append(email);
			sBuilder.append("&validCode=");
			sBuilder.append(validCode);
			sBuilder.append("&round=");
			sBuilder.append(round);
			sBuilder.append("\">点击进行评审!!!");
			sBuilder.append("</a>");

			MailBean mailBean = new MailBean();
			mailBean.setContext(sBuilder.toString());
			mailBean.setFrom(configInfo.email_active_from);
			mailBean.setFromName(configInfo.email_active_from_name);
			mailBean.setSubject(configInfo.email_review_subject);
			mailBean.setToEmails(new String[] { email });
			SendEmail sendEmail = new SendEmail();
			sendEmail.setEmail(email);
			sendEmail.setCreatetime(new Date());
			sendEmail.setRound((byte)round);
			try {
				mailServiceImpl.sendMail(mailBean);
				sendEmail.setSign((byte)1);
				sendEmail.setRemark("发送成功");
			} catch(AuthenticationFailedException e){
				sendEmail.setSign((byte)3);
				sendEmail.setRemark("发送邮箱身份验证异常");
			}catch(SMTPSendFailedException e){
				sendEmail.setSign((byte)4);
				sendEmail.setRemark("SMTPSendFailedException");
			}catch (UnsupportedEncodingException e) {
				sendEmail.setSign((byte)2);
				sendEmail.setRemark("不支持的内容编码格式");
			} catch (MessagingException e) {
				sendEmail.setRemark("MessagingException");
				sendEmail.setSign((byte)2);
			}catch (Exception e){
				sendEmail.setRemark("其它错误");
				sendEmail.setSign((byte)5);
			}
			sendEmailDaoImpl.createSendEmail(sendEmail);
		}
		
	}

	@Override
	public int bindProductAndRound(int productionId, int round) {
		RoundJudge roundJudge = roundJudgeDaoImpl.getRoundJudgeById(round);
		if (roundJudge.getJudge() != null){
			//判断是否为审核通过的作品
			int status = productionDaoImpl.getProductionStatus(productionId);
			if (status > 2){
				String[] judges = roundJudge.getJudge().split("\\,");
				for (String judgeId : judges){
					Review review = new Review();
					review.setCreatetime(new Date());
					review.setRound((byte)round);
					review.setUserId(Integer.parseInt(judgeId));
					review.setProductionId(productionId);
					reviewDaoImpl.createReview(review);
				}
				productionDaoImpl.updateRoundById(productionId, round);
				return ResponseCodeUtil.BIND_JUDGE_SUCCESS;
			}
			else{
				return ResponseCodeUtil.BIND_JUDGE_FAILURE_WITH_NO_CHECK;
			}
		}
		else 
		{
			//请先绑定该轮评审评委
			return ResponseCodeUtil.BIND_JUDGE_FAILURE_WITH_NO_JUDGE;
		}
	}

	@Override
	public void updateReviewScoreByCondition(int productionId, int userId, int round, int score) {
		reviewDaoImpl.updateReviewScoreByCondition(productionId, userId, round, score);
	}

	@Override
	public int getScoreByCondition(int productionId, int userId, int round) {
		// TODO Auto-generated method stub
		return reviewDaoImpl.getScoreByCondition(productionId, userId, round);
	}

	@Override
	public List<RoundScoreBean> getRoundScoreBean(int productionId) {
		// TODO Auto-generated method stub
		return reviewDaoImpl.getRoundScoreBean(productionId);
	}

}
