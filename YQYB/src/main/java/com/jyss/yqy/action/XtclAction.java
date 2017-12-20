package com.jyss.yqy.action;

import java.util.List;

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

	// 每日封顶
	@RequestMapping("/mrfd")
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

	@RequestMapping("/getClsBy")
	@ResponseBody
	public Page<Xtcl> getClsBy(
			@RequestParam(value = "bz_type", required = false) String bz_type,
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<Xtcl> clListBy = clService.getClsBy(bz_type, "");
		PageInfo<Xtcl> pageInfoBy = new PageInfo<Xtcl>(clListBy);
		return new Page<Xtcl>(pageInfoBy);
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
