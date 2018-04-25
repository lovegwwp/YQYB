package com.jyss.yqy.action;

import java.util.ArrayList;
import java.util.List;

import com.jyss.yqy.constant.Constant;
import com.jyss.yqy.entity.Thd;
import com.jyss.yqy.service.AccountUserService;
import com.jyss.yqy.utils.FirstLetterUtil;
import com.jyss.yqy.utils.Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
	@Autowired
	private AccountUserService auService;

	@RequestMapping("/dlrType")
	public String DlrTypeTz() {
		return "dlrType";
	}

	@RequestMapping("/dlrAuth")
	public String DlrAuthTz() {
		return "dlrAuth";
	}

	////合伙人统计
	@RequestMapping("/hhrtj")
	public String hhrtjTz() {
		return "hhrtj";
	}

	////合伙人审核
	@RequestMapping("/hhrsh")
	public String hhrshTz() {
		return "hhrsh";
	}

	////合伙人详情
	@RequestMapping("/hhrinfo")
	public String hhrinfoTz() {
		return "hhrinfo";
	}

	// 代理人统计列表is_chuangke==1=成为代言人 2=一级代理人 3=二级代理人 4=三级代理人 5=经理人（虚拟）6=市场总监助理  -->
	@RequestMapping("/hhrtjCx")
	@ResponseBody
	public List<UserBean> hhrtjCx() {
		List<UserBean> list = new ArrayList<UserBean>();
		List<UserBean> list1 = userService.getUserCount("2", "1", "2");
		List<UserBean> list2 = userService.getUserCount("3", "1", "2");
		List<UserBean> list3 = userService.getUserCount("4", "1", "2");
		list.addAll(0,list1);
		list.addAll(1,list2);
		list.addAll(2,list3);
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		if (lName.equals("") || lName == null) {
			lName= "异常用户";
		}
		auService.addLog(lName,"合伙人管理-合伙人统计查询");
		return list;
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
	// 代理人用户审核列表
	@RequestMapping("/hhrshCx")
	@ResponseBody
	public List<UserAuth> hhrshCx() {
		List<UserAuth> list = new ArrayList<UserAuth>();
		list = userService.getUserAuth("", "", "");
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		if (lName.equals("") || lName == null) {
			lName= "异常用户";
		}
		auService.addLog(lName,"合伙人管理-合伙人审核查询");
		for(UserAuth ua:list){
			ua.setCardPicture1(Constant.httpUrl+ua.getCardPicture1());
			ua.setCardPicture2(Constant.httpUrl+ua.getCardPicture2());
			ua.setCardPicture3(Constant.httpUrl+ua.getCardPicture3());
		}
		return list;
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
			count = userService.upUserAllStatus("", "", "", "", "3", uuid);
		}
		if (count == 1) {
			Subject us = SecurityUtils.getSubject();
			String lName = us.getPrincipal().toString();
			if (lName.equals("") || lName == null) {
				lName= "异常用户";
			}
			auService.addLog(lName,"合伙人管理-合伙人审核不通过");
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// /status/0=审核中 1=通过 2=未通过 u_user_auth
	// /isAuth=1=已提交 2=审核通过3=不通过'u_user
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
		String realName ="";
		if(uaulist.get(0)!=null&&uaulist.get(0).getRealName()!=null){
			realName = uaulist.get(0).getRealName();
		}
		String bcode = FirstLetterUtil.getFirstLetter(realName);
		//判断一次重复
		List<UserBean> ull = userService.getUserIsOnlyBy(null,bcode);
		if(ull!=null&&ull.size()>0){
			bcode = FirstLetterUtil.getFirstLetter(realName);
		}
		if (count == 1) {
			count = 0;
			count = userService.upUserAllStatus("", bcode, "", "", "2", uuid);
			if (count == 1) {
				//计算管理奖
				/*ResponseEntity entity = userRecordBService.insertJBonusGlj(uuid);
				return entity;*/
				Subject us = SecurityUtils.getSubject();
				String lName = us.getPrincipal().toString();
				if (lName.equals("") || lName == null) {
					lName= "异常用户";
				}
				auService.addLog(lName,"合伙人管理-合伙人审核通过");
				return new ResponseEntity("true", "操作成功！");
			}
		}
		return new ResponseEntity("false", "操作失败！");
	}


	// ////1可转，2不可转（账户转账）
	@RequestMapping("/transferNo")
	@ResponseBody
	public ResponseEntity transferNo(@RequestParam("uuid") String uuid) {
		int count = 0;
		List<String> uuids= Utils.stringToStringList(uuid, ",");
		count = userService.upIsTransfer(uuids,"2");
		if (count >= 1) {
			Subject us = SecurityUtils.getSubject();
			String lName = us.getPrincipal().toString();
			if (lName.equals("") || lName == null) {
				lName= "异常用户";
			}
			auService.addLog(lName,"合伙人管理-合伙人设置可以转账");
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// ////1可转，2不可转（账户转账）
	@RequestMapping("/transferOk")
	@ResponseBody
	public ResponseEntity transferOk(@RequestParam("uuid") String uuid) {
		int count = 0;
		List<String> uuids= Utils.stringToStringList(uuid, ",");
		count = userService.upIsTransfer(uuids,"1");
		if (count >= 1) {
			Subject us = SecurityUtils.getSubject();
			String lName = us.getPrincipal().toString();
			if (lName.equals("") || lName == null) {
				lName= "异常用户";
			}
			auService.addLog(lName,"合伙人管理-合伙人设置不可转账");
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}


	/**
	 * 查询所有合伙人信息
	 */
	@RequestMapping("/getUsers")
	@ResponseBody
	public List<UserBean> getUsers(@RequestParam("account") String account){
		List<UserBean> users = userService.getUsers(account);

		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		auService.addLog(lName,"合伙人管理-合伙人查询");
		return users;
	}


	/**
	 * 禁用用户
	 */
	@RequestMapping("/jyUsersByUuid")
	@ResponseBody
	public ResponseEntity updateUserStatus(@RequestParam("status") String status,@RequestParam("uuid") String uuid){
		int count = userService.updateUserStatus(status, uuid);
		if(count == 1){
			Subject us = SecurityUtils.getSubject();
			String lName = us.getPrincipal().toString();
			auService.addLog(lName,"合伙人管理-合伙人修改");
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}



}
