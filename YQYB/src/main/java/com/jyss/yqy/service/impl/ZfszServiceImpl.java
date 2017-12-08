package com.jyss.yqy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyss.yqy.entity.Zfsz;
import com.jyss.yqy.mapper.ZfszMapper;
import com.jyss.yqy.service.ZfszService;
import com.jyss.yqy.utils.CommTool;

@Service
public class ZfszServiceImpl implements ZfszService {

	@Autowired
	private ZfszMapper zfMapper;

	@Override
	public List<Zfsz> getZfsz() {
		// TODO Auto-generated method stub
		return zfMapper.getZfsz();
	}

	@Override
	public List<Zfsz> getZfszBy(String type) {
		// TODO Auto-generated method stub
		return zfMapper.getZfszBy(type);
	}

	@Override
	public int addZfsz(Zfsz zf) {
		// TODO Auto-generated method stub
		zf.setStatus(1);
		zf.setCreatedAt(CommTool.getNowTimestamp());
		return zfMapper.addZfsz(zf);
	}

	@Override
	public int updateZfsz(Zfsz zf) {
		// TODO Auto-generated method stub
		zf.setLastModifyTime(CommTool.getNowTimestamp());
		return zfMapper.updateZfsz(zf);
	}

	@Override
	public int deleteZfsz(List<Long> ids) {
		// TODO Auto-generated method stub
		return zfMapper.deleteZfsz(ids);
	}

}
