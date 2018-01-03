package com.cidic.design.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cidic.design.DcController;
import com.cidic.design.exception.DCException;
import com.cidic.design.exception.ServerException;
import com.cidic.design.model.Production;
import com.cidic.design.model.ProdutionPageModel;
import com.cidic.design.model.ResultModel;
import com.cidic.design.model.Review;
import com.cidic.design.model.RoundScoreBean;
import com.cidic.design.service.JudgeService;
import com.cidic.design.service.ProductionService;
import com.cidic.design.service.ReviewService;
import com.cidic.design.util.ResponseCodeUtil;

/**
 * 作品评分记录
 * @author dev
 *
 */
@Controller
@RequestMapping(value="/review")
public class ReviewController extends DcController {

	@Autowired
	@Qualifier(value = "reviewServiceImpl")
	private ReviewService reviewServiceImpl;
	
	@Autowired
	@Qualifier(value = "productionServiceImpl")
	private ProductionService productionServiceImpl;
	
	@Autowired
	@Qualifier(value = "judgeServiceImpl")
	private JudgeService judgeServiceImpl;
	
	@RequiresRoles(value = { "管理员" })
	@RequestMapping(value = "/sendEmail")
	public String sendEmail(HttpServletRequest request, Model model) {
		return "backend/sendEmail";
	}
	
	@RequiresRoles(value = { "管理员" })
	@RequestMapping(value = "/scoreMgr")
	public String scoreMgr(HttpServletRequest request, Model model) {
		return "backend/scoreMgr";
	}
	
	@RequestMapping(value = "/judgeIndex/{round}")
	public ModelAndView judgeIndex(HttpServletRequest request, Model model, @PathVariable int round) {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("frontend/judge/index");
		Subject subject = SecurityUtils.getSubject();
		int judgeId = judgeServiceImpl.findJudgeIdByEmail(subject.getSession().getAttribute("email").toString());
		modelView.addObject("judgeId",judgeId);
		modelView.addObject("round",round);
		return modelView;
	}
	
	@RequestMapping(value = "/score/{id}")
	public ModelAndView score(HttpServletRequest request, Model model, @PathVariable int id) {
		try {
			Optional<Production> production = productionServiceImpl.getProductionDetailById(id);
			ModelAndView modelView = new ModelAndView();
			modelView.setViewName("frontend/judge/score");
			Subject subject = SecurityUtils.getSubject();
			int judgeId = judgeServiceImpl.findJudgeIdByEmail(subject.getSession().getAttribute("email").toString());
			int score = reviewServiceImpl.getScoreByCondition(id, judgeId, production.get().getRound());
			modelView.addObject("production",production.get());
			modelView.addObject("judgeId",judgeId);
			modelView.addObject("score",score);
			return modelView;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServerException(400, "服务器内部出错了");
		}

	}
	
	/**
	 * 针对评委创建多个多个评分的作品任务，
	 * @param request
	 * @param response
	 * @param userId
	 * @param productIds  = example: “1,3,5,6,7”
	 * @return
	 */
	@RequiresRoles(value ={"评委"})
	@ResponseBody
	@RequestMapping(value="/createReviews", method = RequestMethod.POST)
	public ResultModel createReviews(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int userId, @RequestParam String productIds, @RequestParam  int round){
		resultModel = new ResultModel();
		try{
			reviewServiceImpl.createReviews(userId, productIds,round);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
	
	@RequiresRoles(value ={"管理员"})
	@ResponseBody
	@RequestMapping(value="/bindProductAndRound", method = RequestMethod.POST)
	public ResultModel bindProductAndRound(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int productId, @RequestParam  int round){
		resultModel = new ResultModel();
		try{
			int result = reviewServiceImpl.bindProductAndRound(productId,round);
			if (result == ResponseCodeUtil.BIND_JUDGE_SUCCESS){
				resultModel.setResultCode(200);
				resultModel.setSuccess(true);
			}
			else if (result == ResponseCodeUtil.BIND_JUDGE_FAILURE_WITH_NO_CHECK){
				resultModel.setResultCode(300);
				resultModel.setSuccess(false);
				resultModel.setMessage("作品审核未通过");
			}
			else if (result == ResponseCodeUtil.BIND_JUDGE_FAILURE_WITH_NO_JUDGE){
				resultModel.setResultCode(300);
				resultModel.setSuccess(false);
				resultModel.setMessage("评审轮次未绑定评委");
			}
			else if (result == ResponseCodeUtil.BIND_JUDGE_FAILURE_OTHER){
				resultModel.setResultCode(500);
				resultModel.setSuccess(false);
				resultModel.setMessage("其他错误");
			}
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
	
	@RequiresRoles(value ={"评委"})
	@ResponseBody
	@RequestMapping(value="/createReview", method = RequestMethod.POST)
	public ResultModel createReview(HttpServletRequest request, HttpServletResponse response,@RequestBody Review review){
		resultModel = new ResultModel();
		try{
			review.setCreatetime(new Date());
			reviewServiceImpl.createReview(review);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
	
	@RequiresRoles(value ={"评委"})
	@ResponseBody
	@RequestMapping(value="/updateReview", method = RequestMethod.POST)
	public ResultModel updateReview(HttpServletRequest request, HttpServletResponse response,@RequestBody Review review){
		resultModel = new ResultModel();
		try{
			review.setCreatetime(new Date());
			reviewServiceImpl.updateReview(review);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
	
	@RequiresRoles(value ={"评委"})
	@ResponseBody
	@RequestMapping(value="/deleteReview/{id}", method = RequestMethod.GET)
	public ResultModel deleteReview(HttpServletRequest request, HttpServletResponse response,@PathVariable int id){
		resultModel = new ResultModel();
		try{
			reviewServiceImpl.deleteReview(id);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getReviewListByProductionId/{productionId}", method = RequestMethod.GET)
	public ResultModel getReviewListByProductionId(HttpServletRequest request, HttpServletResponse response,@PathVariable int productionId){
		resultModel = new ResultModel();
		try{
			List<Review> list = reviewServiceImpl.getReviewListByProductionId(productionId);
			resultModel.setResultCode(200);
			resultModel.setObject(list);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "读取数据出错");
		}
	}
	
	@RequiresRoles(value ={"评委"})
	@ResponseBody
	@RequestMapping(value="/getReviewListByUserId", method = RequestMethod.POST)
	public ResultModel getReviewListByUserId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int userId, @RequestParam int scoreSign,  @RequestParam int round, @RequestParam  int offset,@RequestParam  int limit){
		resultModel = new ResultModel();
		try{
			ProdutionPageModel produtionPageModel = reviewServiceImpl.getReviewListByUserId(userId,scoreSign, round, offset, limit);
			resultModel.setResultCode(200);
			resultModel.setObject(produtionPageModel);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new DCException(500, "读取数据出错");
		}
	}
	
	@RequiresRoles(value ={"评委"})
	@ResponseBody
	@RequestMapping(value="/updateReviewScore", method = RequestMethod.POST)
	public ResultModel updateReviewScore(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int productionId, @RequestParam int userId, @RequestParam int round, @RequestParam int score){
		resultModel = new ResultModel();
		try{
			reviewServiceImpl.updateReviewScoreByCondition(productionId, userId, round, score);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "提交数据出错");
		}
	}
	
	@RequiresRoles(value ={"管理员"})
	@ResponseBody
	@RequestMapping(value="/sendReviewEmail", method = RequestMethod.POST)
	public ResultModel sendReviewEmail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int round, @RequestParam String emailContent, @RequestParam(required=false) String email){
		System.out.println("roundroundroundroundroundround:"+round);
		resultModel = new ResultModel();
		try{
			reviewServiceImpl.sendReviewEmail(round,emailContent,email);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "提交数据出错");
		}
	}
	
	@RequiresRoles(value ={"管理员"})
	@ResponseBody
	@RequestMapping(value="/getScoreByProductId", method = RequestMethod.POST)
	public ResultModel getScoreByProductId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int productionId){
		
		resultModel = new ResultModel();
		try{
			List<RoundScoreBean> list = reviewServiceImpl.getRoundScoreBean(productionId);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			resultModel.setObject(list);
			return resultModel;
		}
		catch(Exception e){
			e.printStackTrace();
			
			throw new DCException(500, "提交数据出错");
		}
	}
}
