package com.jyss.yqy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyss.yqy.entity.ThOrders;
import com.jyss.yqy.entity.Thd;
import com.jyss.yqy.mapper.ThdMapper;
import com.jyss.yqy.mapper.XtclMapper;
import com.jyss.yqy.service.ThdService;
import com.jyss.yqy.utils.BaiduLngLat;
import com.jyss.yqy.utils.CommTool;
import com.jyss.yqy.utils.PasswordUtil;

@Service
public class ThdServiceImpl implements ThdService {
	@Autowired
	private ThdMapper thdMapper;
	@Autowired
	private XtclMapper xtclMapper;

	public String getValue(String area, String id) {
		if (xtclMapper.getBaseAreas("", area, "", id) != null
				&& xtclMapper.getBaseAreas("", area, "", id).size() != 0) {
			String nameVal = xtclMapper.getBaseAreas("", area, "", id).get(0)
					.getName();
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
		t.setProvince(getValue("2", String.valueOf(t.getProvinceId())));
		t.setCity(getValue("3", String.valueOf(t.getCityId())));
		t.setArea(getValue("4", String.valueOf(t.getAreaId())));
		// t.setTelShow(t.getTel());
		String addr = "";
		addr = t.getProvince() + t.getCity() + t.getArea() + t.getAddr();
		Map<String, Double> m = new HashMap<String, Double>();
		m = BaiduLngLat.getLngAndLat(addr);
		float lng = (float) (Math.round((m.get("lng").floatValue()) * 100)) / 100;// //取2位小数
		float lat = (float) (Math.round((m.get("lat").floatValue()) * 100)) / 100;
		t.setLat(lat);
		t.setLng(lng);
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
