package com.cidic.design.controller;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.cidic.design.model.News;
import com.cidic.design.model.NewsPageModel;
import com.cidic.design.model.ResultModel;
import com.cidic.design.service.NewsService;

/**
 * 大赛新闻信息处理
 * 
 * @author dev
 *
 */
@Controller
@RequestMapping(value = "/news")
public class NewsController extends DcController {

	@Autowired
	@Qualifier(value = "newsServiceImpl")
	private NewsService newsServiceImpl;

	@RequestMapping(value = "/news/{page}")
	public ModelAndView news(HttpServletRequest request, Model model, @PathVariable int page) {
		try {
			NewsPageModel newsPageModel = newsServiceImpl.findNewsByPage((page - 1) * 10, 10);
			newsPageModel.setCurrentPage(page);
			ModelAndView modelView = new ModelAndView();
			modelView.setViewName("frontend/news");
			modelView.addObject(newsPageModel);
			return modelView;
		} catch (Exception e) {
			throw new ServerException(400, "服务器内部出错了");
		}

	}

	@RequestMapping(value = "/newsDetail/{id}")
	public ModelAndView newsDetail(HttpServletRequest request, Model model, @PathVariable int id) {
		try {
			Optional<News> news = newsServiceImpl.findNewsById(id);
			ModelAndView modelView = new ModelAndView();
			modelView.setViewName("frontend/newsDetail");
			modelView.addObject(news.get());
			return modelView;
		} catch (Exception e) {
			throw new ServerException(400, "服务器内部出错了");
		}

	}

	@RequiresRoles(value = { "管理员" })
	@RequestMapping(value = "/newsMgr")
	public String newsMgr(HttpServletRequest request, Model model) {
		return "backend/newsMgr";
	}

	@RequiresRoles(value = { "管理员" })
	@RequestMapping(value = "/newsCOU")
	public String newsCOU(HttpServletRequest request, Model model) {
		return "backend/newsCOU";
	}

	@RequiresRoles(value = { "管理员" })
	@RequestMapping(value = "/newsCOU/{id}", method = RequestMethod.GET)
	public ModelAndView updateCOU(HttpServletRequest request, @PathVariable int id) {
		try {
			News news = null;
			if (id > 0) {
				news = newsServiceImpl.findNewsById(id).get();
			}

			ModelAndView model = new ModelAndView();
			model.setViewName("backend/newsCOU");
			model.addObject(news);
			return model;
		} catch (Exception e) {
			throw new ServerException(400, "服务器内部出错了");
		}

	}

	@RequiresRoles(value = { "管理员" })
	@ResponseBody
	@RequestMapping(value = "/createNews", method = RequestMethod.POST)
	public ResultModel createNews(HttpServletRequest request, HttpServletResponse response, @RequestBody News news) {
		resultModel = new ResultModel();
		try {
			news.setPublishTime(new Date());
			newsServiceImpl.createNews(news);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} catch (Exception e) {
			throw new DCException(500, "创建出错");
		}
	}

	@RequiresRoles(value = { "管理员" })
	@ResponseBody
	@RequestMapping(value = "/deleteNews/{id}", method = RequestMethod.POST)
	public ResultModel deleteNews(HttpServletRequest request, HttpServletResponse response, @PathVariable int id) {
		resultModel = new ResultModel();
		try {
			newsServiceImpl.deleteNews(id);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} catch (Exception e) {
			throw new DCException(500, "删除出错");
		}
	}

	@RequiresRoles(value = { "管理员" })
	@ResponseBody
	@RequestMapping(value = "/updateNews", method = RequestMethod.POST)
	public ResultModel updateNews(HttpServletRequest request, HttpServletResponse response, @RequestBody News news) {
		resultModel = new ResultModel();
		try {
			news.setPublishTime(new Date());
			newsServiceImpl.updateNews(news);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} catch (Exception e) {
			throw new DCException(500, "获取数据出错");
		}
	}

	@ResponseBody
	@RequestMapping(value = "/findNewsById/{id}", method = RequestMethod.GET)
	public ResultModel findNewsById(HttpServletRequest request, HttpServletResponse response, @PathVariable int id) {

		resultModel = new ResultModel();
		try {
			News news = newsServiceImpl.findNewsById(id).get();

			resultModel.setObject(news);
			resultModel.setSuccess(true);
			resultModel.setResultCode(200);
			return resultModel;
		} catch (Exception e) {
			throw new DCException(500, "获取数据出错");
		}
	}

	@ResponseBody
	@RequestMapping(value = "/findNewsByPage", method = RequestMethod.POST)
	public ListResultModel findNewsByPage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int iDisplayStart, @RequestParam int iDisplayLength, @RequestParam String sEcho) {

		ListResultModel listResultModel = new ListResultModel();
		try {
			NewsPageModel newsListModel = newsServiceImpl.findNewsByPage(iDisplayStart, iDisplayLength);
			listResultModel.setAaData(newsListModel.getList());
			listResultModel.setsEcho(sEcho);
			listResultModel.setiTotalRecords(newsListModel.getCount());
			listResultModel.setiTotalDisplayRecords(newsListModel.getCount());
			listResultModel.setSuccess(true);
		} catch (Exception e) {
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}
}
