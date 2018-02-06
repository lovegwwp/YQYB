package com.jyss.yqy.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyss.yqy.entity.BaseConfig;
import com.jyss.yqy.entity.Page;
import com.jyss.yqy.entity.ResponseEntity;
import com.jyss.yqy.service.BaseConfigService;
import com.jyss.yqy.utils.Utils;

@Controller
public class BaseConfigAction {
	@Autowired
	private BaseConfigService bcService;

	// //用户注册协议//////
	@RequestMapping("/baseconfig")
	public String baseconfigTz() {
		return "baseconfig";
	}

	@RequestMapping("/getBaseConfig")
	@ResponseBody
	public Page<BaseConfig> getBaseConfig(@RequestParam("page") int page,
			@RequestParam("rows") int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<BaseConfig> BaseConfigList = bcService.getAllConfig("",
				"sign.info");
		PageInfo<BaseConfig> pageInfo = new PageInfo<BaseConfig>(BaseConfigList);
		return new Page<BaseConfig>(pageInfo);
	}

	@RequestMapping("/getBaseConfigBy")
	@ResponseBody
	public Page<BaseConfig> getBaseConfigBy(@RequestParam("page") int page,
			@RequestParam("rows") int rows, @RequestParam("title") String title) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<BaseConfig> BaseConfigListBy = bcService.getAllConfig(title,
				"sign.info");
		PageInfo<BaseConfig> pageInfo = new PageInfo<BaseConfig>(
				BaseConfigListBy);
		return new Page<BaseConfig>(pageInfo);
	}

	@RequestMapping("/addBaseConfig")
	@ResponseBody
	public ResponseEntity addBaseConfig(BaseConfig baseConfig) {
		// TODO Auto-generated method stub
		int count = 0;
		if (baseConfig.getId() == 0) {
			// 新增
			baseConfig.setKey("sign.info");
			count = bcService.insertConfig(baseConfig);
		} else {
			// 修改
			count = bcService.updateByPrimaryKey(baseConfig);
		}

		if (count == 1) {
			return new ResponseEntity("OK", "操作成功！");
		}
		return new ResponseEntity("NO", "操作失败！");
	}

	@RequestMapping("/delBaseConfig")
	@ResponseBody
	public ResponseEntity delBaseConfig(String strIds) {
		// TODO Auto-generated method stub
		int count = 0;
		List<Long> ids = Utils.stringToLongList(strIds, ",");
		count = bcService.deleteConfig(ids);
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功!");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// //用户充值协议//////
	@RequestMapping("/czxy")
	public String czxyTz() {
		return "czxy";
	}

	@RequestMapping("/getCzxyInfo")
	@ResponseBody
	public Page<BaseConfig> getCzxyInfo(@RequestParam("page") int page,
			@RequestParam("rows") int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<BaseConfig> BaseConfigList = bcService.getAllConfig("",
				"czxy.info");
		PageInfo<BaseConfig> pageInfo = new PageInfo<BaseConfig>(BaseConfigList);
		return new Page<BaseConfig>(pageInfo);
	}

	@RequestMapping("/getCzxyInfoBy")
	@ResponseBody
	public Page<BaseConfig> getCzxyInfoBy(@RequestParam("page") int page,
			@RequestParam("rows") int rows, @RequestParam("title") String title) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<BaseConfig> BaseConfigListBy = bcService.getAllConfig(title,
				"czxy.info");
		PageInfo<BaseConfig> pageInfo = new PageInfo<BaseConfig>(
				BaseConfigListBy);
		return new Page<BaseConfig>(pageInfo);
	}

	@RequestMapping("/addCzxyInfo")
	@ResponseBody
	public ResponseEntity addCzxyInfo(BaseConfig baseConfig) {
		// TODO Auto-generated method stub
		int count = 0;
		if (baseConfig.getId() == 0) {
			// 新增
			baseConfig.setKey("czxy.info");
			count = bcService.insertConfig(baseConfig);
		} else {
			// 修改
			count = bcService.updateByPrimaryKey(baseConfig);
		}

		if (count == 1) {
			return new ResponseEntity("OK", "操作成功！");
		}
		return new ResponseEntity("NO", "操作失败！");
	}

	@RequestMapping("/delCzxyInfo")
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

	// //陪玩权限//////
	@RequestMapping("/pwqx")
	public String pwqxTz() {
		return "pwqx";
	}

	@RequestMapping("/getPwqxInfo")
	@ResponseBody
	public Page<BaseConfig> getPwqxInfo(@RequestParam("page") int page,
			@RequestParam("rows") int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<BaseConfig> BaseConfigList = bcService.getAllConfig("",
				"help.info");
		PageInfo<BaseConfig> pageInfo = new PageInfo<BaseConfig>(BaseConfigList);
		return new Page<BaseConfig>(pageInfo);
	}

	@RequestMapping("/getPwqxInfoBy")
	@ResponseBody
	public Page<BaseConfig> getPwqxInfoBy(@RequestParam("page") int page,
			@RequestParam("rows") int rows, @RequestParam("title") String title) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<BaseConfig> BaseConfigListBy = bcService.getAllConfig(title,
				"help.info");
		PageInfo<BaseConfig> pageInfo = new PageInfo<BaseConfig>(
				BaseConfigListBy);
		return new Page<BaseConfig>(pageInfo);
	}

	@RequestMapping("/addPwqxInfo")
	@ResponseBody
	public ResponseEntity addPwqxInfo(BaseConfig baseConfig) {
		// TODO Auto-generated method stub
		int count = 0;
		if (baseConfig.getId() == 0) {
			// 新增
			baseConfig.setKey("help.info");
			count = bcService.insertConfig(baseConfig);
		} else {
			// 修改
			count = bcService.updateByPrimaryKey(baseConfig);
		}

		if (count == 1) {
			return new ResponseEntity("OK", "操作成功！");
		}
		return new ResponseEntity("NO", "操作失败！");
	}

	@RequestMapping("/delPwqxInfo")
	@ResponseBody
	public ResponseEntity delPwqxInfo(String strIds) {
		// TODO Auto-generated method stub
		int count = 0;
		List<Long> ids = Utils.stringToLongList(strIds, ",");
		count = bcService.deleteConfig(ids);
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功!");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// //提现手续费说明/////
	@RequestMapping("/txsvf")
	public String txsvfTz() {
		return "txsvf";
	}

	@RequestMapping("/getTxsvfInfo")
	@ResponseBody
	public Page<BaseConfig> getTxsvfInfo(@RequestParam("page") int page,
			@RequestParam("rows") int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<BaseConfig> BaseConfigList = bcService.getAllConfig("",
				"take.info");
		PageInfo<BaseConfig> pageInfo = new PageInfo<BaseConfig>(BaseConfigList);
		return new Page<BaseConfig>(pageInfo);
	}

	@RequestMapping("/getTxsvfInfoBy")
	@ResponseBody
	public Page<BaseConfig> getTxsvfInfoBy(@RequestParam("page") int page,
			@RequestParam("rows") int rows, @RequestParam("title") String title) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<BaseConfig> BaseConfigListBy = bcService.getAllConfig(title,
				"take.info");
		PageInfo<BaseConfig> pageInfo = new PageInfo<BaseConfig>(
				BaseConfigListBy);
		return new Page<BaseConfig>(pageInfo);
	}

	@RequestMapping("/addTxsvfInfo")
	@ResponseBody
	public ResponseEntity addTxsvfInfo(BaseConfig baseConfig) {
		// TODO Auto-generated method stub
		int count = 0;
		if (baseConfig.getId() == 0) {
			// 新增
			baseConfig.setKey("take.info");
			count = bcService.insertConfig(baseConfig);
		} else {
			// 修改
			count = bcService.updateByPrimaryKey(baseConfig);
		}

		if (count == 1) {
			return new ResponseEntity("OK", "操作成功！");
		}
		return new ResponseEntity("NO", "操作失败！");
	}

	@RequestMapping("/delTxsvfInfo")
	@ResponseBody
	public ResponseEntity delTxsvfInfo(String strIds) {
		// TODO Auto-generated method stub
		int count = 0;
		List<Long> ids = Utils.stringToLongList(strIds, ",");
		count = bcService.deleteConfig(ids);
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功!");
		}
		return new ResponseEntity("false", "操作失败！");
	}

	// //系统消息/////
	@RequestMapping("/sysnews")
	public String sysnewsTz() {
		return "sysnews";
	}

	@RequestMapping("/getSysnewsInfo")
	@ResponseBody
	public Page<BaseConfig> getSysnewsInfo(@RequestParam("page") int page,
			@RequestParam("rows") int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<BaseConfig> BaseConfigList = bcService.getAllConfig("",
				"system.info");
		PageInfo<BaseConfig> pageInfo = new PageInfo<BaseConfig>(BaseConfigList);
		return new Page<BaseConfig>(pageInfo);
	}

	@RequestMapping("/getSysnewsInfoBy")
	@ResponseBody
	public Page<BaseConfig> getSysnewsInfoBy(@RequestParam("page") int page,
			@RequestParam("rows") int rows, @RequestParam("title") String title) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 分页语句
		List<BaseConfig> BaseConfigListBy = bcService.getAllConfig(title,
				"system.info");
		PageInfo<BaseConfig> pageInfo = new PageInfo<BaseConfig>(
				BaseConfigListBy);
		return new Page<BaseConfig>(pageInfo);
	}

	@RequestMapping("/addSysnewsInfo")
	@ResponseBody
	public ResponseEntity addSysnewsInfo(BaseConfig baseConfig) {
		// TODO Auto-generated method stub
		int count = 0;
		if (baseConfig.getId() == 0) {
			// 新增
			baseConfig.setKey("system.info");
			count = bcService.insertConfig(baseConfig);
		} else {
			// 修改
			count = bcService.updateByPrimaryKey(baseConfig);
		}

		if (count == 1) {
			return new ResponseEntity("OK", "操作成功！");
		}
		return new ResponseEntity("NO", "操作失败！");
	}

	@RequestMapping("/delSysnewsInfo")
	@ResponseBody
	public ResponseEntity delSysnewsInfo(String strIds) {
		// TODO Auto-generated method stub
		int count = 0;
		List<Long> ids = Utils.stringToLongList(strIds, ",");
		count = bcService.deleteConfig(ids);
		if (count >= 1) {
			return new ResponseEntity("true", "操作成功!");
		}
		return new ResponseEntity("false", "操作失败！");
	}
	
	    ////用户分享//////
		@RequestMapping("/yhfx")
		public String yhfxTz() {
			return "yhfx";
		}

		@RequestMapping("/getYhfxInfo")
		@ResponseBody
		public Page<BaseConfig> getYhfxInfo(@RequestParam("page") int page,
				@RequestParam("rows") int rows) {
			// TODO Auto-generated method stub
			PageHelper.startPage(page, rows);// 分页语句
			List<BaseConfig> BaseConfigList = bcService.getAllConfig("",
					"share.info");
			PageInfo<BaseConfig> pageInfo = new PageInfo<BaseConfig>(BaseConfigList);
			return new Page<BaseConfig>(pageInfo);
		}

		@RequestMapping("/getYhfxInfoBy")
		@ResponseBody
		public Page<BaseConfig> getYhfxInfoBy(@RequestParam("page") int page,
				@RequestParam("rows") int rows, @RequestParam("title") String title) {
			// TODO Auto-generated method stub
			PageHelper.startPage(page, rows);// 分页语句
			List<BaseConfig> BaseConfigListBy = bcService.getAllConfig(title,
					"share.info");
			PageInfo<BaseConfig> pageInfo = new PageInfo<BaseConfig>(
					BaseConfigListBy);
			return new Page<BaseConfig>(pageInfo);
		}

		@RequestMapping("/addYhfxInfo")
		@ResponseBody
		public ResponseEntity addYhfxInfo(BaseConfig baseConfig) {
			// TODO Auto-generated method stub
			int count = 0;
			if (baseConfig.getId() == 0) {
				// 新增
				baseConfig.setKey("share.info");
				count = bcService.insertConfig(baseConfig);
			} else {
				// 修改
				count = bcService.updateByPrimaryKey(baseConfig);
			}

			if (count == 1) {
				return new ResponseEntity("OK", "操作成功！");
			}
			return new ResponseEntity("NO", "操作失败！");
		}

		@RequestMapping("/delYhfxInfo")
		@ResponseBody
		public ResponseEntity delYhfxInfo(String strIds) {
			// TODO Auto-generated method stub
			int count = 0;
			List<Long> ids = Utils.stringToLongList(strIds, ",");
			count = bcService.deleteConfig(ids);
			if (count >= 1) {
				return new ResponseEntity("true", "操作成功!");
			}
			return new ResponseEntity("false", "操作失败！");
		}

		
	    ////客服电话//////
			@RequestMapping("/kfdh")
			public String kfdhTz() {
				return "kfdh";
			}

			@RequestMapping("/getKfdhInfo")
			@ResponseBody
			public Page<BaseConfig> getKfdhInfo(@RequestParam("page") int page,
					@RequestParam("rows") int rows) {
				// TODO Auto-generated method stub
				PageHelper.startPage(page, rows);// 分页语句
				List<BaseConfig> BaseConfigList = bcService.getAllConfig("",
						"tel.info");
				PageInfo<BaseConfig> pageInfo = new PageInfo<BaseConfig>(BaseConfigList);
				return new Page<BaseConfig>(pageInfo);
			}

			@RequestMapping("/getKfdhInfoBy")
			@ResponseBody
			public Page<BaseConfig> getKfdhInfoBy(@RequestParam("page") int page,
					@RequestParam("rows") int rows, @RequestParam("title") String title) {
				// TODO Auto-generated method stub
				PageHelper.startPage(page, rows);// 分页语句
				List<BaseConfig> BaseConfigListBy = bcService.getAllConfig(title,
						"tel.info");
				PageInfo<BaseConfig> pageInfo = new PageInfo<BaseConfig>(
						BaseConfigListBy);
				return new Page<BaseConfig>(pageInfo);
			}

			@RequestMapping("/addKfdhInfo")
			@ResponseBody
			public ResponseEntity addKfdhInfo(BaseConfig baseConfig) {
				// TODO Auto-generated method stub
				int count = 0;
				if (baseConfig.getId() == 0) {
					// 新增
					baseConfig.setKey("tel.info");
					count = bcService.insertConfig(baseConfig);
				} else {
					// 修改
					count = bcService.updateByPrimaryKey(baseConfig);
				}

				if (count == 1) {
					return new ResponseEntity("OK", "操作成功！");
				}
				return new ResponseEntity("NO", "操作失败！");
			}

			@RequestMapping("/delKfdhInfo")
			@ResponseBody
			public ResponseEntity delKfdhInfo(String strIds) {
				// TODO Auto-generated method stub
				int count = 0;
				List<Long> ids = Utils.stringToLongList(strIds, ",");
				count = bcService.deleteConfig(ids);
				if (count >= 1) {
					return new ResponseEntity("true", "操作成功!");
				}
				return new ResponseEntity("false", "操作失败！");
			}

}
