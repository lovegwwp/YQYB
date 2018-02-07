package com.jyss.yqy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyss.yqy.entity.BaseConfig;
import com.jyss.yqy.entity.BaseShare;
import com.jyss.yqy.mapper.BaseConfigMapper;
import com.jyss.yqy.service.BaseConfigService;

@Service
public class BaseConfigServiceImpl implements BaseConfigService{
	@Autowired
	private BaseConfigMapper bcMapper;

	@Override
	public int insertConfig(BaseConfig bc) {
		// TODO Auto-generated method stub
    	bc.setStatus(1);
		//bc.setKey("sign.info");
		return bcMapper.insertConfig(bc);
	}

	@Override
	public int updateByPrimaryKey(BaseConfig bc) {
		// TODO Auto-generated method stub
		return bcMapper.updateByPrimaryKey(bc);
	}

	@Override
	public List<BaseConfig> getAllConfig(String title,String key) {
		// TODO Auto-generated method stub
		return bcMapper.getAllConfig(title,key);
	}

	@Override
	public int deleteConfig(List<Long> ids) {
		// TODO Auto-generated method stub
		return bcMapper.deleteConfig(ids);
	}

	@Override
	public int insertShare(BaseShare bs) {
		// TODO Auto-generated method stub
		bs.setStatus(1);
		return bcMapper.insertShare(bs);
	}

	@Override
	public int updateShare(BaseShare bs) {
		// TODO Auto-generated method stub
		return bcMapper.updateShare(bs);
	}

	@Override
	public List<BaseShare> getAllShare(String title, String content,
			String shareKey, String status) {
		// TODO Auto-generated method stub
		return bcMapper.getAllShare(title, content, shareKey,  status);
	}

	@Override
	public int deleteShare(List<Long> ids) {
		// TODO Auto-generated method stub
		return bcMapper.deleteShare(ids);
	}

}
