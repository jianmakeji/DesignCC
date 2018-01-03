package com.cidic.design.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.cidic.design.model.FileInfo;
import com.cidic.design.model.ResultModel;
import com.cidic.design.service.FileInfoService;

/**
 * 用于文件信息的处理，例如章程文件上传保存
 * @author dev
 *
 */
@Controller
@RequestMapping(value="/fileInfo")
public class FileInfoController  extends DcController{

	@Autowired
	@Qualifier(value = "fileInfoServiceImpl")
	private FileInfoService fileInfoServiceImpl;
	
	@ResponseBody
	@RequestMapping(value="/createFile", method = RequestMethod.POST)
	public ResultModel createFile(HttpServletRequest request, HttpServletResponse response,@RequestBody FileInfo file){
		resultModel = new ResultModel();
		try{
			fileInfoServiceImpl.createFile(file);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "创建出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteFile/{id}", method = RequestMethod.GET)
	public ResultModel deleteFile(HttpServletRequest request, HttpServletResponse response,@PathVariable int id){
		resultModel = new ResultModel();
		try{
			fileInfoServiceImpl.deleteFile(id);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "删除出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/updateFile", method = RequestMethod.POST)
	public ResultModel updateFile(HttpServletRequest request, HttpServletResponse response,@RequestBody FileInfo file){
		resultModel = new ResultModel();
		try{
			fileInfoServiceImpl.updateFile(file);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "更新出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getAllFile", method = RequestMethod.POST)
	public ResultModel getAllFile(){
		resultModel = new ResultModel();
		try{
			List<FileInfo> list = fileInfoServiceImpl.getAllFile();
			resultModel.setResultCode(200);
			resultModel.setObject(list);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "获取数据出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getFileByType/{type}", method = RequestMethod.GET)
	public ResultModel getFileByType(HttpServletRequest request, HttpServletResponse response,@PathVariable int type){
		resultModel = new ResultModel();
		try{
			List<FileInfo> list = fileInfoServiceImpl.getFileByType(type);
			resultModel.setObject(list);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "获取数据出错");
		}
	}
}
