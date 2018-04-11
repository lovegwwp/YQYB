package com.jyss.yqy.service.impl;

import com.jyss.yqy.entity.ResponseEntity;
import com.jyss.yqy.entity.ScoreBalance;
import com.jyss.yqy.entity.Xtcl;
import com.jyss.yqy.entity.jsonEntity.UserBean;
import com.jyss.yqy.mapper.ScoreBalanceMapper;
import com.jyss.yqy.mapper.UserMapper;
import com.jyss.yqy.mapper.XtclMapper;
import com.jyss.yqy.service.ScoreBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class ScoreBalanceServiceImpl implements ScoreBalanceService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ScoreBalanceMapper scoreBalanceMapper;
    @Autowired
    private XtclMapper xtclMapper;


    @Override
    public ResponseEntity insertBdScore(String uuid, Float payAmount, String zzCode) {
        //后台报单券总池余额
        Xtcl xtcl1 = xtclMapper.getClsValue("bdqzc_type", "1");        //后台报单券总池余额
        double double1 = Double.parseDouble(xtcl1.getBz_value());                   //20000000

        if(payAmount < double1){
            List<UserBean> userBeans = userMapper.getUserByUuid(uuid);
            if(userBeans != null && userBeans.size() == 1){
                UserBean userBean = userBeans.get(0);

                SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
                String outTradeNo = sdf.format(new Date()) + "O" + userBean.getId()
                        + "r" + (long) (Math.random() * 1000L);

                //创建订单
                ScoreBalance scoreBalance = new ScoreBalance();
                scoreBalance.setEnd(2);
                scoreBalance.setuUuid(uuid);
                scoreBalance.setCategory(11);
                scoreBalance.setSecoCate(3);    //1支付宝，2微信，3线下充值
                scoreBalance.setType(1);
                scoreBalance.setScore(payAmount);
                scoreBalance.setJyScore(userBean.getBdScore() + payAmount);
                scoreBalance.setOrderSn(outTradeNo);
                scoreBalance.setStatus(1);
                scoreBalance.setZzCode(zzCode);
                int count = scoreBalanceMapper.insertEntryScore(scoreBalance);
                if(count == 1){
                    //修改报单券
                    UserBean userBean1 = new UserBean();
                    userBean1.setId(userBean.getId());
                    userBean1.setBdScore(userBean.getBdScore() + payAmount);
                    int count1 = userMapper.updateScore(userBean1);
                    if(count1 == 1){
                        //减总池
                        double syMoney = double1 - payAmount;
                        Xtcl xtcl = new Xtcl();
                        xtcl.setId(xtcl1.getId());
                        xtcl.setBz_type(syMoney + "");
                        xtclMapper.updateCl(xtcl);

                        return new ResponseEntity("true","购买成功！");
                    }
                }
                return new ResponseEntity("false","购买失败！");
            }
            return new ResponseEntity("false","用户不存在！");
        }
        return new ResponseEntity("false","无剩余报单券可购买！");
    }

    @Override
    public List<ScoreBalance> getEntryScoreBalance(Integer tjType) {
        return scoreBalanceMapper.getEntryScoreBalance(tjType);
    }


}
