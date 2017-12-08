package com.jyss.yqy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyss.yqy.entity.Cwzf;
import com.jyss.yqy.mapper.CwzfMapper;
import com.jyss.yqy.service.CwzfService;
import com.jyss.yqy.utils.CommTool;

@Service
public class CwzfServiceImpl implements CwzfService {
	@Autowired
	private CwzfMapper cwMapper;

	@Override
	public List<Cwzf> getCw() {
		// TODO Auto-generated method stub
		return cwMapper.getCw();
	}

	@Override
	public List<Cwzf> getCwBy(String account, String kssj, String jssj,
			String czType) {
		// TODO Auto-generated method stub
		return cwMapper.getCwBy(account, kssj, jssj, czType);
	}

	@Override
	public int addCw(Cwzf cw) {
		// TODO Auto-generated method stub
		cw.setStatus(1);
		cw.setCreatedAt(CommTool.getNowTimestamp());
		return cwMapper.addCw(cw);
	}

	@Override
	public int deleteCw(List<Long> ids) {
		// TODO Auto-generated method stub
		return cwMapper.deleteCw(ids);
	}

	@Override
	public int upCw(Cwzf cw) {
		// TODO Auto-generated method stub
		cw.setLastModifyTime(CommTool.getNowTimestamp());
		return cwMapper.upCw(cw);
	}

	@Override
	public List<Cwzf> getCwByNo(String NO) {
		// TODO Auto-generated method stub
		return cwMapper.getCwByNo(NO);
	}

}
