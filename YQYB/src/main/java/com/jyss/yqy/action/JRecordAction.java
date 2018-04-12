package com.jyss.yqy.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyss.yqy.entity.JRecord;
import com.jyss.yqy.entity.ResponseEntity;
import com.jyss.yqy.service.JRecordService;

@Controller
public class JRecordAction {
	@Autowired
	private JRecordService recordService;

	//
	@RequestMapping("/scjry")
	public String scjRyTz() {
		return "scjry";
	}


	/**
	 * 添加市场用户      zjUid = 总监id ，uAccount = 下级的推荐码 ，pAccount = 上级的推荐码
	 */
	@RequestMapping(value = "/jrc/addJRecord", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addJRecord(@RequestParam("id") int id,
									 @RequestParam("uAccount") String uAccount,
									 @RequestParam("pAccount") String pAccount,
									 @RequestParam("zjUid") Integer zjUid,
									 @RequestParam("depart") Integer depart) {
		ResponseEntity entity = new ResponseEntity("false", "操作失败！");
		System.out.println("id======>" + id);
		// /新增
		if (id == 0) {
			if (StringUtils.isEmpty(depart)) {
				return new ResponseEntity("false", "请选择分配的市场！");
			}
			if (StringUtils.isEmpty(zjUid)) {
				return new ResponseEntity("false", "请选择大区域市场！");
			}
			entity = recordService.insertJRecord(uAccount, pAccount, zjUid, depart);
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
	public List<JRecord> getUserList(@RequestParam("zjUid") Integer zjUid) {
		//PageHelper.startPage(page, rows);
		List<JRecord> list = recordService.getJRecordList(zjUid);
		//PageInfo<JRecord> pageInfo = new PageInfo<JRecord>(list);
		//Page<JRecord> result = new Page<JRecord>();
		//result.setTotal(pageInfo.getTotal());
		//result.setRows(list);
		return list;
	}


	/**
	 * 搜索展示市场用户     account = 用户的推荐码 ，uId = 用户id
	 */
	@RequestMapping("/jrc/listByAccount")
	@ResponseBody
	public List<JRecord> getJRecordListByAccount(@RequestParam("account") String account,@RequestParam("uId") String uId) {
		if(StringUtils.isEmpty(account) && StringUtils.isEmpty(uId)){
			List<JRecord> list = new ArrayList<JRecord>();
			return list;
		}
		List<JRecord> list = recordService.getJRecordListByAccount(account,uId);
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
	 * 删除市场用户      strIds = 数据库自增id
	 */
	@RequestMapping("/jrc/deleteJrc")
	@ResponseBody
	public ResponseEntity deleteJRecord(@RequestParam("strIds") int strIds) {
		ResponseEntity entity = recordService.deleteJRecord(strIds);
		return entity;
	}



	/**
	 * 查询市场总监       第4层
	 */
	@RequestMapping("/jrc/getRecord")
	@ResponseBody
	public List<JRecord> selectJRecordByFloor() {
		List<JRecord> list = recordService.selectJRecordByFloor(4);
		return list;
	}



}
