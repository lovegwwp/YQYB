package com.jyss.yqy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyss.yqy.entity.BaseConfig;
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

}
