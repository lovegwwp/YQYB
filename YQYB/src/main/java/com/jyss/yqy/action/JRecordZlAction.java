package com.jyss.yqy.action;

import com.jyss.yqy.entity.JRecordZl;
import com.jyss.yqy.entity.ResponseEntity;
import com.jyss.yqy.service.JRecordZlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/zl")
public class JRecordZlAction {

    @Autowired
    private JRecordZlService jRecordZlService;


    /**
     * 查询代理市场
     */
    @RequestMapping("/selectZl")
    @ResponseBody
    public List<JRecordZl> selectJRecordZl(@RequestParam("uId")Integer uId){
        List<JRecordZl> list = jRecordZlService.selectJRecordZl(uId);
        return list;
    }


    /**
     * 添加市场助理
     */
    @RequestMapping("/insertZl")
    @ResponseBody
    public ResponseEntity insertJRecordZl(@RequestParam("uId")Integer uId,@RequestParam("zjUid")Integer zjUid,
                                          @RequestParam("zjCode")String zjCode,@RequestParam("zjName")String zjName){
        if(uId == null || zjUid == null){
            return new ResponseEntity("false","助理或市场不能为空");
        }
        ResponseEntity result = jRecordZlService.insertJRecordZl(uId, zjUid, zjCode, zjName);
        return result;
    }



    /**
     * 修改市场助理
     */
    @RequestMapping("/updateZl")
    @ResponseBody
    public ResponseEntity updateJRecordZl(@RequestParam("id")Integer id,@RequestParam("uId")Integer uId,
                                          @RequestParam("zjUid")Integer zjUid,@RequestParam("zjCode")String zjCode,
                                          @RequestParam("zjName")String zjName){
        if(uId == null || zjUid == null){
            return new ResponseEntity("false","助理或市场不能为空");
        }
        ResponseEntity result = jRecordZlService.updateJRecordZl(id, uId, zjUid, zjCode, zjName);
        return result;
    }


    /**
     * 删除市场助理
     */
    @RequestMapping("/deleteZl")
    @ResponseBody
    public ResponseEntity deleteJRecordZl(@RequestParam("id")Integer id){
        ResponseEntity result = jRecordZlService.deleteJRecordZl(id);
        return result;
    }



}
