package com.jyss.yqy.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jyss.yqy.entity.*;
import com.jyss.yqy.utils.PasswordUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jyss.yqy.service.AccountUserService;
import com.jyss.yqy.shiro.ShiroToken;
import com.jyss.yqy.utils.CommTool;
import com.jyss.yqy.utils.Utils;

@Controller
public class AccountUserAction {
	@Autowired
	private AccountUserService auService;

	// private String yzm;

	@RequestMapping("/SuccTz")
	public String SuccTz() {
		return "index";
	}

	// /用户列表页面跳转
	@RequestMapping("/accountuser")
	public String accuontsTz() {
		return "accountuser";
	}

	// /角色列表页面跳转
	@RequestMapping("/accountrole")
	public String accountroleTz() {
		return "accountrole";
	}


	@RequestMapping("/rePwd")
	public String rePwd() {
		return "rePwd";
	}

	@RequestMapping("/shiro-tcxt")
	public String tcxt(ServletRequest servletRequest,
			ServletResponse servletResponse) throws IOException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			try {
				subject.logout();
			} catch (Exception ex) {
			}
		}
		return "login";
	}

	@RequestMapping("/shiro-getLogin")
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
		isOnly = auService.getAuNum(logName);
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
		AccountUser dbAu = auService.getAuBy(logName);
		String info = loginUser(dbAu);
		if (!"SUCC".equals(info)) {
			mav.addObject("error", info);
			mav.setViewName("error");
			return mav;
		} else {
			// 最终登陆成功
			HttpSession session = request.getSession();
			session.setAttribute("username", logName);
			// session.setMaxInactiveInterval(1 * 60 * 60);// 设置时间1小时
			mav.addObject("username", logName);
			mav.setViewName("index");
			getMenuList();
			return mav;
		}

	}

	private String loginUser(AccountUser user) {
		if (isRelogin(user))
			return "SUCC"; // 如果已经登陆，无需重新登录

		return shiroLogin(user); // 调用shiro的登陆验证
	}

	private String shiroLogin(AccountUser user) {
		// 组装token，包括客户公司名称、简称、客户编号、用户名称；密码
		// UsernamePasswordToken token = new UsernamePasswordToken(
		// user.getUsername(), user.getPassword());
		ShiroToken token = new ShiroToken(user.getUsername(),
				user.getPassword(), user.getSalt());
		token.setRememberMe(true); // 记住我 下次无需验证

		// shiro登陆验证
		try {
			SecurityUtils.getSubject().login(token);
		} catch (UnknownAccountException ex) {
			return "用户不存在！";
		} catch (IncorrectCredentialsException ex) {
			return "密码错误！";
		} catch (AuthenticationException ex) {
			return ex.getMessage(); // 自定义报错信息
		} catch (Exception ex) {
			ex.printStackTrace();
			return "内部错误，请重试！";
		}
		return "SUCC";
	}

	private boolean isRelogin(AccountUser user) {
		Subject us = SecurityUtils.getSubject();
		if (us.isAuthenticated()) {
			return true; // 参数未改变，无需重新登录，默认为已经登录成功
		}
		return false; // 需要重新登陆
	}

	@RequestMapping("/upHtPwd")
	@ResponseBody
	public ResponseEntity upHtPwd(@RequestParam("newPwd") String newPwd,@RequestParam("oldPwd") String oldPwd,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		int count = 0;
		Subject us = SecurityUtils.getSubject();
		String loginName = us.getPrincipal().toString();
		if (loginName.equals("") || loginName == null) {
			return new ResponseEntity("false", "操作失败！");
		}
		////判断原密码
		List<AccountUser> aulist = auService.getPermissionAndName(loginName,null);
		if (aulist==null||aulist.size()!=1){
			return new ResponseEntity("false", "用户信息异常！");
		}
		System.out.print(oldPwd+"==================>"+aulist.get(0).getSalt()+"==================>"+aulist.get(0).getPassword());
		if(oldPwd==null||oldPwd.equals("")||!(PasswordUtil.generate(oldPwd, aulist.get(0).getSalt()).equals(aulist.get(0).getPassword()))){
			return new ResponseEntity("false", "原密码错误！");
		}
		String salt = CommTool.getSalt();
		count = auService.upHtPwd(loginName, newPwd, salt);
		if (count == 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	@RequestMapping("/addAccount")
	@ResponseBody
	public ResponseEntity addAccount(AccountUser au) {
		// TODO Auto-generated method stub
		int count = 0;
		int isOnly = 0;
		List<AccountUser> alist = auService.getPermissionAndName(au.getUsername(),null);
		if (alist!=null&&alist.size()>=1) {
			if (au.getId()==0){
				return new ResponseEntity("false", "账号冲突！");
			}else if (au.getId()!=alist.get(0).getId()){
				return new ResponseEntity("false", "账号冲突！");
			}
		}
		if (au.getId() == 0) {
			// 新增
			count = auService.addAccount(au);
		} else {
			// 修改
			count = auService.upAccount(au);
		}

		if (count == 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	@RequestMapping("/delAccount")
	@ResponseBody
	public ResponseEntity delAccount(String strIds) {
		// TODO Auto-generated method stub
		int count = 0;
		List<Long> ids = Utils.stringToLongList(strIds, ",");
		count = auService.deleteAccounts(ids);
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	@RequestMapping("/delRoles")
	@ResponseBody
	public ResponseEntity delRoles(String strIds) {
		// TODO Auto-generated method stub
		int count = 0;
		List<Long> ids = Utils.stringToLongList(strIds, ",");
		List<AccountUser>  aalist = new ArrayList<AccountUser>();
		///删除前要判断此权限是否有关联用户，若关联，要先删除
		for(long id :ids){
		    aalist = auService.getPermissionAndName(null,id+"");
			if (aalist!=null&&aalist.size()>0){
				return new ResponseEntity("false", "该权限已分配账号，请删除账号在进行操作！");
			}
		}
		count = auService.delRoles(ids);
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	@RequestMapping("/addRoles")
	@ResponseBody
	public ResponseEntity addRoles(AccountUser au,String strIds) {
		// TODO Auto-generated method stub
		//System.out.print("权限菜单"+strIds);
		if (strIds==null||strIds.length()==0){
			return new ResponseEntity("false", "请勾选权限菜单！");
		}
		int count = 0;
		List<Long> ids = Utils.stringToLongList(strIds, ",");
		////判断权限账号是否唯一冲突
		List<AccountUser> rlist = auService.getRoles(au.getRoleSign());
		if (rlist!=null&&rlist.size()>=1) {
			if (au.getId()==0){
				return new ResponseEntity("false", "权限名称冲突！");
			}else if (au.getId()!=rlist.get(0).getId()){
				return new ResponseEntity("false", "权限名称冲突！");
			}
		}
		//0===新增，否则修改
		if (au.getId()==0) {
			count = auService.addMyRoles(au,ids);
		}else{
			///修改，（修改角色（角色修改，权限先删除，后添加））
			count = auService.updateMyRoles(au,ids);
		}
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	@RequestMapping("/upAccountZt")
	@ResponseBody
	public ResponseEntity updateDevZt(String strIds,
			@RequestParam("status") String status) {
		// TODO Auto-generated method stub
		int count = 0;
		List<Long> ids = Utils.stringToLongList(strIds, ",");
		count = auService.upAccountStatus(ids, status);
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// 用户列表
	@RequestMapping("/accountsCx")
	@ResponseBody
	public List<AccountUser> accountsCx(
			@RequestParam("username") String username) {
		List<AccountUser> list = new ArrayList<AccountUser>();
		list = auService.getAuByUsername(username);
		return list;
	}

	@RequestMapping("/accountsSx")
	@ResponseBody
	public List<AccountUser> accountsSx() {
		List<AccountUser> list = new ArrayList<AccountUser>();
		list = auService.getAuByUsername("");
		return list;
	}

	@RequestMapping("/roleList")
	@ResponseBody
	public List<AccountUser> roleList() {
		List<AccountUser> list = new ArrayList<AccountUser>();
		list = auService.getRoles(null);
		return list;
	}


	/**
	 *
	 * 权限菜单列表
	 * @return
     */
	@RequestMapping("/getMenuList")
	@ResponseBody
	public List <MennuBean> getMenuList() {
		List <MennuBean> menuList =  new ArrayList<MennuBean>();
		List <ChildBean> childList =  new ArrayList<ChildBean>();
		Map<String ,Object> m = new HashMap<String,Object>();
		m.put("fMenu","1");
		MennuBean mb = new MennuBean();
		int count =0;//是否只有一组菜单列表
		ChildBean chlilBean = new ChildBean();
		Subject us = SecurityUtils.getSubject();
		String loginName = us.getPrincipal().toString();
		if (loginName==null||loginName.equals("")){
			loginName = "dba";
		}
		/////循环权限列表，进行格式规划
		List <AccountUser> permissionList = auService.getPermissionLsitBy(loginName);
		for(AccountUser au : permissionList){
			if (au.getCode().length()==2){
				count ++;
				mb = new MennuBean();
				mb.setHref(au.getHref());
				mb.setIcon(au.getIcon());
				mb.setTitle(au.getTitle());
				mb.setSpread(false);
				if (!m.get("fMenu").equals("1")){
					MennuBean mbF =(MennuBean)m.get("fMenu");
					mbF.setChildren(childList);
					menuList.add(mbF);
					childList = new ArrayList<ChildBean>();
				}
				m.put("fMenu",mb);
			}else if (au.getCode().length()==4){
				chlilBean = new ChildBean();
				chlilBean.setHref(au.getHref());
				chlilBean.setIcon(au.getIcon());
				chlilBean.setTitle(au.getTitle());
				chlilBean.setSpread(false);
				childList.add(chlilBean);
			}
		}
		////不止一组列表，最后一组不会自动加上
		MennuBean mbF =(MennuBean)m.get("fMenu");
		mbF.setChildren(childList);
		menuList.add(mbF);
		return menuList;

	}


	/**
	 *
	 * 菜单树
	 * @return
	 */
	@RequestMapping("/getMenuTree")
	@ResponseBody
	public TreeBean getMenuTree() {
		///顶点节点
		TreeBean mennuTree = new TreeBean();
		Map<String,Boolean> state = new HashMap<String,Boolean>();
		state.put("opened",true);
		mennuTree.setState(state);
		mennuTree.setChecked(false);
		mennuTree.setId("-1");
		mennuTree.setText("顶级节点");
		mennuTree.setHasChildren(true);
		mennuTree.setHasParent(false);
		//////顶点节点下面的子节点/////
		List <TreeBean> menuList =  new ArrayList<TreeBean>();
		List <TreeBean> childList =  new ArrayList<TreeBean>();
		Map<String ,Object> m = new HashMap<String,Object>();
		m.put("fMenu","1");
		TreeBean tb = new TreeBean();
		int count =0;//是否只有一组菜单列表
		TreeBean chlilBean = new TreeBean();
		/////循环权限菜单，进行格式规划
		List <AccountUser> treeList = auService.getMennuTree(null);
		for(AccountUser au : treeList){
			state.put("selected",false);
			if (au.getCode().length()==2){
				count ++;
				tb = new TreeBean();
				tb.setState(state);
				tb.setChecked(false);
				tb.setId(au.getId()+"");
				tb.setText(au.getTitle());
				tb.setHasChildren(true);
				tb.setHasParent(true);
				tb.setParentId("0");
				if (!m.get("fMenu").equals("1")){
					TreeBean tbF =(TreeBean)m.get("fMenu");
					tbF.setChildren(childList);
					menuList.add(tbF);
					childList = new ArrayList<TreeBean>();
				}
				m.put("fMenu",tb);
			}else if (au.getCode().length()==4){
				TreeBean cbF =(TreeBean)m.get("fMenu");
				chlilBean = new TreeBean();
				chlilBean.setState(state);
				chlilBean.setChecked(false);
				chlilBean.setId(au.getId()+"");
				chlilBean.setText(au.getTitle());
				chlilBean.setHasChildren(false);
				chlilBean.setHasParent(true);
				chlilBean.setParentId(cbF.getId()+"");
				childList.add(chlilBean);
			}
		}
		////不止一组列表，最后一组不会自动加上
		TreeBean tbF =(TreeBean)m.get("fMenu");
		tbF.setChildren(childList);
		menuList.add(tbF);
		mennuTree.setChildren(menuList);
		return mennuTree;

	}







}
