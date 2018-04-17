package com.jyss.yqy.action;


import com.jyss.yqy.constant.Constant;
import com.jyss.yqy.entity.AccountUser;
import com.jyss.yqy.entity.ResponseEntity;
import com.jyss.yqy.entity.ScoreBalance;
import com.jyss.yqy.entity.jsonEntity.UserBean;
import com.jyss.yqy.service.AccountUserService;
import com.jyss.yqy.service.ScoreBalanceService;
import com.jyss.yqy.service.UserService;
import com.jyss.yqy.utils.CommTool;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class ScoreBalanceAction {

    @Autowired
    private ScoreBalanceService scoreBalanceService;
    @Autowired
    private AccountUserService auService;
    @Autowired
    private UserService userService;

    @RequestMapping("/hhrcwtj")
    public String hhrcwtjTz() {
        return "hhrcwtj";
    }


    /**
     * 根据推荐码查询用户
     */
    @RequestMapping("/ht/userInfo")
    @ResponseBody
    public UserBean selectUserBy(@RequestParam("bCode")String bCode){
        List<UserBean> list = userService.getUserByBCode(bCode);
        if(list != null && list.size() == 1){
            UserBean userBean = list.get(0);
            if(!userBean.getAvatar().equals("")){
                userBean.setAvatar(Constant.httpUrl + userBean.getAvatar());
            }
            return userBean;
        }
        return null;
    }



    /**
     * 后台充值报单券     zzCode = 财务账号 ，uuid = 用户uuid
     */
    @RequestMapping("/ht/topup")
    @ResponseBody
    public ResponseEntity insertBdScore(@RequestParam("uuid") String uuid, @RequestParam("payAmount") Float payAmount,
                                        MultipartFile picture,HttpServletRequest request){
       // @RequestParam("zzCode") String zzCode
        Subject us = SecurityUtils.getSubject();
        String lName = us.getPrincipal().toString();
        AccountUser au = auService.getAuBy(lName);
        String zzCode = au.getUsername();


        //图片上传
        String filePath = request.getSession().getServletContext().getRealPath("/");
        int index = filePath.indexOf("YQYB");
        filePath = filePath.substring(0, index) + "uploadCzPic" + "/";
        /*File file = new File(filePath);
        CommTool.judeDirExists(file);*/

        String uploadCzPic = "";
        if(!StringUtils.isEmpty(picture)){
            String filename = picture.getOriginalFilename();
            String extName = filename.substring(filename.lastIndexOf("."));
            String imgPath = filePath + System.currentTimeMillis() + (long) (Math.random() * 1000L) + extName;
            //上传图片
            File file = new File(imgPath);
            if(!file.exists()){
                file.mkdirs();
            }
            //向磁盘写文件
            try {
                picture.transferTo(file);
            } catch (IOException e) {
                return new ResponseEntity("false","图片上传失败！");
            }

            uploadCzPic = imgPath.substring(imgPath.indexOf("uploadCzPic"));
        }

        ResponseEntity result = scoreBalanceService.insertBdScore(uuid, payAmount, zzCode, uploadCzPic);
        auService.addLog(lName,"财务管理-充值报单券");
        return result;
    }


    /**
     * 借贷充值报单券
     */
    @RequestMapping("/ht/borrow")
    @ResponseBody
    public ResponseEntity updateUserBorrow(@RequestParam("uuid") String uuid,@RequestParam("payAmount") Float payAmount,
                                           MultipartFile picture,HttpServletRequest request){
       // @RequestParam("zzCode") String zzCode
        Subject us = SecurityUtils.getSubject();
        String lName = us.getPrincipal().toString();
        AccountUser au = auService.getAuBy(lName);
        String zzCode = au.getUsername();

        //图片上传
        String filePath = request.getSession().getServletContext().getRealPath("/");
        int index = filePath.indexOf("YQYB");
        filePath = filePath.substring(0, index) + "uploadCzPic" + "/";
        /*File file = new File(filePath);
        CommTool.judeDirExists(file);*/

        String uploadCzPic = "";
        if(!StringUtils.isEmpty(picture)){
            String filename = picture.getOriginalFilename();
            String extName = filename.substring(filename.lastIndexOf("."));
            String imgPath = filePath + System.currentTimeMillis() + (long) (Math.random() * 1000L) + extName;
            //上传图片
            File file = new File(imgPath);
            if(!file.exists()){
                file.mkdirs();
            }
            //向磁盘写文件
            try {
                picture.transferTo(file);
            } catch (IOException e) {
                return new ResponseEntity("false","图片上传失败！");
            }

            uploadCzPic = imgPath.substring(imgPath.indexOf("uploadCzPic"));
        }

        ResponseEntity result = scoreBalanceService.updateUserBorrow(uuid, payAmount, zzCode, uploadCzPic);
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
