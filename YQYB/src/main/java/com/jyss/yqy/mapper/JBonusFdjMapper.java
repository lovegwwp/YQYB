package com.jyss.yqy.mapper;

import com.jyss.yqy.entity.JBonusFdj;
import com.jyss.yqy.entity.JBonusFdjExample;
import com.jyss.yqy.entity.JBonusGlj;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface JBonusFdjMapper {
    int countByExample(JBonusFdjExample example);

    int deleteByExample(JBonusFdjExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(JBonusFdj record);

    int insertSelective(JBonusFdj record);

    List<JBonusFdj> selectByExample(JBonusFdjExample example);

    JBonusFdj selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") JBonusFdj record, @Param("example") JBonusFdjExample example);

    int updateByExample(@Param("record") JBonusFdj record, @Param("example") JBonusFdjExample example);

    int updateByPrimaryKeySelective(JBonusFdj record);

    int updateByPrimaryKey(JBonusFdj record);
    
    
    //查询昨日个人列表详情
    //List<JBonusFdj> selectJBonusFdjByPid(@Param("parentId")int parentId);
    
    //查询昨日个人列表总值
    //List<JBonusFdj> selectJBonusFdj();
    
    //查询昨日总金额
    double selectFdjTotal();
    
    //查询本周总金额
    double selectFdjTotalByWek();
    
    //按两个日期查询个人列表详情
    //List<JBonusFdj> selectJBonusFdjByPidAndDay(@Param("parentId")int parentId, @Param("beginTime")String beginTime,@Param("endTime")String endTime);
    
    //按两个日期查询个人列表总值
    //List<JBonusFdj> selectJBonusFdjByDay(@Param("beginTime")String beginTime,@Param("endTime")String endTime);
    
    //按两个日期查询总收益
    double selectFdjTotalByDay(@Param("beginTime")String beginTime,@Param("endTime")String endTime);
    
    //查询本周个人列表总值
    //List<JBonusFdj> selectJBonusFdjWek();
    
    //按月查询
    //List<JBonusFdj> selectJBonusFdjByMonth(@Param("parentId")int parentId, @Param("month")String month);
    
    //按月查询总收益
    double selectFdjTotalByMonth(@Param("month")String month);
    
}