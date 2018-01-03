package com.cidic.design.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
import com.cidic.design.model.ListResultModel;
import com.cidic.design.model.ResultModel;
import com.cidic.design.model.RoundJudge;
import com.cidic.design.model.RoundJudgeListModel;
import com.cidic.design.service.RoundJudgeService;

@Controller
@RequestMapping(value="/roundJudge")
public class RoundJudgeController  extends DcController {

	@Autowired
	@Qualifier(value = "roundJudgeServiceImpl")
	private RoundJudgeService roundJudgeServiceImpl;
	
	@RequiresRoles(value = { "管理员" })
	@RequestMapping(value = "/judgeRoundMgr")
	public String judgeMgr(HttpServletRequest request, Model model) throws AuthorizationException{
		return "backend/judgeRoundMgr";
	}

	@RequiresRoles(value = { "管理员" })
	@RequestMapping(value = "/judgeRoundCOU")
	public String judgeCOU(HttpServletRequest request, Model model) throws AuthorizationException{
		return "backend/judgeRoundCOU";
	}
	
	@RequiresRoles(value = { "管理员" })
	@RequestMapping(value = "/judgeRoundCOU/{id}", method = RequestMethod.GET)
	public ModelAndView updateCOU(HttpServletRequest request, @PathVariable int id) {
		try {
			RoundJudge roundJudge = null;
			if (id > 0) {
				roundJudge = roundJudgeServiceImpl.getRoundJudgeById(id);
			}

			ModelAndView model = new ModelAndView();
			model.setViewName("backend/judgeRoundCOU");
			model.addObject(roundJudge);
			return model;
		} catch (Exception e) {
			throw new ServerException(400, "服务器内部出错了");
		}

	}
	
	/**
	 * 创建轮次
	 * @param request
	 * @param response
	 * @param roundJudge
	 * @return
	 */
	@RequiresRoles(value ={"管理员"})
	@ResponseBody
	@RequestMapping(value="/createRoundJudge", method = RequestMethod.POST)
	public ResultModel createRoundJudge(HttpServletRequest request, HttpServletResponse response,
			@RequestBody RoundJudge roundJudge){
		resultModel = new ResultModel();
		try{
			roundJudgeServiceImpl.createRoundJudge(roundJudge);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "创建出错");
		}
	}
	
	/**
	 * 更新轮次和评委绑定关系
	 * @param request
	 * @param response
	 * @param id
	 * @param judge
	 * @return
	 */
	@RequiresRoles(value ={"管理员"})
	@ResponseBody
	@RequestMapping(value="/bindingRoundJudge", method = RequestMethod.POST)
	public ResultModel createRoundJudge(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int id, @RequestParam String judge){
		resultModel = new ResultModel();
		try{
			roundJudgeServiceImpl.bindingRoundJudge(id, judge);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "绑定出错");
		}
	}
	
	/**
	 * 修改信息
	 * @param request
	 * @param response
	 * @param roundJudge
	 * @return
	 */
	@RequiresRoles(value ={"管理员"})
	@ResponseBody
	@RequestMapping(value="/updateRoundJudge", method = RequestMethod.POST)
	public ResultModel updateRoundJudge(HttpServletRequest request, HttpServletResponse response,
			@RequestBody RoundJudge roundJudge){
		resultModel = new ResultModel();
		try{
			roundJudgeServiceImpl.updateRoundJudge(roundJudge);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
	
	/**
	 * 根据ID删除
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequiresRoles(value ={"管理员"})
	@ResponseBody
	@RequestMapping(value="/deleteRoundJudge/{id}", method = RequestMethod.POST)
	public ResultModel deleteRoundJudge(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int id){
		resultModel = new ResultModel();
		try{
			roundJudgeServiceImpl.deleteRoundJudge(id);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "删除出错");
		}
	}
	
	/**
	 * 修改信息
	 * @param request
	 * @param response
	 * @param roundJudge
	 * @return
	 */
	@RequiresRoles(value ={"管理员"})
	@ResponseBody
	@RequestMapping(value="/getRoundJudgeById", method = RequestMethod.GET)
	public ResultModel getRoundJudgeById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int id){
		resultModel = new ResultModel();
		try{
			RoundJudge roundJudge = roundJudgeServiceImpl.getRoundJudgeById(id);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			resultModel.setObject(roundJudge);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
	
	/**
	 * 获取绑定记录
	 * @param request
	 * @param response
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param sEcho
	 * @return
	 */
	@RequiresRoles(value ={"管理员"})
	@ResponseBody
	@RequestMapping(value="/getRoundJudgeByPage", method = RequestMethod.GET)
	public ListResultModel getRoundJudgeByPage(HttpServletRequest request, HttpServletResponse response,
			 @RequestParam int iDisplayStart, @RequestParam int iDisplayLength,@RequestParam String sEcho){
		
		ListResultModel listResultModel = new ListResultModel();
		try {
			
			RoundJudgeListModel roundJudgeListModel = roundJudgeServiceImpl.getRoundJudgeByPage(iDisplayStart, iDisplayLength);
			
			listResultModel.setAaData(roundJudgeListModel);
			listResultModel.setsEcho(sEcho);
			listResultModel.setiTotalRecords(roundJudgeListModel.getRjList().size());
			listResultModel.setiTotalDisplayRecords(roundJudgeListModel.getRjList().size());
			listResultModel.setSuccess(true);
		}
		catch (Exception e) {
			e.printStackTrace();
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}
}
