package com.jyss.yqy.service.impl;


import com.jyss.yqy.entity.JRecordResult;
import com.jyss.yqy.entity.JRecordTotal;
import com.jyss.yqy.mapper.JBonusFhjMapper;
import com.jyss.yqy.mapper.ScoreBalanceMapper;
import com.jyss.yqy.service.JBonusFhjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JBonusFhjServiceImpl implements JBonusFhjService {

    @Autowired
    private JBonusFhjMapper bonusFhjMapper;
    @Autowired
    private ScoreBalanceMapper scoreBalanceMapper;


    /**
     * 当日数据
     */
    @Override
    public JRecordResult selectJBonusFhj() {
        float amount = bonusFhjMapper.selectFhjTotal();
        float cashScore = scoreBalanceMapper.selectTodayCashScore(4);
        float shoppingScore = scoreBalanceMapper.selectTodayShoppingScore(4);
        float elecScore = scoreBalanceMapper.selectTodayElecScore(4);

        JRecordResult result = new JRecordResult();
        result.setAmount(amount);
        result.setCashScore(cashScore);
        result.setShoppingScore(shoppingScore);
        result.setElecScore(elecScore);
        return result;
    }


    /**
     * 本周数据
     */
    @Override
    public JRecordResult selectJBonusFhjWek() {
        float amount = bonusFhjMapper.selectFhjTotalByWek();
        float cashScore = scoreBalanceMapper.selectCashScoreByWek1(4);
        float shoppingScore = scoreBalanceMapper.selectShoppingScoreByWek1(4);
        float elecScore = scoreBalanceMapper.selectElecScoreByWek1(4);

        JRecordResult result = new JRecordResult();
        result.setAmount(amount);
        result.setCashScore(cashScore);
        result.setShoppingScore(shoppingScore);
        result.setElecScore(elecScore);
        return result;
    }


    /**
     * 两个日期数据
     */
    @Override
    public JRecordResult selectJBonusFhjByDay(String beginTime, String endTime) {
        float amount = bonusFhjMapper.selectFhjTotalByDay(beginTime,endTime);
        float cashScore = scoreBalanceMapper.selectCashScoreByDay1(4,beginTime,endTime);
        float shoppingScore = scoreBalanceMapper.selectShoppingScoreByDay1(4,beginTime,endTime);
        float elecScore = scoreBalanceMapper.selectElecScoreByDay1(4,beginTime,endTime);

        JRecordResult result = new JRecordResult();
        result.setAmount(amount);
        result.setCashScore(cashScore);
        result.setShoppingScore(shoppingScore);
        result.setElecScore(elecScore);
        return result;
    }


    /**
     * 按月数据
     */
    @Override
    public JRecordResult selectJBonusFhjByMonth(String month) {
        float amount = bonusFhjMapper.selectFhjTotalByMonth(month);
        float cashScore = scoreBalanceMapper.selectCashScoreByMonth1(4,month);
        float shoppingScore = scoreBalanceMapper.selectShoppingScoreByMonth1(4,month);
        float elecScore = scoreBalanceMapper.selectElecScoreByMonth1(4,month);

        JRecordResult result = new JRecordResult();
        result.setAmount(amount);
        result.setCashScore(cashScore);
        result.setShoppingScore(shoppingScore);
        result.setElecScore(elecScore);
        return result;
    }


    /**
     * 总收益列表
     */
    @Override
    public List<JRecordTotal> selectFhjTotalList(String month) {
        return bonusFhjMapper.selectFhjTotalList(month);
    }


}
