package com.jyss.yqy.action;


import com.jyss.yqy.entity.ResponseEntity;
import com.jyss.yqy.entity.ScoreBalance;
import com.jyss.yqy.service.ScoreBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ScoreBalanceAction {

    @Autowired
    private ScoreBalanceService scoreBalanceService;


    /**
     * 后台充值报单券     zzCode = 财务账号 ，uuid = 用户uuid
     */
    @RequestMapping("/ht/topup")
    @ResponseBody
    public ResponseEntity insertBdScore(@RequestParam("uuid") String uuid, @RequestParam("payAmount") Float payAmount,
                                        @RequestParam("zzCode") String zzCode){

        ResponseEntity result = scoreBalanceService.insertBdScore(uuid, payAmount, zzCode);

        return result;
    }


    /**
     * 借贷充值报单券
     */
    @RequestMapping("/ht/borrow")
    @ResponseBody
    public ResponseEntity updateUserBorrow(@RequestParam("uuid") String uuid, @RequestParam("payAmount") Float payAmount,
                                           @RequestParam("zzCode") String zzCode){

        ResponseEntity result = scoreBalanceService.updateUserBorrow(uuid, payAmount, zzCode);

        return result;
    }




    /**
     * 条件查询充值记录        tjType: 1支付宝，2微信，3线下充值，4借贷充值
     */
    @RequestMapping("/ht/bill")
    @ResponseBody
    public List<ScoreBalance> selectBdScore(@RequestParam("tjType") Integer tjType,
                                            @RequestParam("beginTime") String beginTime,
                                            @RequestParam("endTime") String endTime){
        //PageHelper.startPage(page, rows);
        List<ScoreBalance> list = scoreBalanceService.getEntryScoreBalance(tjType,beginTime,endTime);
        //PageInfo<ScoreBalance> pageInfo = new PageInfo<ScoreBalance>(list);
        //return new Page<ScoreBalance>(pageInfo);
        return list;

    }


    /**
     * 条件查询充值总值        tjType: 1支付宝，2微信，3财务充值
     */
    @RequestMapping("/ht/total")
    @ResponseBody
    public double selectTotalBdScore(@RequestParam("tjType") Integer tjType,
                                     @RequestParam("beginTime") String beginTime,
                                     @RequestParam("endTime") String endTime){

        return scoreBalanceService.selectTotalBdScore(tjType, beginTime, endTime);

    }




}
