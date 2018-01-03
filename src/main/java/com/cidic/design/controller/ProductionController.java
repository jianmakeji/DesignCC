package com.cidic.design.controller;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
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
import com.cidic.design.model.ListResultModel;
import com.cidic.design.model.Production;
import com.cidic.design.model.ProdutionPageModel;
import com.cidic.design.model.ResultModel;
import com.cidic.design.service.ProductionService;
import com.cidic.design.util.ConfigInfo;
import com.cidic.design.util.DateUtil;

/**
 * 选手作品信息处理
 * @author dev
 *
 */
@Controller
@RequestMapping(value="/production")
public class ProductionController  extends DcController{

	@Autowired
	@Qualifier(value = "productionServiceImpl")
	private ProductionService productionServiceImpl;
	
	@Autowired
	@Qualifier(value = "configInfo")
	private ConfigInfo configInfo;
	
	@RequiresRoles(value ={"管理员"})
	@RequestMapping(value = "/worksMgr")
	public String works(HttpServletRequest request, Model model) throws AuthorizationException{
		return "/backend/worksMgr";
	}
	
	@RequiresRoles(value ={"竞赛者"})
	@RequestMapping(value = "/uploadWork")
	public ModelAndView uploadWork(HttpServletRequest request, Model model) throws ServerException {
		
		if(DateUtil.compareDate(configInfo.contribute_end_time)){
			ModelAndView modelView = new ModelAndView();
			modelView.setViewName("/frontend/uploadWork");
			Subject subject = SecurityUtils.getSubject();
			modelView.addObject(subject.getSession().getAttribute("userId").toString());
			return modelView;
		}
		else{
			throw new ServerException(400, "投稿已经结束");//投稿结束页面
		}
	}
	
	//@RequiresRoles(value ={"竞赛者"})
	@RequestMapping(value = "/workDetail/{id}")
	public ModelAndView worksMgr(HttpServletRequest request, Model model,@PathVariable int id) throws ServerException {
		ModelAndView modelView = new ModelAndView();
		Production production = null;
		if (id > 0){
			production = productionServiceImpl.getProductionDetailById(id).get();
		}
		modelView.setViewName("/frontend/workDetail");
		modelView.addObject(production);
		return modelView;
	}
	
	@RequiresRoles(value ={"管理员"})
	@RequestMapping(value = "/manageWorkDetail/{id}")
	public ModelAndView manageWorkDetail(HttpServletRequest request, Model model,@PathVariable int id) throws ServerException {
		ModelAndView modelView = new ModelAndView();
		Production production = null;
		if (id > 0){
			production = productionServiceImpl.getProductionDetailById(id).get();
		}
		modelView.setViewName("/backend/workDetail");
		modelView.addObject(production);
		return modelView;
	}
	
	@RequiresRoles(value ={"竞赛者"})
	@RequestMapping(value = "/works")
	public ModelAndView worksMgr(HttpServletRequest request, Model model) throws ServerException {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("/frontend/works");
		Subject subject = SecurityUtils.getSubject();
		modelView.addObject(subject.getSession().getAttribute("userId").toString());
		return modelView;
	}
	
	@RequiresRoles(value ={"竞赛者"})
	@RequestMapping(value = "/uploadWork/{id}")
	public ModelAndView uploadWork(HttpServletRequest request, @PathVariable int id) throws ServerException{
		Production production = null;
		ModelAndView model = new ModelAndView();
		if(DateUtil.compareDate(configInfo.contribute_end_time)){
			if (id > 0){
				production = productionServiceImpl.getProductionDetailById(id).get();
			}
			model.setViewName("/frontend/uploadWork");
			model.addObject(production);
		}
		else{
			throw new ServerException(400, "投稿已经结束");//投稿结束页面
		}
		
        return model;
	}
	
	@RequiresRoles(value ={"竞赛者"})
	@ResponseBody
	@RequestMapping(value="/createProduction", method = RequestMethod.POST)
	public ResultModel createProduction(HttpServletRequest request, HttpServletResponse response,@RequestBody Production production) throws DCException{
		resultModel = new ResultModel();
		try{
			if(DateUtil.compareDate(configInfo.contribute_end_time)){
				Subject subject = SecurityUtils.getSubject();
				production.setUserId(Integer.parseInt(subject.getSession().getAttribute("userId").toString()));
				production.setCreateTime(new Date());
				productionServiceImpl.createProduction(production);
				resultModel.setResultCode(200);
				resultModel.setSuccess(true);
				return resultModel;
			}
			else{
				throw new DCException(600, "投稿日期结束");
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
			throw new DCException(500, "创建出错");
		}
	}
	
	@RequiresRoles(value ={"竞赛者"})
	@ResponseBody
	@RequestMapping(value="/updateProduction", method = RequestMethod.POST)
	public ResultModel updateProduction(HttpServletRequest request, HttpServletResponse response,@RequestBody Production production) throws DCException{
		resultModel = new ResultModel();
		try{
			if(DateUtil.compareDate(configInfo.contribute_end_time)){
				Subject subject = SecurityUtils.getSubject();
				production.setUserId(Integer.parseInt(subject.getSession().getAttribute("userId").toString()));
				production.setCreateTime(new Date());
				productionServiceImpl.updateProduction(production);
				resultModel.setResultCode(200);
				resultModel.setSuccess(true);
				return resultModel;
			}
			else{
				throw new DCException(600, "投稿日期结束");
			}
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
	
	@RequiresRoles(value ={"竞赛者"})
	@ResponseBody
	@RequestMapping(value="/deleteProduction/{id}", method = RequestMethod.GET)
	public ResultModel deleteProduction(HttpServletRequest request, HttpServletResponse response,@PathVariable int id) throws DCException{
		resultModel = new ResultModel();
		try{
			if(DateUtil.compareDate(configInfo.contribute_end_time)){
				productionServiceImpl.deleteProduction(id);
				resultModel.setResultCode(200);
				resultModel.setSuccess(true);
				return resultModel;
			}
			else{
				throw new DCException(600, "投稿日期结束");
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
			throw new DCException(500, "删除出错");
		}
	}
	
	/**
	 * 获取投稿作品信息
	 * @param request
	 * @param response
	 * @param offset
	 * @param limit
	 * @param groupId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getListProductionByPage", method = RequestMethod.POST)
	public ResultModel getListProductionByPage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int offset, @RequestParam int limit, @RequestParam(required=false) Integer groupId){
		
		resultModel = new ResultModel();
		try{
			Subject subject = SecurityUtils.getSubject();
			int userId = Integer.parseInt(subject.getSession().getAttribute("userId").toString());
			ProdutionPageModel produtionPageModel = productionServiceImpl.getListOnlyProductionInfoByPage(offset, limit, groupId,userId);
			resultModel.setResultCode(200);
			resultModel.setObject(produtionPageModel);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "获取数据出错");
		}
	}
	
	/**
	 * 获取投稿作品信息，包含作品对应的竞赛者信息
	 * @param request
	 * @param response
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param sEcho
	 * @param groupId
	 * @return
	 */
	@RequiresRoles(value ={"管理员"})
	@ResponseBody
	@RequestMapping(value="/getDataTableProductionByPage", method = RequestMethod.POST)
	public ListResultModel getDataTableProductionByPage(HttpServletRequest request, HttpServletResponse response,
			 @RequestParam int iDisplayStart, @RequestParam int iDisplayLength,@RequestParam String sEcho, 
			 @RequestParam(required=false) Integer groupId,@RequestParam(required=false) Integer round,@RequestParam(required=false) Integer status){
		
		ListResultModel listResultModel = new ListResultModel();
		try {
			
			ProdutionPageModel pPageModel = productionServiceImpl.getListProductionByPage(iDisplayStart, iDisplayLength, groupId, round, status);
			
			listResultModel.setAaData(pPageModel.getList());
			
			listResultModel.setsEcho(sEcho);
			listResultModel.setiTotalRecords(pPageModel.getCount());
			listResultModel.setiTotalDisplayRecords(pPageModel.getCount());
			listResultModel.setSuccess(true);
		}
		catch (Exception e) {
			e.printStackTrace();
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getProductionDetailById/{id}", method = RequestMethod.GET)
	public ResultModel getProductionDetailById(HttpServletRequest request, HttpServletResponse response,@PathVariable int id){
		
		resultModel = new ResultModel();
		try{
			Optional<Production> production = productionServiceImpl.getProductionDetailById(id);
			resultModel.setObject(production.get());
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "获取数据出错");
		}
	}
	
	@RequiresRoles(value ={"管理员"})
	@ResponseBody
	@RequestMapping(value="/updateProductionScore", method = RequestMethod.POST)
	public ResultModel updateProductionScore(HttpServletRequest request, HttpServletResponse response,@RequestParam int round){
		
		resultModel = new ResultModel();
		try{
			productionServiceImpl.updateProductionScore(round);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new DCException(500, "计算总分出错");
		}
	}
	
	/**
	 * 参赛者管理自己的投稿作品，可以进行有条件的查询
	 * @param request
	 * @param response
	 * @param groupId
	 * @param category
	 * @param status
	 * @param userId
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param sEcho
	 * @return
	 */
	//@RequiresRoles(value ={"竞赛者"})
	@ResponseBody
	@RequestMapping(value="/getProductionPageByCondition", method = RequestMethod.GET)
	public ListResultModel getProductionPageByCondition(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam int groupId, @RequestParam int category, @RequestParam int status, @RequestParam int userId, @RequestParam int round,
			@RequestParam int iDisplayStart, @RequestParam int iDisplayLength,@RequestParam String sEcho){
		
		ListResultModel listResultModel = new ListResultModel();
		try {
			Subject subject = SecurityUtils.getSubject();
			
			try{
				subject.checkRole("竞赛者");
				userId = Integer.parseInt(subject.getSession().getAttribute("userId").toString());
			}
			catch (AuthorizationException e) {
				userId = 0;
			}

			ProdutionPageModel pPageModel = productionServiceImpl.getProductionPageByCondition(groupId, category, status, userId, round, iDisplayLength, iDisplayStart);
			
			listResultModel.setAaData(pPageModel.getList());
			
			listResultModel.setsEcho(sEcho);
			listResultModel.setiTotalRecords(pPageModel.getCount());
			listResultModel.setiTotalDisplayRecords(pPageModel.getCount());
			listResultModel.setSuccess(true);
		}
		catch (Exception e) {
			e.printStackTrace();
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}
	
	@RequiresRoles(value ={"管理员"})
	@ResponseBody
	@RequestMapping(value="/updateProductionStatus", method = RequestMethod.GET)
	public ResultModel updateProductionStatus(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int id,@RequestParam int status){
		
		resultModel = new ResultModel();
		try{
			productionServiceImpl.updateProductionStatus(id,status);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new DCException(500, "设置作品状态出错");
		}
	}
}
