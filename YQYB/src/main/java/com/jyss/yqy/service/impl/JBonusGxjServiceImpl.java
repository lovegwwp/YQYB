package com.jyss.yqy.service.impl;


import com.jyss.yqy.entity.JRecordResult;
import com.jyss.yqy.entity.JRecordTotal;
import com.jyss.yqy.mapper.JBonusGxjMapper;
import com.jyss.yqy.mapper.ScoreBalanceMapper;
import com.jyss.yqy.service.JBonusGxjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JBonusGxjServiceImpl implements JBonusGxjService {

    @Autowired
    private JBonusGxjMapper bonusGxjMapper;
    @Autowired
    private ScoreBalanceMapper scoreBalanceMapper;


    /**
     * 昨日数据
     */
    @Override
    public JRecordResult selectJBonusGxj() {
        float amount = bonusGxjMapper.selectGxjTotal();
        float cashScore = scoreBalanceMapper.selectCashScore(7);
        float shoppingScore = scoreBalanceMapper.selectShoppingScore(7);
        float elecScore = scoreBalanceMapper.selectElecScore(7);

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
    public JRecordResult selectJBonusGxjWek() {
        float amount = bonusGxjMapper.selectGxjTotalByWek();
        float cashScore = scoreBalanceMapper.selectCashScoreByWek(7);
        float shoppingScore = scoreBalanceMapper.selectShoppingScoreByWek(7);
        float elecScore = scoreBalanceMapper.selectElecScoreByWek(7);

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
    public JRecordResult selectJBonusGxjByDay(String beginTime, String endTime) {
        float amount = bonusGxjMapper.selectGxjTotalByDay(beginTime,endTime);
        float cashScore = scoreBalanceMapper.selectCashScoreByDay(7,beginTime,endTime);
        float shoppingScore = scoreBalanceMapper.selectShoppingScoreByDay(7,beginTime,endTime);
        float elecScore = scoreBalanceMapper.selectElecScoreByDay(7,beginTime,endTime);

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
    public JRecordResult selectJBonusGxjByMonth(String month) {
        float amount = bonusGxjMapper.selectGxjTotalByMonth(month);
        float cashScore = scoreBalanceMapper.selectCashScoreByMonth(7,month);
        float shoppingScore = scoreBalanceMapper.selectShoppingScoreByMonth(7,month);
        float elecScore = scoreBalanceMapper.selectElecScoreByMonth(7,month);

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
    public List<JRecordTotal> selectGxjTotalList() {
        return bonusGxjMapper.selectGxjTotalList();
    }


}
