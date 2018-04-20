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
import com.jyss.yqy.entity.jsonEntity.UserBean;
import com.jyss.yqy.service.UserService;
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
	@Autowired
	private UserService userService;

	// private String yzm;

	@RequestMapping("/SuccTz")
	public String SuccTz() {
		return "index";
	}

	@RequestMapping("/accountlog")
	public String accountlogTz() {
		return "accountlog";
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
			auService.addLog(loginName,"当前用户-修改密码");
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
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		if (lName.equals("") || lName == null) {
			return new ResponseEntity("false", "登录用户信息异常！");
		}
		String description="";
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
			description = "权限管理-新增用户";
			////判断是总监助理
			if(au.getRoleId()==22){
				////u_user新增记录（ischuangke istransfer不可提现不可转账）
				//添加u_user用户   is_chuangke = 6,is_transfer = 2
				List<UserBean> ulist = userService.getUserBy(au.getUsername(), "1");
				if(ulist != null && ulist.size() > 0){
					return new ResponseEntity("false", "该手机号已经注册，不可成为总监助理");
				}
				User user = new User();
				user.setAccount(au.getUsername());
				user.setSalt(CommTool.getSalt());
				///生成含有随机盐的密码APP
				user.setPwd(PasswordUtil.generateAPP("666666", user.getSalt()));
				user.setIsChuangke(6);
				user.setIsTransfer(2);
				userService.addUser(user);

				au.setUserId(user.getId());
			}
			count = auService.addAccount(au);
		} else {
			// 修改
			////判断是总监助理
			if(au.getRoleId()==22){
				return new ResponseEntity("false", "总监助理身份不可修改，只能删除重新添加！");
			}
			description = "权限管理-修改用户";
			count = auService.upAccount(au);
		}

		if (count == 1) {
			auService.addLog(lName,description);
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	@RequestMapping("/delAccount")
	@ResponseBody
	public ResponseEntity delAccount(String strIds) {
		// TODO Auto-generated method stub
		int count = 0;
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		if (lName.equals("") || lName == null) {
			return new ResponseEntity("false", "登录用户信息异常！");
		}
		List<Long> ids = Utils.stringToLongList(strIds, ",");
		count = auService.deleteAccounts(ids);
		if (count >= 1) {
			auService.addLog(lName,"权限管理-删除用户");
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	@RequestMapping("/delRoles")
	@ResponseBody
	public ResponseEntity delRoles(String strIds) {
		// TODO Auto-generated method stub
		int count = 0;
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		if (lName.equals("") || lName == null) {
			return new ResponseEntity("false", "登录用户信息异常！");
		}
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
			auService.addLog(lName,"权限管理-删除角色");
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	@RequestMapping("/addRoles")
	@ResponseBody
	public ResponseEntity addRoles(AccountUser au,String strIds) {
		// TODO Auto-generated method stub
		//System.out.print("权限菜单"+strIds);
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		if (lName.equals("") || lName == null) {
			return new ResponseEntity("false", "登录用户信息异常！");
		}
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
		String description="权限管理-新增角色";
		//0===新增，否则修改
		if (au.getId()==0) {
			count = auService.addMyRoles(au,ids);
		}else{
			///修改，（修改角色（角色修改，权限先删除，后添加））
			description="权限管理-修改角色";
			count = auService.updateMyRoles(au,ids);
		}
		if (count >= 1) {
			auService.addLog(lName,description);
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
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		if (lName.equals("") || lName == null) {
			lName="异常用户";
		}
		List<AccountUser> list = new ArrayList<AccountUser>();
		list = auService.getAuByUsername(username);
		auService.addLog(lName,"权限管理-用户列表");
		return list;
	}

	@RequestMapping("/accountsSx")
	@ResponseBody
	public List<AccountUser> accountsSx() {
		List<AccountUser> list = new ArrayList<AccountUser>();
		list = auService.getAuByUsername("");
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		if (lName.equals("") || lName == null) {
			lName="异常用户";
		}
		auService.addLog(lName,"权限管理-用户列表");
		return list;
	}

	@RequestMapping("/getaccountsZl")
	@ResponseBody
	public List<AccountUser> getaccountsZl() {
		List<AccountUser> list = new ArrayList<AccountUser>();
		/////查询总监助理对应的人 roleID=22
		list = auService.getPermissionAndName(null,"22");
	    return list;
	}

	@RequestMapping("/roleList")
	@ResponseBody
	public List<AccountUser> roleList() {
		List<AccountUser> list = new ArrayList<AccountUser>();
		list = auService.getRoles(null);
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		if (lName.equals("") || lName == null) {
			lName="异常用户";
		}
		auService.addLog(lName,"权限管理-角色列表");
		return list;
	}
	@RequestMapping("/accountlogList")
	@ResponseBody
	public List<AccountLog> accountlogList() {
		List<AccountLog> list = new ArrayList<AccountLog>();
		list = auService.getAccountLog(null);
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		if (lName.equals("") || lName == null) {
			lName="异常用户";
		}
		auService.addLog(lName,"日志管理-日志查询");
		return list;
	}


	/**
	 *
	 * 权限菜单列表=
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

	/**
	 *
	 * 菜单树
	 * @return
	 */
	@RequestMapping("/getEditMenuTree")
	@ResponseBody
	public TreeBean getEditMenuTree(@RequestParam("roleId") String roleId) {
		///顶点节点
		TreeBean mennuTree = new TreeBean();
		Map<String,Boolean> state = new HashMap<String,Boolean>();
		state.put("opened",true);
		state.put("selected",false);
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
		/////用户已有菜单
		List <AccountUser> myMenuList = auService.getRolePermission(roleId);
		for(AccountUser au : treeList){
			////循环判断用户菜单数据
			boolean checkFlag =false;
			state = new HashMap<String,Boolean>();
			state.put("opened",true);
			for(AccountUser aus : myMenuList){
				if((au.getId()+"").equals(aus.getPermissionId())){
					checkFlag = true;
				}
			}
			state.put("selected",checkFlag);
			if (au.getCode().length()==2){
				count ++;
				tb = new TreeBean();
				state.put("selected",false);
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
				chlilBean.setChecked(checkFlag);
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
