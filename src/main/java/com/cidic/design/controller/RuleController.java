package com.cidic.design.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.cidic.design.DcController;
import com.cidic.design.exception.DCException;
import com.cidic.design.model.ResultModel;
import com.cidic.design.model.Rule;
import com.cidic.design.service.RuleService;

/**
 * 大赛规则信息
 * @author dev
 *
 */
@Controller
@RequestMapping(value="/rule")
public class RuleController  extends DcController{

	@Autowired
	@Qualifier(value = "ruleServiceImpl")
	private RuleService ruleServiceImpl;
	
	@RequestMapping(value = "/rules")
	public String rules(HttpServletRequest request, Model model) {
		return "frontend/rule";
	}
	
	@RequiresRoles(value ={"管理员"})
	@ResponseBody
	@RequestMapping(value="/createRule", method = RequestMethod.POST)
	public ResultModel createRule(HttpServletRequest request, HttpServletResponse response,@RequestBody Rule rule){
		resultModel = new ResultModel();
		try{
			rule.setCreateTime(new Date());
			ruleServiceImpl.createRule(rule);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new DCException(500, "创建出错");
		}
	}
	
	@RequiresRoles(value ={"管理员"})
	@ResponseBody
	@RequestMapping(value="/updateRule", method = RequestMethod.POST)
	public ResultModel updateRule(HttpServletRequest request, HttpServletResponse response,@RequestBody Rule rule){
		resultModel = new ResultModel();
		try{
			rule.setCreateTime(new Date());
			ruleServiceImpl.updateRule(rule);
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
	@RequestMapping(value="/deleteRule/{id}", method = RequestMethod.POST)
	public ResultModel deleteRule(HttpServletRequest request, HttpServletResponse response,@PathVariable int id){
		resultModel = new ResultModel();
		try{
			ruleServiceImpl.deleteRule(id);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getAllRule", method = RequestMethod.POST)
	public ResultModel getAllRule(){
		resultModel = new ResultModel();
		try{
			List<Rule> list = ruleServiceImpl.getAllRule();
			resultModel.setResultCode(200);
			resultModel.setObject(list);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getRuleById/{id}", method = RequestMethod.GET)
	public ResultModel getRuleById(HttpServletRequest request, HttpServletResponse response,@PathVariable int id){
		resultModel = new ResultModel();
		try{
			Optional<Rule> rule = ruleServiceImpl.getRuleById(id);
			resultModel.setResultCode(200);
			resultModel.setObject(rule.get());
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
}
