package com.jyss.yqy.service.impl;

import com.jyss.yqy.entity.AccountUser;
import com.jyss.yqy.entity.JRecord;
import com.jyss.yqy.entity.JRecordZl;
import com.jyss.yqy.entity.ResponseEntity;
import com.jyss.yqy.entity.jsonEntity.UserBean;
import com.jyss.yqy.mapper.AccountUserMapper;
import com.jyss.yqy.mapper.JRecordMapper;
import com.jyss.yqy.mapper.JRecordZlMapper;
import com.jyss.yqy.mapper.UserMapper;
import com.jyss.yqy.service.JRecordZlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class JRecordZlServiceImpl implements JRecordZlService {

    @Autowired
    private JRecordZlMapper jRecordZlMapper;
    @Autowired
    private JRecordMapper jRecordMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AccountUserMapper accountMapper;



    /**
     * 查询代理市场
     */
    @Override
    public List<JRecordZl> selectJRecordZl(Integer uId) {
        return jRecordZlMapper.selectJRecordZl(uId,null);
    }


    /**
     * 添加市场助理
     */
    @Override
    public ResponseEntity insertJRecordZl(Integer uId, Integer zjUid, String zjCode, String zjName) {
        List<JRecordZl> zlList = jRecordZlMapper.selectJRecordZl(null, zjUid);
        if(zlList == null || zlList.size() == 0){
            List<AccountUser> userBeans = accountMapper.getZlRole(uId.toString(), "22");
            if(userBeans != null && userBeans.size() == 1){
                List<JRecord> jRecords = jRecordMapper.selectJRecordByAccount(null, zjUid + "");
                if(jRecords != null && jRecords.size() == 1){
                    JRecord jRecord = jRecords.get(0);
                    JRecord jRecord1 = new JRecord();
                    jRecord1.setId(jRecord.getId());
                    jRecord1.setZjUid(zjUid);
                    jRecordMapper.updateJRecordById(jRecord1);
                    JRecordZl recordZl = new JRecordZl();
                    recordZl.setuId(uId);
                    recordZl.setZjUid(zjUid);
                    recordZl.setZjCode(zjCode);
                    recordZl.setZjName(zjName);
                    recordZl.setStatus(1);
                    recordZl.setCreateTime(new Date());
                    int count = jRecordZlMapper.addJRecordZl(recordZl);
                    if(count == 1){
                        return new ResponseEntity("true","添加成功！");
                    }
                }
                return new ResponseEntity("false","该市场不存在！");
            }
            return new ResponseEntity("false","该助理不可用！");
        }
        return new ResponseEntity("false","该市场已有助理！");
    }


    /**
     * 修改市场助理
     */
    @Override
    public ResponseEntity updateJRecordZl(Integer id, Integer uId, Integer zjUid, String zjCode, String zjName) {
        List<JRecordZl> zlList = jRecordZlMapper.selectJRecordZl(null, zjUid);
        if(zlList == null || zlList.size() == 0){
            List<AccountUser> userBeans = accountMapper.getZlRole(uId.toString(), "22");
            if(userBeans != null && userBeans.size() == 1){
                JRecordZl recordZl = new JRecordZl();
                recordZl.setId(id);
                recordZl.setuId(uId);
                recordZl.setZjUid(zjUid);
                recordZl.setZjCode(zjCode);
                recordZl.setZjName(zjName);
                recordZl.setCreateTime(new Date());
                int count = jRecordZlMapper.updateJRecordZl(recordZl);
                if(count == 1){
                    return new ResponseEntity("true","修改成功！");
                }
                return new ResponseEntity("false","修改失败！");
            }
            return new ResponseEntity("false","该助理不可用！");
        }
        return new ResponseEntity("false","该市场已有助理！");
    }


    /**
     * 删除市场总监
     */
    @Override
    public ResponseEntity deleteJRecordZl(Integer id) {
        int count = jRecordZlMapper.deleteJRecordZl(id);
        if(count == 1){
            return new ResponseEntity("true","删除成功！");
        }
        return new ResponseEntity("false","删除失败！");
    }


}
