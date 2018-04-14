package com.jyss.yqy.action;


import com.jyss.yqy.entity.AccountUser;
import com.jyss.yqy.entity.ResponseEntity;
import com.jyss.yqy.entity.ScoreBalance;
import com.jyss.yqy.service.AccountUserService;
import com.jyss.yqy.service.ScoreBalanceService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ScoreBalanceAction {

    @Autowired
    private ScoreBalanceService scoreBalanceService;
    @Autowired
    private AccountUserService auService;

    @RequestMapping("/hhrcwtj")
    public String hhrcwtjTz() {
        return "hhrcwtj";
    }


    /**
     * 后台充值报单券     zzCode = 财务账号 ，uuid = 用户uuid
     */
    @RequestMapping("/ht/topup")
    @ResponseBody
    public ResponseEntity insertBdScore(@RequestParam("uuid") String uuid, @RequestParam("payAmount") Float payAmount){
       // @RequestParam("zzCode") String zzCode
        Subject us = SecurityUtils.getSubject();
        String lName = us.getPrincipal().toString();
        AccountUser au = auService.getAuBy(lName);
        String zzCode = au.getUsername();
        ResponseEntity result = scoreBalanceService.insertBdScore(uuid, payAmount, zzCode);
        auService.addLog(lName,"财务管理-充值报单券");
        return result;
    }


    /**
     * 借贷充值报单券
     */
    @RequestMapping("/ht/borrow")
    @ResponseBody
    public ResponseEntity updateUserBorrow(@RequestParam("uuid") String uuid, @RequestParam("payAmount") Float payAmount  ){
       // @RequestParam("zzCode") String zzCode
        Subject us = SecurityUtils.getSubject();
        String lName = us.getPrincipal().toString();
        AccountUser au = auService.getAuBy(lName);
        String zzCode = au.getUsername();
        ResponseEntity result = scoreBalanceService.updateUserBorrow(uuid, payAmount, zzCode);
        auService.addLog(lName,"财务管理-借贷充值报单券");
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
        List<ScoreBalance> list = scoreBalanceService.getEntryScoreBalance(tjType,beginTime,endTime);
        Subject us = SecurityUtils.getSubject();
        String lName = us.getPrincipal().toString();
        auService.addLog(lName,"财务管理-财务查询");
        return list;

    }


    /**
     * 条件查询充值总值        tjType: 1支付宝，2微信，3财务充值
     */
    @RequestMapping("/ht/total")
    @ResponseBody
    public List<Map<String,Double>> selectTotalBdScore(@RequestParam("tjType") Integer tjType,
                                     @RequestParam("beginTime") String beginTime,
                                     @RequestParam("endTime") String endTime){
        Double b =  scoreBalanceService.selectTotalBdScore(tjType, beginTime, endTime);
        Map<String,Double> m  =new HashMap<String,Double>();
        m.put("totald",b);
        List<Map<String,Double>> ld = new ArrayList<Map<String,Double>>();
        ld.add(m);
        Subject us = SecurityUtils.getSubject();
        String lName = us.getPrincipal().toString();
        auService.addLog(lName,"财务管理-财务统计查询");
        return ld;

    }




}
