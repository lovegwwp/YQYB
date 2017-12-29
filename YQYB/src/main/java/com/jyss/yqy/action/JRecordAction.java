package com.jyss.yqy.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyss.yqy.entity.JRecord;
import com.jyss.yqy.entity.Page;
import com.jyss.yqy.entity.ResponseEntity;
import com.jyss.yqy.service.JRecordService;

@Controller
public class JRecordAction {
	@Autowired
	private JRecordService recordService;

	// 每日封顶
	@RequestMapping("/scjry")
	public String scjRyTz() {
		return "scjry";
	}

	/**
	 * 添加市场用户
	 */
	@RequestMapping(value = "/jrc/addJRecord", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addJRecord(@RequestParam("id") int id,
			@RequestParam("uAccount") String uAccount,
			@RequestParam("pAccount") String pAccount,
			@RequestParam("depart") Integer depart) {
		ResponseEntity entity = new ResponseEntity("false", "操作失败！");
		System.out.println("id======>" + id);
		// /新增
		if (id == 0) {
			if (StringUtils.isEmpty(depart)) {
				return new ResponseEntity("false", "请选择分配的市场！");
			}
			entity = recordService.insertJRecord(uAccount, pAccount, depart);
			// //修改
		} else {
			entity = updateJRecord2(id, uAccount);
		}
		return entity;

	}

	public ResponseEntity updateJRecord2(int id, String account) {
		ResponseEntity entity = recordService.updateJRecord(id, account);
		return entity;
	}

	/**
	 * 展示市场用户
	 */
	@RequestMapping("/jrc/user/list")
	@ResponseBody
	public Page getUserList(
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		PageHelper.startPage(page, rows);
		List<JRecord> list = recordService.getJRecordList();
		PageInfo<JRecord> pageInfo = new PageInfo<JRecord>(list);
		Page<JRecord> result = new Page<JRecord>();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	/**
	 * 根据账号展示市场用户
	 */
	@RequestMapping("/jrc/listByAccount")
	@ResponseBody
	public List<JRecord> getJRecordListByAccount(
			@RequestParam("account") String account) {
		List<JRecord> list = recordService.getJRecordListByAccount(account);
		return list;
	}

	/**
	 * 修改市场用户
	 */
	@RequestMapping(value = "/jrc/updateJrc", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity updateJRecord(@RequestParam("id") int id,
			@RequestParam("account") String account) {
		ResponseEntity entity = recordService.updateJRecord(id, account);
		return entity;
	}

	/**
	 * 删除市场用户
	 */
	@RequestMapping("/jrc/deleteJrc")
	@ResponseBody
	public ResponseEntity deleteJRecord(@RequestParam("strIds") int strIds) {
		ResponseEntity entity = recordService.deleteJRecord(strIds);
		return entity;
	}

}
