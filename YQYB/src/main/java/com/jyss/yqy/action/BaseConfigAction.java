package com.jyss.yqy.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jyss.yqy.constant.Constant;
import com.jyss.yqy.service.AccountUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyss.yqy.utils.CommTool;
import com.jyss.yqy.entity.BaseConfig;
import com.jyss.yqy.entity.BaseShare;
import com.jyss.yqy.entity.Page;
import com.jyss.yqy.entity.ResponseEntity;
import com.jyss.yqy.service.BaseConfigService;
import com.jyss.yqy.utils.Utils;

@Controller
public class BaseConfigAction {
	@Autowired
	private BaseConfigService bcService;
	@Autowired
	private AccountUserService auService;


	//注册协议//
	@RequestMapping("/signagree")
	public String signagreeTz() {
		return "signagree";
	}

	//合伙人说明
	@RequestMapping("/hhrsm")
	public String hhrsmTz() {
		return "hhrsm";
	}

	//常见问题
	@RequestMapping("/issues")
	public String issuesTz() {
		return "issues";
	}

	//分享链接
	@RequestMapping("/sharelink")
	public String sharelinkTz() {
		return "sharelink";
	}



    //////获取注册协议////////
	@RequestMapping("/getSignagree")
	@ResponseBody
	public List<BaseConfig> getSignagree() {
		List<BaseConfig> BaseConfigList = bcService.getAllConfig("",
				"config.signup.b");
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		if (lName.equals("") || lName == null) {
			lName= "异常用户";
		}
		auService.addLog(lName,"文字管理-注册协议查询");
		return BaseConfigList;
	}

	//////获取合伙人说明////////
	@RequestMapping("/getHhrsm")
	@ResponseBody
	public List<BaseConfig> getHhrsm() {
		List<BaseConfig> BaseConfigList = bcService.getAllConfig("",
				"config.dljb.b");
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		if (lName.equals("") || lName == null) {
			lName= "异常用户";
		}
		auService.addLog(lName,"文字管理-合伙人说明查询");
		return BaseConfigList;
	}

	//////常见问题////////
	@RequestMapping("/getIssues")
	@ResponseBody
	public List<BaseConfig> getIssues() {

		List<BaseConfig> BaseConfigListBy = bcService.getAllConfig(null,
				"config.issue.b");
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		if (lName.equals("") || lName == null) {
			lName= "异常用户";
		}
		auService.addLog(lName,"文字管理-常见问题查询");
		return BaseConfigListBy;
	}

	//////用户分享////////
	@RequestMapping("/getSharelink")
	@ResponseBody
	public List<BaseShare> getSharelink() {

		List<BaseShare> BaseConfigList = bcService.getAllShare("", "",
				"chunagke.sign", "1");
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		if (lName.equals("") || lName == null) {
			lName= "异常用户";
		}
		auService.addLog(lName,"文字管理-用户分享查询");
		return BaseConfigList;
	}

 ///////////////////旧版本接口//////////////////////////////


	// //常见问题//////
	@RequestMapping("/cjwt")
	public String cjwtTz() {
		return "cjwt";
	}

	@RequestMapping("/getCjwtInfo")
	@ResponseBody
	public Page<BaseConfig> getCjwtInfo(@RequestParam("page") int page,
			@RequestParam("rows") int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<BaseConfig> BaseConfigList = bcService.getAllConfig("",
				"config.issue.b");
		PageInfo<BaseConfig> pageInfo = new PageInfo<BaseConfig>(BaseConfigList);
		return new Page<BaseConfig>(pageInfo);
	}

	@RequestMapping("/getCjwtInfoBy")
	@ResponseBody
	public Page<BaseConfig> getCjwtInfoBy(@RequestParam("page") int page,
			@RequestParam("rows") int rows, @RequestParam("title") String title) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<BaseConfig> BaseConfigListBy = bcService.getAllConfig(title,
				"config.issue.b");
		PageInfo<BaseConfig> pageInfo = new PageInfo<BaseConfig>(
				BaseConfigListBy);
		return new Page<BaseConfig>(pageInfo);
	}

	@RequestMapping("/addCjwtInfo")
	@ResponseBody
	public ResponseEntity addCzxyInfo(BaseConfig baseConfig) {
		// TODO Auto-generated method stub
		int count = 0;
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		if (lName.equals("") || lName == null) {
			lName= "异常用户";
		}
		String title="";
		if (baseConfig.getId() == 0) {
			// 新增
			title ="文字管理-新增常见问题";
			baseConfig.setKey("config.issue.b");
			count = bcService.insertConfig(baseConfig);
		} else {
			// 修改
			title ="文字管理-编辑常见问题";
			count = bcService.updateByPrimaryKey(baseConfig);
		}

		if (count == 1) {
			auService.addLog(lName,title);
			return new ResponseEntity("OK", "操作成功！");
		}
		return new ResponseEntity("NO", "操作失败！");
	}

	@RequestMapping("/delCjwtInfo")
	@ResponseBody
	public ResponseEntity delCzxyInfo(String strIds) {
		// TODO Auto-generated method stub
		int count = 0;
		List<Long> ids = Utils.stringToLongList(strIds, ",");
		count = bcService.deleteConfig(ids);
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功!");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// //注册协议//////
	@RequestMapping("/zcxy")
	public String zcxyTz() {
		return "zcxy";
	}

	@RequestMapping("/getZcxyInfo")
	@ResponseBody
	public Page<BaseConfig> getZcxyInfo(@RequestParam("page") int page,
			@RequestParam("rows") int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<BaseConfig> BaseConfigList = bcService.getAllConfig("",
				"config.signup.b");
		PageInfo<BaseConfig> pageInfo = new PageInfo<BaseConfig>(BaseConfigList);
		return new Page<BaseConfig>(pageInfo);
	}

	@RequestMapping("/getZcxyInfoBy")
	@ResponseBody
	public Page<BaseConfig> getZcxyInfoBy(@RequestParam("page") int page,
			@RequestParam("rows") int rows, @RequestParam("title") String title) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<BaseConfig> BaseConfigListBy = bcService.getAllConfig(title,
				"config.signup.b");
		PageInfo<BaseConfig> pageInfo = new PageInfo<BaseConfig>(
				BaseConfigListBy);
		return new Page<BaseConfig>(pageInfo);
	}

	@RequestMapping("/addZcxyInfo")
	@ResponseBody
	public ResponseEntity addZcxyInfo(BaseConfig baseConfig) {
		// TODO Auto-generated method stub
		int count = 0;
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		if (lName.equals("") || lName == null) {
			lName= "异常用户";
		}
		String title="";
		if (baseConfig.getId() == 0) {
			// 新增
			title ="文字管理-新增注册协议";
			baseConfig.setKey("config.signup.b");
			count = bcService.insertConfig(baseConfig);
		} else {
			// 修改
			title ="文字管理-编辑注册协议";
			count = bcService.updateByPrimaryKey(baseConfig);
		}

		if (count == 1) {
			auService.addLog(lName,title);
			return new ResponseEntity("OK", "操作成功！");
		}
		return new ResponseEntity("NO", "操作失败！");
	}

	@RequestMapping("/delZcxyInfo")
	@ResponseBody
	public ResponseEntity delZcxyInfo(String strIds) {
		// TODO Auto-generated method stub
		int count = 0;
		List<Long> ids = Utils.stringToLongList(strIds, ",");
		count = bcService.deleteConfig(ids);
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功!");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// //代理人说明/////
	@RequestMapping("/dlsm")
	public String dlsmTz() {
		return "dlsm";
	}

	@RequestMapping("/getDlsmInfo")
	@ResponseBody
	public Page<BaseConfig> getDlsmInfo(@RequestParam("page") int page,
			@RequestParam("rows") int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<BaseConfig> BaseConfigList = bcService.getAllConfig("",
				"config.dljb.b");
		PageInfo<BaseConfig> pageInfo = new PageInfo<BaseConfig>(BaseConfigList);
		return new Page<BaseConfig>(pageInfo);
	}

	@RequestMapping("/getDlsmInfoBy")
	@ResponseBody
	public Page<BaseConfig> getDlsmInfoBy(@RequestParam("page") int page,
			@RequestParam("rows") int rows, @RequestParam("title") String title) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<BaseConfig> BaseConfigListBy = bcService.getAllConfig(title,
				"config.dljb.b");
		PageInfo<BaseConfig> pageInfo = new PageInfo<BaseConfig>(
				BaseConfigListBy);
		return new Page<BaseConfig>(pageInfo);
	}

	@RequestMapping("/addDlsmInfo")
	@ResponseBody
	public ResponseEntity addDlsmInfo(BaseConfig baseConfig) {
		// TODO Auto-generated method stub
		int count = 0;
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		if (lName.equals("") || lName == null) {
			lName= "异常用户";
		}
		String title="";
		if (baseConfig.getId() == 0) {
			// 新增
			title ="文字管理-新增合伙人说明";
			baseConfig.setKey("config.dljb.b");
			count = bcService.insertConfig(baseConfig);
		} else {
			// 修改
			title ="文字管理-编辑合伙人说明";
			count = bcService.updateByPrimaryKey(baseConfig);
		}

		if (count == 1) {
			auService.addLog(lName,title);
			return new ResponseEntity("OK", "操作成功！");
		}
		return new ResponseEntity("NO", "操作失败！");
	}

	@RequestMapping("/delDlsmInfo")
	@ResponseBody
	public ResponseEntity delDlsmInfo(String strIds) {
		// TODO Auto-generated method stub
		int count = 0;
		List<Long> ids = Utils.stringToLongList(strIds, ",");
		count = bcService.deleteConfig(ids);
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功!");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// //用户分享//////
	@RequestMapping("/yhfx")
	public String yhfxTz() {
		return "yhfx";
	}

	@RequestMapping("/getYhfxInfo")
	@ResponseBody
	public Page<BaseShare> getYhfxInfo(@RequestParam("page") int page,
			@RequestParam("rows") int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<BaseShare> BaseConfigList = bcService.getAllShare("", "",
				"chunagke.sign", "1");
		PageInfo<BaseShare> pageInfo = new PageInfo<BaseShare>(BaseConfigList);
		return new Page<BaseShare>(pageInfo);
	}

	@RequestMapping("/getYhfxInfoBy")
	@ResponseBody
	public Page<BaseShare> getYhfxInfoBy(@RequestParam("page") int page,
			@RequestParam("rows") int rows,
			@RequestParam("title") String title,
			@RequestParam("content") String content) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<BaseShare> BaseConfigListBy = bcService.getAllShare(title,
				content, "chunagke.sign", "1");
		PageInfo<BaseShare> pageInfo = new PageInfo<BaseShare>(BaseConfigListBy);
		return new Page<BaseShare>(pageInfo);
	}

	@RequestMapping("/addYhfxInfo")
	@ResponseBody
	public ResponseEntity addYhfxInfo(BaseShare bs,
			@RequestParam("pics2") MultipartFile pics2,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		int count = 0;
		String filePath = request.getSession().getServletContext()
				.getRealPath("/");
		//System.out.println(filePath);
		int index = filePath.indexOf("YQYB");
		filePath = filePath.substring(0, index) + "uploadShareImg/"
				+ CommTool.getFileNameOnlyNum(pics2.getOriginalFilename());
		if (pics2.getSize() > 5400000L) {
			return new ResponseEntity("NO", "文件过大，应不超过5M!");
		}
		if (!Utils.saveUpload(pics2, filePath)) {
			return new ResponseEntity("NO", "文件上传失败！");
		}

		filePath = filePath.substring(filePath.indexOf("uploadShareImg"));
		bs.setLinkPic(filePath);
		if (bs.getId() == 0) {
			// 新增
			bs.setShareKey("chunagke.sign");
			count = bcService.insertShare(bs);
		} else {
			// 修改
			count = bcService.updateShare(bs);
		}

		if (count == 1) {
			return new ResponseEntity("OK", "操作成功！");
		}
		return new ResponseEntity("NO", "操作失败！");
	}

	@RequestMapping("/addShareLink")
	@ResponseBody
	public ResponseEntity addShareLink(BaseShare bs) {
		// TODO Auto-generated method stub
		int count = 0;
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		if (lName.equals("") || lName == null) {
			lName= "异常用户";
		}
		String title="";
		if (bs.getId() == 0) {
			// 新增
			title ="文字管理-新增分享链接";
			bs.setShareKey("chunagke.sign");
			count = bcService.insertShare(bs);
		} else {
			// 修改
			title ="文字管理-编辑分享链接";
			auService.addLog(lName,title);
			count = bcService.updateShare(bs);
		}

		if (count == 1) {
			return new ResponseEntity("OK", "操作成功！");
		}
		return new ResponseEntity("NO", "操作失败！");
	}

	@RequestMapping("/upLoadImg")
	@ResponseBody
	public ResponseEntity upLoadImg(@RequestParam("imgFile") MultipartFile imgFile, HttpServletRequest request) {
		// TODO Auto-generated method stub
		int count = 0;
		String filePath = request.getSession().getServletContext()
				.getRealPath("/");
		System.out.print("===================>"+filePath);
		int index = filePath.lastIndexOf("YQYB");
		filePath = filePath.substring(0, index) + "uploadShareImg/"
				+ CommTool.getFileNameOnlyNum(imgFile.getOriginalFilename());
		if (imgFile.getSize() > 5400000L) {
			return new ResponseEntity("false", "文件过大，应不超过5M!");
		}
		if (!Utils.saveUpload(imgFile, filePath)) {
			return new ResponseEntity("false", "文件上传失败！");
		}
		filePath = filePath.substring(filePath.indexOf("uploadShareImg"));
        System.out.print("===================>"+filePath);
		return new ResponseEntity("true",Constant.httpUrl+filePath);
	}

	@RequestMapping("/delYhfxInfo")
	@ResponseBody
	public ResponseEntity delYhfxInfo(String strIds) {
		// TODO Auto-generated method stub
		int count = 0;
		List<Long> ids = Utils.stringToLongList(strIds, ",");
		count = bcService.deleteShare(ids);
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功!");
		}
		return new ResponseEntity("false", "操作失败！");
	}

}
