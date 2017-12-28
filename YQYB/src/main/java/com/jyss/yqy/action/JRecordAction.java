package com.jyss.yqy.action;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyss.yqy.entity.Page;
import org.apache.ibatis.annotations.Param;
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

import javax.transaction.Transactional;

@Controller
@RequestMapping("/jrc")
public class JRecordAction {
	@Autowired
	private JRecordService recordService;
	
	/**
	 * 添加市场用户
	 */
	@RequestMapping(value="/addJRecord", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addJRecord(@RequestParam("uAccount") String uAccount,@RequestParam("pAccount") String pAccount,
			@RequestParam("depart")Integer depart){
		if(StringUtils.isEmpty(depart)){
			return new ResponseEntity("false", "请选择分配的市场！");
		}
		ResponseEntity entity = recordService.insertJRecord(uAccount,pAccount,depart);
		return entity;
		
	}
	
	
	/**
	 * 展示市场用户
	 */
	@RequestMapping("/user/list")
	@ResponseBody
	public Page getUserList(@RequestParam(value = "page", required = true) int page,
							@RequestParam(value = "limit", required = true) int limit){
		PageHelper.startPage(page, limit);
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
    @RequestMapping("/listByAccount")
	@ResponseBody
    public List<JRecord> getJRecordListByAccount(@RequestParam("account") String account){
        List<JRecord> list = recordService.getJRecordListByAccount(account);
        return list;
    }
	
	/**
	 * 修改市场用户
	 */
	@RequestMapping(value="/updateJrc",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity updateJRecord(@RequestParam("id")int id,@RequestParam("account")String account){
		ResponseEntity entity = recordService.updateJRecord(id, account);
		return entity;
	}
	
	
	/**
	 * 删除市场用户
	 */
	@RequestMapping("/deleteJrc")
	@ResponseBody
	public ResponseEntity deleteJRecord(@RequestParam("id")int id){
		ResponseEntity entity = recordService.deleteJRecord(id);
		return entity;
	}

	

}
