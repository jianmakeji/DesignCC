package com.cidic.design.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cidic.design.DcController;
import com.cidic.design.exception.DCException;
import com.cidic.design.model.ResultModel;
import com.cidic.design.model.Stage;
import com.cidic.design.service.StageService;

/**
 * 大赛阶段信息
 * @author dev
 *
 */
@Controller
@RequestMapping(value="/stage")
public class StageController  extends DcController{

	@Autowired
	@Qualifier(value = "stageServiceImpl")
	private StageService stageServiceImpl;
	
	@RequiresRoles(value ={"管理员"})
	@ResponseBody
	@RequestMapping(value="/createStage", method = RequestMethod.POST)
	public ResultModel createStage(HttpServletRequest request, HttpServletResponse response,@RequestBody Stage stage){
		resultModel = new ResultModel();
		try{
			stage.setCreateTime(new Date());
			stageServiceImpl.createStage(stage);
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
	@RequestMapping(value="/updateStage", method = RequestMethod.POST)
	public ResultModel updateStage(HttpServletRequest request, HttpServletResponse response,@RequestBody Stage stage){
		resultModel = new ResultModel();
		try{
			stage.setCreateTime(new Date());
			stageServiceImpl.updateStage(stage);
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
	@RequestMapping(value="/deleteStage/{id}", method = RequestMethod.GET)
	public ResultModel deleteStage(HttpServletRequest request, HttpServletResponse response,@PathVariable int id){
		resultModel = new ResultModel();
		try{
			stageServiceImpl.deleteStage(id);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getAllStage", method = RequestMethod.GET)
	public ResultModel getAllStage(HttpServletRequest request, HttpServletResponse response){
		resultModel = new ResultModel();
		try{
			List<Stage> list = stageServiceImpl.getAllStage();
			resultModel.setObject(list);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
}
