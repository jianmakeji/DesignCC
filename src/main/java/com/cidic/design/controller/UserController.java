package com.cidic.design.controller;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cidic.design.DcController;
import com.cidic.design.exception.DCException;
import com.cidic.design.exception.ServerException;
import com.cidic.design.model.FindPwd;
import com.cidic.design.model.ListResultModel;
import com.cidic.design.model.ResultModel;
import com.cidic.design.model.User;
import com.cidic.design.model.UserPageModel;
import com.cidic.design.service.FindPwdService;
import com.cidic.design.service.UserService;
import com.cidic.design.util.GraphicsUtil;
import com.cidic.design.util.ResponseCodeUtil;

/**
 * 大赛用户信息处理
 * 
 * @author dev
 *
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends DcController {

	@Autowired
	@Qualifier(value = "userServiceImpl")
	private UserService userServiceImpl;

	@Autowired
	@Qualifier(value = "findPwdServiceImpl")
	private FindPwdService findPwdServiceImpl;

	@RequestMapping(value = "/forgetPwd")
	public String news(HttpServletRequest request, Model model) {
		return "/frontend/forgetPwd";
	}

	@RequestMapping(value = "/userMgr")
	public String userMgr(HttpServletRequest request, Model model) {
		return "/backend/userMgr";
	}

	@RequestMapping(value = "/resetInfo")
	public ModelAndView resetInfo(HttpServletRequest request, Model model) {
		try {
			Subject subject = SecurityUtils.getSubject();
			Object email = subject.getSession().getAttribute("email");
			ModelAndView modelView = new ModelAndView();
			if (email != null) {
				Optional<User> user = userServiceImpl.findByEmail(email.toString());
				modelView.addObject(user.get());
				modelView.setViewName("/frontend/resetInfo");
			} else {
				modelView.setViewName("/frontend/login");
			}

			return modelView;
		} catch (Exception e) {
			throw new ServerException(400, "服务器内部出错了");
		}

	}

	@RequestMapping(value = "/resetPwd")
	public String resetPwd(HttpServletRequest request, Model model) {
		return "/frontend/resetPwd";
	}

	@RequestMapping(value = "/register")
	public String register(HttpServletRequest request, Model model) {
		return "/frontend/register";
	}

	/**
	 * 用户注册
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResultModel registerUser(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) throws DCException{
		resultModel = new ResultModel();
		
			Object sessionRand = request.getSession().getAttribute("rand");
			//前端的值传递过来验证码放在activeCode属性值中
			if (sessionRand != null && sessionRand.toString().equalsIgnoreCase(user.getActivecode())) {
				user.setCreatetime(new Date());
				int result = 0;
				try {
					result = userServiceImpl.createUser(user);
				} catch (Exception e) {
					e.printStackTrace();
					throw new DCException(500, "创建出错");
				}
				
				if (result == ResponseCodeUtil.UESR_CREATE_EXIST) {
					resultModel.setResultCode(300);
					resultModel.setSuccess(false);
					resultModel.setMessage("用户已经存在!");
				} else {
					resultModel.setResultCode(200);
					resultModel.setSuccess(true);
				}

				return resultModel;
			} else {
				throw new DCException(400, "验证码不正确");
			}
		

	}

	/**
	 * 用户信息更新
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResultModel updateUser(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
		resultModel = new ResultModel();
		try {
			Subject subject = SecurityUtils.getSubject();
			user.setEmail(subject.getSession().getAttribute("email").toString());
			user.setCreatetime(new Date());
			userServiceImpl.updateUser(user);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} catch (Exception e) {
			throw new DCException(500, "更新出错");
		}
	}

	/**
	 * 用户激活
	 * 
	 * @param request
	 * @param response
	 * @param email
	 * @param activeCode
	 * @return
	 */

	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public ModelAndView activeUser(HttpServletRequest request, HttpServletResponse response, @RequestParam String email,
			@RequestParam String activeCode) {
		try {
			userServiceImpl.activeUser(email, activeCode);
			ModelAndView modelView = new ModelAndView();
			modelView.setViewName("/frontend/login");
			return modelView;
		} catch (Exception e) {
			throw new ServerException(400, "服务器内部出错了");
		}

	}

	/**
	 * 找回密码
	 * 
	 * @param request
	 * @param response
	 * @param email
	 * @return 500:出错， 300：email不正确， 200:请求成功，请查看邮箱
	 */
	@ResponseBody
	@RequestMapping(value = "/findYourPwd", method = RequestMethod.POST)
	public ResultModel findYourPwd(HttpServletRequest request, HttpServletResponse response, @RequestParam String email,
			@RequestParam String rand) throws DCException {
		resultModel = new ResultModel();
		Object sessionRand = request.getSession().getAttribute("rand");
		if (sessionRand != null && sessionRand.toString().equalsIgnoreCase(rand)) {
			FindPwd findPwd = new FindPwd();
			findPwd.setEmail(email);
			int result = findPwdServiceImpl.createFindPwd(findPwd);

			if (result == ResponseCodeUtil.UESR_OPERATION_USER_IS_NOT_EXISTS) {
				throw new DCException(300, "用户不存在");
			} else {
				resultModel.setResultCode(200);
				resultModel.setSuccess(true);
				return resultModel;
			}
		} else {
			throw new DCException(400, "验证码不正确");
		}

	}

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @param response
	 * @param newPwd
	 * @return
	 */
	@RequestMapping(value = "/getFindPwdByCondition", method = RequestMethod.GET)
	public ModelAndView getFindPwdByCondition(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String email, @RequestParam String validCode, @RequestParam int id) {
		resultModel = new ResultModel();
		try {
			int result = findPwdServiceImpl.getFindPwdByCondition(email, validCode, id);
			if (ResponseCodeUtil.USER_FINDPWD_LINK_OUT_TIME == result) {
				resultModel.setResultCode(300);
				resultModel.setSuccess(false);
				resultModel.setMessage("修改链接超时！");
			} else if (ResponseCodeUtil.USER_FINDPWD_LINK_VALID_ERROR == result) {
				resultModel.setResultCode(300);
				resultModel.setSuccess(false);
				resultModel.setMessage("链接验证码不正确！");
			} else {
				Optional<User> user = userServiceImpl.findByEmail(email);
				resultModel.setResultCode(200);
				resultModel.setSuccess(true);
				resultModel.setObject(user.get().getSlot());
			}
		} catch (Exception e) {
			resultModel.setResultCode(500);
			resultModel.setSuccess(false);
			resultModel.setMessage("修改出错！");
		}

		ModelAndView model = new ModelAndView();
		model.setViewName("/frontend/setPwd");
		model.addObject(resultModel);
		return model;
	}

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @param response
	 * @param newPwd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/resetYourPwd", method = RequestMethod.POST)
	public ResultModel resetYourPwd(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String email, @RequestParam String newPwd, @RequestParam String code) {
		resultModel = new ResultModel();
		try {
			userServiceImpl.updatePwd(email, newPwd, code);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} catch (Exception e) {
			throw new DCException(500, "修改出错");
		}
	}

	@RequestMapping(value = "/getCode", method = RequestMethod.GET)
	public void getCode(HttpServletRequest request, HttpServletResponse response) {
		try {

			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			// 表明生成的响应是图片
			response.setContentType("image/jpeg");

			Map<String, Object> map = new GraphicsUtil().getGraphics();
			request.getSession().setAttribute("rand", map.get("rand"));
			ImageIO.write((RenderedImage) map.get("image"), "JPEG", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @param response
	 * @param newPwd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/resetLoginUserPwd", method = RequestMethod.POST)
	public ResultModel resetLoginUserPwd(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String newPwd) {
		resultModel = new ResultModel();
		try {
			userServiceImpl.resetLoginUserPwd(newPwd);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} catch (Exception e) {
			throw new DCException(500, "修改出错");
		}
	}

	@ResponseBody
	@RequestMapping(value = "/getDataTableUserByPage", method = RequestMethod.POST)
	public ListResultModel getDataTableUserByPage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int iDisplayStart, @RequestParam int iDisplayLength, @RequestParam String sEcho) {

		ListResultModel listResultModel = new ListResultModel();
		try {
			UserPageModel userPageModel = userServiceImpl.getUserByPage(iDisplayStart, iDisplayLength);
			listResultModel.setAaData(userPageModel.getList());
			listResultModel.setsEcho(sEcho);
			listResultModel.setiTotalRecords(userPageModel.getCount());
			listResultModel.setiTotalDisplayRecords(userPageModel.getCount());
			listResultModel.setSuccess(true);
		} catch (Exception e) {
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}

	@ResponseBody
	@RequestMapping(value = "/resetUserValid", method = RequestMethod.POST)
	public ResultModel resetUserValid(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String email, @RequestParam int valid) {
		resultModel = new ResultModel();
		try {
			userServiceImpl.updateValidSign(email, valid);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} catch (Exception e) {
			throw new DCException(500, "更新用户状态出错");
		}
	}
}
