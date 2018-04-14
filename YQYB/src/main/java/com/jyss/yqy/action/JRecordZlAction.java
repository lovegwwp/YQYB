package com.jyss.yqy.action;

import com.jyss.yqy.entity.AccountUser;
import com.jyss.yqy.entity.JRecordZl;
import com.jyss.yqy.entity.ResponseEntity;
import com.jyss.yqy.service.AccountUserService;
import com.jyss.yqy.service.JRecordZlService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
/*@RequestMapping("/zl")*/
public class JRecordZlAction {

    @Autowired
    private JRecordZlService jRecordZlService;
    @Autowired
    private AccountUserService auService;

    //助理分配市场
    @RequestMapping("/zlfpsc")
    public String zlfpscTz() {
        return "zlfpsc";
    }

    /**
     * 查询代理市场
     */
    @RequestMapping("/zl/selectZl")
    @ResponseBody
    public List<JRecordZl> selectJRecordZl(){
       /* @RequestParam("uId")Integer uId*/
        Subject us = SecurityUtils.getSubject();
        String lName = us.getPrincipal().toString();
        AccountUser au = auService.getAuBy(lName);
        Integer uId = au.getId();
        List<JRecordZl> list = jRecordZlService.selectJRecordZl(uId);
        auService.addLog(lName,"总监助理管理-对应市场查询");
        return list;
    }

    /**
     * 查询所有代理市场
     */
    @RequestMapping("/zl/selectAllZl")
    @ResponseBody
    public List<JRecordZl> selectAllZl(){
        Subject us = SecurityUtils.getSubject();
        String lName = us.getPrincipal().toString();
        List<JRecordZl> list = jRecordZlService.selectJRecordZl(null);
        auService.addLog(lName,"总监助理管理-助理市场查询");
        return list;
    }


    /**
     * 添加市场助理     uId = 总监助理id ，zjUid = 总监id ，zjCode = 总监的推荐码 ，zjName = 市场名称
     *
     */
    @RequestMapping("/zl/insertZl")
    @ResponseBody
    public ResponseEntity insertJRecordZl(@RequestParam("zjUid")Integer zjUid,@RequestParam("uId")Integer uId,
                                          @RequestParam("zjCode")String zjCode,@RequestParam("zjName")String zjName){
        Subject us = SecurityUtils.getSubject();
        String lName = us.getPrincipal().toString();
       // AccountUser au = auService.getAuBy(lName);
        //Integer uId = au.getId();

        if(uId == null || zjUid == null){
            return new ResponseEntity("false","助理或市场不能为空");
        }
        ResponseEntity result = jRecordZlService.insertJRecordZl(uId, zjUid, zjCode, zjName);
        auService.addLog(lName,"总监助理管理-添加市场助理");
        return result;
    }



    /**
     * 修改市场助理
     */
    @RequestMapping("/zl/updateZl")
    @ResponseBody
   //
    public ResponseEntity updateJRecordZl(@RequestParam("id")Integer id,@RequestParam("uId")Integer uId,
                                          @RequestParam("zjUid")Integer zjUid,@RequestParam("zjCode")String zjCode,
                                          @RequestParam("zjName")String zjName){
        Subject us = SecurityUtils.getSubject();
        String lName = us.getPrincipal().toString();
       // AccountUser au = auService.getAuBy(lName);
        //Integer uId = au.getId();

        if(uId == null || zjUid == null){
            return new ResponseEntity("false","助理或市场不能为空");
        }
        ResponseEntity result = jRecordZlService.updateJRecordZl(id, uId, zjUid, zjCode, zjName);
        auService.addLog(lName,"总监助理管理-修改市场助理");
        return result;
    }


    /**
     * 删除市场助理
     */
    @RequestMapping("/zl/deleteZl")
    @ResponseBody
    public ResponseEntity deleteJRecordZl(@RequestParam("id")Integer id){
        Subject us = SecurityUtils.getSubject();
        String lName = us.getPrincipal().toString();

        ResponseEntity result = jRecordZlService.deleteJRecordZl(id);
        auService.addLog(lName,"总监助理管理-删除市场助理");
        return result;
    }



}
