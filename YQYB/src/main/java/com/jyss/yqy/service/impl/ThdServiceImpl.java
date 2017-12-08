package com.jyss.yqy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyss.yqy.entity.ThOrders;
import com.jyss.yqy.entity.Thd;
import com.jyss.yqy.mapper.ThdMapper;
import com.jyss.yqy.mapper.XtclMapper;
import com.jyss.yqy.service.ThdService;
import com.jyss.yqy.utils.CommTool;
import com.jyss.yqy.utils.PasswordUtil;

@Service
public class ThdServiceImpl implements ThdService {
	@Autowired
	private ThdMapper thdMapper;
	@Autowired
	private XtclMapper xtclMapper;

	public String getValue(String bz_type, String id) {
		if (xtclMapper.getClsValue(bz_type, id) != null) {
			String nameVal = xtclMapper.getClsValue(bz_type, id).getBz_value();
			if (nameVal.isEmpty() || nameVal.equals("")) {
				return id;
			}
			return nameVal;
		} else {
			return id;
		}

	}

	@Override
	public List<Thd> getThdBy(String name) {
		// TODO Auto-generated method stub
		return thdMapper.getThdBy(name);
	}

	@Override
	public int getThdNum(String name) {
		// TODO Auto-generated method stub
		return thdMapper.getThdNum(name);
	}

	@Override
	public int upHtPwd(String name, String password, String salt) {
		// TODO Auto-generated method stub
		return thdMapper.upHtPwd(name, password, salt);
	}

	public Thd thdCl(Thd t) {
		t.setProvince(getValue("pro_type", String.valueOf(t.getProvinceId())));
		t.setCity(getValue("city_type", String.valueOf(t.getCityId())));
		t.setArea(getValue("area_type", String.valueOf(t.getAreaId())));
		t.setTelShow(t.getTel());
		return t;
	}

	@Override
	public int addThd(Thd thd) {
		// TODO Auto-generated method stub
		// thd.setPics("uploadImg/1.jpg");
		thd.setStatus("1");
		thd.setSalt(CommTool.getSalt());
		thd.setPassword(PasswordUtil.generate("666666", thd.getSalt()));
		return thdMapper.addThd(thdCl(thd));
	}

	@Override
	public int upThd(Thd thd) {
		// TODO Auto-generated method stub
		return thdMapper.upThd(thdCl(thd));
	}

	@Override
	public int deleteThds(List<Long> ids) {
		// TODO Auto-generated method stub
		return thdMapper.deleteThds(ids);
	}

	@Override
	public int upThdStatus(List<Long> ids, String status) {
		// TODO Auto-generated method stub
		return thdMapper.upThdStatus(ids, status);
	}

	@Override
	public List<ThOrders> getThSpBy(String name) {
		// TODO Auto-generated method stub
		return thdMapper.getThSpBy(name);
	}

}
