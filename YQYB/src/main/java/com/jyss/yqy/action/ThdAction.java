package com.jyss.yqy.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyss.yqy.entity.Page;
import com.jyss.yqy.entity.ResponseEntity;
import com.jyss.yqy.entity.ThOrders;
import com.jyss.yqy.entity.Thd;
import com.jyss.yqy.service.ThdService;
import com.jyss.yqy.utils.CommTool;
import com.jyss.yqy.utils.PasswordUtil;
import com.jyss.yqy.utils.Utils;

@Controller
public class ThdAction {
	@Autowired
	private ThdService thdService;

	// 提货点

	@RequestMapping("/thd")
	public String thdTz() {
		return "thd";
	}

	// 提货点商品详情列表
	@RequestMapping("/thdSp")
	public String thdSpTz() {
		return "thdSp";
	}

	// ///**==================提货点====================**////////
	@RequestMapping("/thdLogin")
	public ModelAndView getLogin(@RequestParam("logName") String logName,
			@RequestParam("logPass") String logPass,
			@RequestParam("logYzm") String logYzm, HttpServletRequest request) {
		// TODO Auto-generated method stub
		ModelAndView mav = new ModelAndView();
		// 验证验证码
		String codeImg = (String) request.getSession().getAttribute("codeImg");
		if (!(logYzm.toUpperCase().equals(codeImg))) {
			mav.addObject("error", "验证码错误！");
			mav.setViewName("error");
			return mav;
		}
		int isOnly = 0;
		isOnly = thdService.getThdNum(logName);
		if (isOnly == 0) {
			mav.addObject("error", "账号不存在！");
			mav.setViewName("error");
			return mav;
		}
		if (isOnly != 1) {
			mav.addObject("error", "账号冲突！");
			mav.setViewName("error");
			return mav;
		}
		Thd thd = thdService.getThdBy(logName).get(0);

		if (PasswordUtil.generate(logPass, thd.getSalt()).equals(
				thd.getPassword())) {
			HttpSession session = request.getSession();
			session.setAttribute("username", logName);
			// session.setMaxInactiveInterval(1 * 60 * 60);// 设置时间1小时
			mav.addObject("username", logName);
			mav.setViewName("index");
			return mav;
		} else {
			// 密码错误
			mav.addObject("error", "密码错误");
			mav.setViewName("error");
			return mav;
		}
	}

	@RequestMapping("/upThdPwd")
	@ResponseBody
	public ResponseEntity upHtPwd(@RequestParam("password") String password,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		int count = 0;
		String username = (String) request.getSession()
				.getAttribute("username");
		if (username.equals("") || username == null) {
			return new ResponseEntity("false", "操作失败！");
		}
		String salt = CommTool.getSalt();
		count = thdService.upHtPwd(username, password, salt);
		if (count == 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	@RequestMapping("/addThd")
	@ResponseBody
	public ResponseEntity addThd(Thd thd) {
		// TODO Auto-generated method stub
		int count = 0;
		int isOnly = 0;
		isOnly = thdService.getThdNum(thd.getName());
		if (isOnly >= 1) {
			return new ResponseEntity("NO", "账号冲突！");
		}
		if (thd.getId() == 0) {
			// 新增
			count = thdService.addThd(thd);
		} else {
			// 修改
			count = thdService.upThd(thd);
		}

		if (count == 1) {
			return new ResponseEntity("OK", "操作成功！");
		}
		return new ResponseEntity("NO", "操作失败！");
	}

	@RequestMapping("/delThd")
	@ResponseBody
	public ResponseEntity delThd(String strIds) {
		// TODO Auto-generated method stub
		int count = 0;
		List<Long> ids = Utils.stringToLongList(strIds, ",");
		count = thdService.deleteThds(ids);
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	@RequestMapping("/upThdZt")
	@ResponseBody
	public ResponseEntity upThdZt(String strIds,
			@RequestParam("status") String status) {
		// TODO Auto-generated method stub
		int count = 0;
		List<Long> ids = Utils.stringToLongList(strIds, ",");
		count = thdService.upThdStatus(ids, status);
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// 提货点用户列表
	@RequestMapping("/thdCx")
	@ResponseBody
	public Page<Thd> thdCx(@RequestParam("name") String name,
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		List<Thd> list = new ArrayList<Thd>();
		PageHelper.startPage(page, rows);// 分页语句
		list = thdService.getThdBy(name);
		PageInfo<Thd> pageInfoBy = new PageInfo<Thd>(list);
		return new Page<Thd>(pageInfoBy);
	}

	@RequestMapping("/thdSx")
	@ResponseBody
	public Page<Thd> thdSx(
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		List<Thd> list = new ArrayList<Thd>();
		PageHelper.startPage(page, rows);// 分页语句
		list = thdService.getThdBy("");
		PageInfo<Thd> pageInfoBy = new PageInfo<Thd>(list);
		return new Page<Thd>(pageInfoBy);
	}

	// ///**==================提货点==商品==================**////////

	// 提货点用户列表
	@RequestMapping("/thdSpCx")
	@ResponseBody
	public Page<ThOrders> thdSpCx(@RequestParam("thName") String thName,
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		List<ThOrders> list = new ArrayList<ThOrders>();
		PageHelper.startPage(page, rows);// 分页语句
		list = thdService.getThSpBy(thName);
		PageInfo<ThOrders> pageInfoBy = new PageInfo<ThOrders>(list);
		return new Page<ThOrders>(pageInfoBy);
	}

	@RequestMapping("/thdSpSx")
	@ResponseBody
	public Page<ThOrders> thdSpSx(
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		List<ThOrders> list = new ArrayList<ThOrders>();
		PageHelper.startPage(page, rows);// 分页语句
		list = thdService.getThSpBy("");
		PageInfo<ThOrders> pageInfoBy = new PageInfo<ThOrders>(list);
		return new Page<ThOrders>(pageInfoBy);
	}

}
