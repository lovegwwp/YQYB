package com.jyss.yqy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jyss.yqy.entity.JRecord;
import com.jyss.yqy.entity.ResponseEntity;
import com.jyss.yqy.entity.jsonEntity.UserBean;
import com.jyss.yqy.mapper.JRecordMapper;
import com.jyss.yqy.mapper.UserMapper;
import com.jyss.yqy.service.JRecordService;

@Service
@Transactional
public class JRecordServiceImpl implements JRecordService {

	@Autowired
	private JRecordMapper recordMapper;
	@Autowired
	private UserMapper userMapper;

	/**
	 * 添加市场用户
	 */
	@Override
	public ResponseEntity insertJRecord(String uAccount,String pAccount,Integer zjUid,Integer depart) {
		if(StringUtils.isEmpty(uAccount)){
			return new ResponseEntity("false", "账号不能为空！");
		}
		List<JRecord> recordList = recordMapper.selectJRecordByAccount(uAccount,null);
		if(recordList != null && recordList.size()>0){
			return new ResponseEntity("false", "账号已被分配，不可重复分配！");
		}
		JRecord jRecord = new JRecord();
		if(depart == 0){
			List<JRecord> list = recordMapper.selectJRecordByPid(0);
			if(list != null && list.size()>0){
				return new ResponseEntity("false", "最上级已存在，不可再添加！");
			}
			List<UserBean> uList = userMapper.getUserIdByAccount(uAccount);
			if(uList != null && uList.size()>0){
				UserBean uUserBean = uList.get(0);
				jRecord.setuId(uUserBean.getId());
				jRecord.setuAccount(uAccount);
				jRecord.setParentId(0);
				jRecord.setDepart(depart);
				jRecord.setFloor(1);
				jRecord.setStatus(1);
				int count = recordMapper.insertJRecord(jRecord);
				if(count == 1){
					return new ResponseEntity("true", "添加成功！");
				}
				return new ResponseEntity("false", "添加失败！");
			}
			return new ResponseEntity("false", "用户不可用！");
		}else if(depart == 1 || depart == 2){
			List<UserBean> uList = userMapper.getUserIdByAccount(uAccount);
			if(uList != null && uList.size()>0){
				UserBean uUserBean = uList.get(0);
				List<UserBean> pList = userMapper.getUserIdByAccount(pAccount);
				if(pList != null && pList.size()>0){
					UserBean pUserBean = pList.get(0);
					
					List<JRecord> recordPList = recordMapper.selectJRecordByUid(pUserBean.getId());
					if(recordPList != null && recordPList.size()==1){
						JRecord recordP = recordPList.get(0);
						Integer floor = recordP.getFloor();
						floor++;
						//确认市场分配
						List<JRecord> pJRecordList = recordMapper.selectJRecordByPid(pUserBean.getId());
						if(pJRecordList != null && pJRecordList.size() == 1){
							JRecord record1 = pJRecordList.get(0);
							Integer depart1 = record1.getDepart();
							if((depart == 1 && depart1 == 2) || (depart == 2 && depart1 == 1)){
								jRecord.setuId(uUserBean.getId());
								jRecord.setuAccount(uAccount);
								jRecord.setParentId(pUserBean.getId());
								jRecord.setDepart(depart);
								jRecord.setZjUid(zjUid);
								jRecord.setFloor(floor);
								jRecord.setStatus(1);
								int count = recordMapper.insertJRecord(jRecord);
								if(count == 1){
									return new ResponseEntity("true", ""+uAccount+"分配市场成功！");
								}
								return new ResponseEntity("false", "添加失败！");
							}
							return new ResponseEntity("false", "该市场已存在用户！");
							
						}else if(pJRecordList == null || pJRecordList.size() == 0){
							jRecord.setuId(uUserBean.getId());
							jRecord.setuAccount(uAccount);
							jRecord.setParentId(pUserBean.getId());
							jRecord.setDepart(depart);
							jRecord.setZjUid(zjUid);
							jRecord.setFloor(floor);
							jRecord.setStatus(1);
							int count = recordMapper.insertJRecord(jRecord);
							if(count == 1){
								return new ResponseEntity("true", ""+uAccount+"分配市场成功！");
							}
							return new ResponseEntity("false", "添加失败！");
						}
						return new ResponseEntity("false", "上级用户已有两个市场，请选择其他上级！");
					}
					return new ResponseEntity("false", "上级用户还没被分配市场！");
				}
				return new ResponseEntity("false", "上级账号不可用！");
			}
			return new ResponseEntity("false", "账号不可用！");
		}
		return new ResponseEntity("false", "请确认分配市场！");
	}
	
	/**
	 * 展示市场用户
	 */
	@Override
	public List<JRecord> getJRecordList(Integer zjUid){
		return recordMapper.selectAllJRecord(zjUid);
	}


	/**
	 * 搜索展示市场用户
	 */
	@Override
	public List<JRecord> getJRecordListByAccount(String account,String uId){
        List<JRecord> list = recordMapper.selectJRecordByAccount(account,uId);
        List<JRecord> result = new ArrayList<JRecord>();
        if(list != null && list.size() == 1){
            JRecord jRecord = list.get(0);
            result.add(jRecord);
            getJRecordListByPid(jRecord.getuId(),result);
            return result;
        }
        return result;
    }
    //递归查询
    private void getJRecordListByPid(Integer pId,List<JRecord> result){
        List<JRecord> list = recordMapper.selectJRecordByPid(pId);
        if(list != null && list.size()>0){
            for (JRecord jRecord : list) {
                result.add(jRecord);
                getJRecordListByPid(jRecord.getuId(),result);
            }
        }
    }


	/**
	 * 修改市场用户
	 */
	@Override
	public  ResponseEntity updateJRecord(int id,String account){
		if(StringUtils.isEmpty(account)){
			return new ResponseEntity("false", "账号不能为空！");
		}
		List<JRecord> list = recordMapper.selectJRecordById(id);
		if(list != null && list.size()>0){
			List<UserBean> userList = userMapper.getUserIdByAccount(account);
			if(userList != null && userList.size()>0){
				List<JRecord> recordList = recordMapper.selectJRecordByAccount(account,null);
				if(recordList == null || recordList.size() == 0){
					UserBean userBean = userList.get(0);
					JRecord record = new JRecord();
					record.setId(id);
					record.setuId(userBean.getId());
					record.setuAccount(account);
					int count = recordMapper.updateJRecordById(record);
					if(count == 1){
						JRecord record2 = list.get(0);
						List<JRecord> pRecordList = recordMapper.selectJRecordByPid(record2.getuId());
						for (JRecord pRecord : pRecordList) {
							recordMapper.upJRecordById(userBean.getId(), pRecord.getId());
						}
						return new ResponseEntity("true", "修改成功！");
						
					}
					return new ResponseEntity("false", "修改失败！");
				}
				return new ResponseEntity("false", "用户已被分配，修改失败！");
			}
			return new ResponseEntity("false", "账号不可用！");
		}
		return new ResponseEntity("false", "请重新修改！");
	}
	
	
	/**
	 * 删除市场用户
	 */
	@Override
	public ResponseEntity deleteJRecord(int id){
		List<JRecord> list = recordMapper.selectJRecordById(id);
		if(list != null && list.size()>0){
			JRecord record = list.get(0);
			List<JRecord> recordList = recordMapper.selectJRecordByPid(record.getuId());
			if(recordList != null && recordList.size()>0){
				for (JRecord jRecord : recordList) {
					deleteJRecord(jRecord.getId());
				}
			}
			int count = recordMapper.deleteJRecordById(id);
			if(count > 0){
				return new ResponseEntity("true", "删除成功！");
			}
			return new ResponseEntity("false", "删除失败！");
		}
		return new ResponseEntity("false", "删除失败！");
	}

}
