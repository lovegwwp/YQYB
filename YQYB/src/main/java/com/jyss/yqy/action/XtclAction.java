package com.jyss.yqy.action;

import java.util.List;

import com.jyss.yqy.service.AccountUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyss.yqy.entity.BaseArea;
import com.jyss.yqy.entity.Page;
import com.jyss.yqy.entity.ResponseEntity;
import com.jyss.yqy.entity.Xtcl;
import com.jyss.yqy.service.XtclService;
import com.jyss.yqy.utils.Utils;

@Controller
public class XtclAction {
	@Autowired
	private XtclService clService;
	@Autowired
	private AccountUserService auService;

	// 每日封顶
/*	@RequestMapping("/mrfd")
	public String mrfdTz() {
		return "mrfd";
	}

	// 奖金比例
	@RequestMapping("/jjbl")
	public String jjblTz() {
		return "jjbl";
	}

	// 返还周期
	@RequestMapping("/fhzq")
	public String fhzqTz() {
		return "fhzq";
	}

	// 市场奖
	@RequestMapping("/scj")
	public String scjTz() {
		return "scj";
	}

	// 分销奖
	@RequestMapping("/fxj")
	public String fxjTz() {
		return "fxj";
	}

	// 辅导奖
	@RequestMapping("/fdj")
	public String fdjTz() {
		return "fdj";
	}

	// 管理奖
	@RequestMapping("/glj")
	public String gljTz() {
		return "glj";
	}

	// pv设置
	@RequestMapping("/pvsz")
	public String pvszTz() {
		return "pvsz";
	}

	// 积分设置
	@RequestMapping("/jfsz")
	public String jfszTz() {
		return "jfsz";
	}

	// 代言费
	@RequestMapping("/dyf")
	public String dyfTz() {
		return "dyf";
	}

	// 代言积分
	@RequestMapping("/dyjf")
	public String dyjfTz() {
		return "dyjf";
	}
	
	// 代言积分
	@RequestMapping("/qqfhj")
	public String qqfhjTz() {
			return "qqfhj";
	}*/
    /***********通用方法******************/
	public ResponseEntity editCl(Xtcl cl,String loginName ,String title ) {
		// TODO Auto-generated method stub
		int count = 0;
		if (loginName.equals("") || loginName == null) {
			loginName ="异常用户";
		}
		count = clService.updateCl(cl);
		if (count == 1) {
			auService.addLog(loginName,title);
			return new ResponseEntity("OK", "操作成功！");
		}
		return new ResponseEntity("NO", "操作失败！");
	}

	public List<Xtcl> getClsCommon(
			String bz_type,String loginName,String title) {
		if (loginName.equals("") || loginName == null) {
			loginName ="异常用户";
		}
		List<Xtcl> clListBy = clService.getClsBy(bz_type, "");
		auService.addLog(loginName,title);
		return clListBy;
	}
	/********************************/


	// 合伙人缴费
	@RequestMapping("/cjhhr")
	public String cjhhrTz() {
		return "cjhhr";
	}

	@RequestMapping("/getCjhhr")
	@ResponseBody
	public List<Xtcl> getCjhhr() {
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		String title ="常量管理-合伙人金额查询";
		return getClsCommon("cjhhr_type",lName,title);
	}

	@RequestMapping("/addCjhhr")
	@ResponseBody
	public ResponseEntity addCjhhr(Xtcl cl) {
		// TODO Auto-generated method stub
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		String title ="常量管理-合伙人金额编辑";
		return editCl(cl,lName,title);
	}

	// 分红权倍数
	@RequestMapping("/fhqbs")
	public String fhqbsTz() {
		return "fhqbs";
	}

	@RequestMapping("/getfhqbs")
	@ResponseBody
	public List<Xtcl> getFhqbs() {
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		String title ="常量管理-分红权倍数查询";
		return getClsCommon("fhqbs_type",lName,title);
	}

	@RequestMapping("/addfhqbs")
	@ResponseBody
	public ResponseEntity addFhqbs(Xtcl cl) {
		// TODO Auto-generated method stub
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		String title ="常量管理-分红权倍数编辑";
		return editCl(cl,lName,title);
	}

	// 层奖比例
	@RequestMapping("/cjbl")
	public String cjblTz() {
		return "cjbl";
	}

	@RequestMapping("/getcjbl")
	@ResponseBody
	public List<Xtcl> getcjbl() {
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		String title ="常量管理-层奖比例查询";
		return getClsCommon("cjbl_type",lName,title);
	}

	@RequestMapping("/addcjbl")
	@ResponseBody
	public ResponseEntity addCjbl(Xtcl cl) {
		// TODO Auto-generated method stub
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		String title ="常量管理-层奖比例编辑";
		return editCl(cl,lName,title);
	}
	// 量奖封顶
	@RequestMapping("/ljfde")
	public String ljfdeTz() {
		return "ljfde";
	}

	@RequestMapping("/getljfde")
	@ResponseBody
	public List<Xtcl> getLjfde() {
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		String title ="常量管理-量奖封顶查询";
		return getClsCommon("ljfde_type",lName,title);
	}

	@RequestMapping("/addljfde")
	@ResponseBody
	public ResponseEntity addLjfde(Xtcl cl) {
		// TODO Auto-generated method stub
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		String title ="常量管理-量奖封顶编辑";
		return editCl(cl,lName,title);
	}

	// 共享奖封顶
	@RequestMapping("/gxjbl")
	public String gxjblTz() {
		return "gxjbl";
	}

	@RequestMapping("/getgxjbl")
	@ResponseBody
	public List<Xtcl> getGxjbl() {
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		String title ="常量管理-共享奖比例查询";
		return getClsCommon("gxjbl_type",lName,title);
	}

	@RequestMapping("/addgxjbl")
	@ResponseBody
	public ResponseEntity addGxjbl(Xtcl cl) {
		// TODO Auto-generated method stub
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		String title ="常量管理-共享奖比例编辑";
		return editCl(cl,lName,title);
	}

	// 动态奖金比例
	@RequestMapping("/dtjjbl")
	public String dtjjblTz() {
		return "dtjjbl";
	}

	@RequestMapping("/getdtjjbl")
	@ResponseBody
	public List<Xtcl> getDtjjbl() {
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		String title ="常量管理-动态奖金比例查询";
		return getClsCommon("dtjjbl_type",lName,title);
	}

	@RequestMapping("/adddtjjbl")
	@ResponseBody
	public ResponseEntity addDtjjbl(Xtcl cl) {
		// TODO Auto-generated method stub
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		String title ="常量管理-动态奖金比例编辑";
		return editCl(cl,lName,title);
	}

	// 股券相关设置
	@RequestMapping("/gqsz")
	public String gqszTz() {
		return "gqsz";
	}

	@RequestMapping("/getgqsz")
	@ResponseBody
	public List<Xtcl> getGqsz() {
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		String title ="常量管理-股券相关查询";
		return getClsCommon("gqsz_type",lName,title);
	}

	@RequestMapping("/addgqsz")
	@ResponseBody
	public ResponseEntity addGqsz(Xtcl cl) {
		// TODO Auto-generated method stub
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		String title ="常量管理-股券相关编辑";
		return editCl(cl,lName,title);
	}

	// 其他比例设置
	@RequestMapping("/otherbl")
	public String otherblTz() {
		return "otherbl";
	}

	@RequestMapping("/getotherbl")
	@ResponseBody
	public List<Xtcl> getotherbl() {
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		String title ="常量管理-其他比例查询";
		return getClsCommon("gqsz_type",lName,title);
	}

	@RequestMapping("/addotherbl")
	@ResponseBody
	public ResponseEntity addotherbl(Xtcl cl) {
		// TODO Auto-generated method stub
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		String title ="常量管理-其他比例编辑";
		return editCl(cl,lName,title);
	}


	//////=================之前版本，新版本没用到========================================//////////////////////////
	@RequestMapping("/getClsBy")
	@ResponseBody
	public List<Xtcl> getClsBy(
			@RequestParam(value = "bz_type", required = false) String bz_type
		) {
		List<Xtcl> clListBy = clService.getClsBy(bz_type, "");
		return clListBy;
	}

	@RequestMapping("/getClsCo")
	@ResponseBody
	public List<Xtcl> getClsCombox(
			@RequestParam(value = "bz_type", required = true) String bz_type,
			@RequestParam(value = "pid", required = false) String pid) {
		// TODO Auto-generated method stub
		List<Xtcl> clListCo = clService.getClsCombox(bz_type, pid);
		return clListCo;
	}

	@RequestMapping("/getClsCoCl")
	@ResponseBody
	public List<Xtcl> getClsComboxCl(
			@RequestParam(value = "bz_type", required = true) String bz_type,
			@RequestParam(value = "pid", required = false) String pid) {
		// TODO Auto-generated method stub
		List<Xtcl> clListCo = clService.getClsCl(bz_type, pid);
		return clListCo;
	}

	@RequestMapping("/getBaCo")
	@ResponseBody
	public List<BaseArea> getBaCo(
			@RequestParam(value = "area", required = true) String area,
			@RequestParam(value = "fid", required = false) String fid) {
		// TODO Auto-generated method stub
		List<BaseArea> clListCo = clService.getBaseAreas("", area, fid, "");
		return clListCo;
	}




	@RequestMapping("/addCl")
	@ResponseBody
	public ResponseEntity addDoc(Xtcl cl) {
		// TODO Auto-generated method stub
		int count = 0;
		// if (cl.getId() == 0) {
		// // 洗新增
		// count = clService.addCl(cl);
		// } else {
		// // 修改
		count = clService.updateCl(cl);
		// }
		if (count == 1) {
			return new ResponseEntity("OK", "操作成功！");
		}
		return new ResponseEntity("NO", "操作失败！");
	}

	@RequestMapping("/delCl")
	@ResponseBody
	public ResponseEntity deleteDoc(String strIds) {
		// TODO Auto-generated method stub
		int count = 0;
		List<Long> ids = Utils.stringToLongList(strIds, ",");
		count = clService.deleteCl(ids);
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功!");
		}
		return new ResponseEntity("false", "操作失败！");
	}

}
