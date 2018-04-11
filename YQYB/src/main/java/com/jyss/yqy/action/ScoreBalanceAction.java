package com.jyss.yqy.action;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyss.yqy.entity.JRecord;
import com.jyss.yqy.entity.Page;
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
     * 财务统计  tjType: 1支付宝，2微信，3财务充值
     */
    @RequestMapping("/ht/bill")
    @ResponseBody
    public Page<ScoreBalance> selectBdScore(@RequestParam("tjType") Integer tjType,
                                            @RequestParam(value = "page", required = true) int page,
                                            @RequestParam(value = "rows", required = true) int rows){
        PageHelper.startPage(page, rows);
        List<ScoreBalance> list = scoreBalanceService.getEntryScoreBalance(tjType);
        PageInfo<ScoreBalance> pageInfo = new PageInfo<ScoreBalance>(list);
        return new Page<ScoreBalance>(pageInfo);

    }


}
