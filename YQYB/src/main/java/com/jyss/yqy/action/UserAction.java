package com.jyss.yqy.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyss.yqy.entity.Page;
import com.jyss.yqy.entity.ResponseEntity;
import com.jyss.yqy.entity.UserAuth;
import com.jyss.yqy.entity.jsonEntity.UserBean;
import com.jyss.yqy.service.UserService;

@Controller
public class UserAction {
	@Autowired
	private UserService userService;

	@RequestMapping("/dlrType")
	public String DlrTypeTz() {
		return "dlrType";
	}

	@RequestMapping("/dlrAuth")
	public String DlrAuthTz() {
		return "dlrAuth";
	}

	// 代理人统计列表
	@RequestMapping("/dlrTypeCx")
	@ResponseBody
	public Page<UserBean> dlrTypeCx(
			@RequestParam("isChuangke") String isChuangke,
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		List<UserBean> list = new ArrayList<UserBean>();
		PageHelper.startPage(page, rows);// 分页语句
		list = userService.getUserCount(isChuangke, "1", "2");
		PageInfo<UserBean> pageInfoBy = new PageInfo<UserBean>(list);
		return new Page<UserBean>(pageInfoBy);
	}

	@RequestMapping("/dlrTypeSx")
	@ResponseBody
	public Page<UserBean> dlrTypeSx(
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		List<UserBean> list = new ArrayList<UserBean>();
		PageHelper.startPage(page, rows);// 分页语句
		list = userService.getUserCount("2", "1", "2");
		PageInfo<UserBean> pageInfoBy = new PageInfo<UserBean>(list);
		return new Page<UserBean>(pageInfoBy);
	}

	// 代理人用户审核列表
	@RequestMapping("/dlrAuthCx")
	@ResponseBody
	public Page<UserAuth> dlrAuthCx(@RequestParam("account") String account,
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		List<UserAuth> list = new ArrayList<UserAuth>();
		PageHelper.startPage(page, rows);// 分页语句
		list = userService.getUserAuth("", account, "");
		PageInfo<UserAuth> pageInfoBy = new PageInfo<UserAuth>(list);
		return new Page<UserAuth>(pageInfoBy);
	}

	@RequestMapping("/dlrAuthSx")
	@ResponseBody
	public Page<UserAuth> dlrAuthSx(
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		List<UserAuth> list = new ArrayList<UserAuth>();
		PageHelper.startPage(page, rows);// 分页语句
		list = userService.getUserAuth("", "", "");
		PageInfo<UserAuth> pageInfoBy = new PageInfo<UserAuth>(list);
		return new Page<UserAuth>(pageInfoBy);
	}

	// ////0=审核中 1=通过 2=未通过
	@RequestMapping("/dlrRefuse")
	@ResponseBody
	public ResponseEntity dlrRefuse(@RequestParam("uuid") String uuid) {
		List<UserAuth> ualist = new ArrayList<UserAuth>();
		ualist = userService.getUserAuth(uuid, "", "0");
		if (ualist == null || ualist.size() != 1) {
			return new ResponseEntity("false", "用户数据异常！");
		}
		int count = 0;
		count = userService.upUserSh("2", "0", uuid);
		if (count == 1) {
			count = 0;
			count = userService.upUserAllStatus("", "", "", "", "2", uuid);
		}
		if (count == 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// /ststus/0=审核中 1=通过 2=未通过
	// /isAuth=1=已提交 2=审核通过3=不通过'
	@RequestMapping("/dlrAgree")
	@ResponseBody
	public ResponseEntity dlrAgree(@RequestParam("uuid") String uuid) {

		List<UserAuth> uaulist = new ArrayList<UserAuth>();
		uaulist = userService.getUserAuth(uuid, "", "0");
		if (uaulist == null || uaulist.size() != 1) {
			return new ResponseEntity("false", "用户数据异常！");
		}
		int count = 0;
		count = userService.upUserSh("1", "0", uuid);
		if (count == 1) {
			count = 0;
			count = userService.upUserAllStatus("", "", "", "", "2", uuid);
		}
		if (count == 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}
}
