package com.jyss.yqy.service.impl;


import com.jyss.yqy.entity.JRecordResult;
import com.jyss.yqy.mapper.JBonusCjMapper;
import com.jyss.yqy.mapper.ScoreBalanceMapper;
import com.jyss.yqy.service.JBonusCjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JBonusCjServiceImpl implements JBonusCjService {

    @Autowired
    private JBonusCjMapper bonusCjMapper;
    @Autowired
    private ScoreBalanceMapper scoreBalanceMapper;


    /**
     * 当日数据
     */
    @Override
    public JRecordResult selectJBonusCj() {
        float amount = bonusCjMapper.selectCjTotal();
        float cashScore = scoreBalanceMapper.selectTodayCashScore(5);
        float shoppingScore = scoreBalanceMapper.selectTodayShoppingScore(5);
        float elecScore = scoreBalanceMapper.selectTodayElecScore(5);

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
    public JRecordResult selectJBonusCjWek() {
        float amount = bonusCjMapper.selectCjTotalByWek();
        float cashScore = scoreBalanceMapper.selectCashScoreByWek1(5);
        float shoppingScore = scoreBalanceMapper.selectShoppingScoreByWek1(5);
        float elecScore = scoreBalanceMapper.selectElecScoreByWek1(5);

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
    public JRecordResult selectJBonusCjByDay(String beginTime, String endTime) {
        float amount = bonusCjMapper.selectCjTotalByDay(beginTime,endTime);
        float cashScore = scoreBalanceMapper.selectCashScoreByDay1(5,beginTime,endTime);
        float shoppingScore = scoreBalanceMapper.selectShoppingScoreByDay1(5,beginTime,endTime);
        float elecScore = scoreBalanceMapper.selectElecScoreByDay1(5,beginTime,endTime);

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
    public JRecordResult selectJBonusCjByMonth(String month) {
        float amount = bonusCjMapper.selectCjTotalByMonth(month);
        float cashScore = scoreBalanceMapper.selectCashScoreByMonth1(5,month);
        float shoppingScore = scoreBalanceMapper.selectShoppingScoreByMonth1(5,month);
        float elecScore = scoreBalanceMapper.selectElecScoreByMonth1(5,month);

        JRecordResult result = new JRecordResult();
        result.setAmount(amount);
        result.setCashScore(cashScore);
        result.setShoppingScore(shoppingScore);
        result.setElecScore(elecScore);
        return result;
    }


}
