package com.jyss.yqy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyss.yqy.entity.JBonusGlj;
import com.jyss.yqy.entity.JBonusGljExample;

public interface JBonusGljMapper {
    int countByExample(JBonusGljExample example);

    int deleteByExample(JBonusGljExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(JBonusGlj record);

    int insertSelective(JBonusGlj record);

    List<JBonusGlj> selectByExample(JBonusGljExample example);

    JBonusGlj selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") JBonusGlj record, @Param("example") JBonusGljExample example);

    int updateByExample(@Param("record") JBonusGlj record, @Param("example") JBonusGljExample example);

    int updateByPrimaryKeySelective(JBonusGlj record);

    int updateByPrimaryKey(JBonusGlj record);
    
    //查询昨日个人列表详情
    //List<JBonusGlj> selectJBonusGljByPid(@Param("parentId")int parentId);
    
    //查询昨日个人列表总值
    //List<JBonusGlj> selectJBonusGlj();
    
    //查询昨日总金额
    double selectGljTotal();
    
    //查询本周总金额
    double selectGljTotalByWek();
    
    //按两个日期查询个人列表详情
    List<JBonusGlj> selectJBonusGljByPidAndDay(@Param("parentId")int parentId, @Param("beginTime")String beginTime,@Param("endTime")String endTime);
    
    //按两个日期查询个人列表总值
    List<JBonusGlj> selectJBonusGljByDay(@Param("beginTime")String beginTime,@Param("endTime")String endTime);
    
    //按两个日期查询总收益
    double selectGljTotalByDay(@Param("beginTime")String beginTime,@Param("endTime")String endTime);
    
    
    //查询本周个人列表总值
    //List<JBonusGlj> selectJBonusGljWek();
    
    //按月查询
    //List<JBonusGlj> selectJBonusGljByMonth(@Param("parentId")int parentId, @Param("month")String month);
    
    //按月查询总收益
    double selectGljTotalByMonth(@Param("month")String month);
    
    
}