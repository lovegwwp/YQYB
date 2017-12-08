package com.jyss.yqy.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyss.yqy.entity.Page;
import com.jyss.yqy.entity.ResponseEntity;
import com.jyss.yqy.entity.Zfsz;
import com.jyss.yqy.service.ZfszService;
import com.jyss.yqy.utils.Utils;

@Controller
public class ZfszAction {
	@Autowired
	private ZfszService zfService;

	@RequestMapping("/zfsz")
	public String docTz() {
		return "zfsz";
	}

	@RequestMapping("/getZfsz")
	@ResponseBody
	public Page<Zfsz> getZfsz(int page, int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<Zfsz> zfList = zfService.getZfsz();
		PageInfo<Zfsz> pageInfo = new PageInfo<Zfsz>(zfList);
		return new Page<Zfsz>(pageInfo);
	}

	@RequestMapping("/getZfszBy")
	@ResponseBody
	public Page<Zfsz> getZfszBy(
			@RequestParam(value = "type1", required = false) String type1,
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<Zfsz> zfListBy = zfService.getZfszBy(type1);
		PageInfo<Zfsz> pageInfoBy = new PageInfo<Zfsz>(zfListBy);
		return new Page<Zfsz>(pageInfoBy);
	}

	@RequestMapping("/addZfsz")
	@ResponseBody
	public ResponseEntity addZfsz(Zfsz zf) {
		// TODO Auto-generated method stub
		int count = 0;
		if (zf.getId() == 0) {
			// 洗新增
			count = zfService.addZfsz(zf);
		} else {
			// 修改
			count = zfService.updateZfsz(zf);
		}

		if (count == 1) {
			return new ResponseEntity("OK", "操作成功！");
		}
		return new ResponseEntity("NO", "操作失败！");
	}

	@RequestMapping("/delZfsz")
	@ResponseBody
	public ResponseEntity deleteZfsz(String strIds) {
		// TODO Auto-generated method stub
		int count = 0;
		List<Long> ids = Utils.stringToLongList(strIds, ",");
		count = zfService.deleteZfsz(ids);
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功！");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	@RequestMapping("/pat/getZfszBy")
	@ResponseBody
	public Map<String, List<Zfsz>> getPatZf() {
		// TODO Auto-generated method stub
		Map<String, List<Zfsz>> m = new HashMap<String, List<Zfsz>>();
		List<Zfsz> patZfListSp = zfService.getZfszBy("2");
		m.put("SP", patZfListSp);
		List<Zfsz> patZfListTh = zfService.getZfszBy("3");
		m.put("TH", patZfListTh);
		return m;
	}
}
