package com.jyss.yqy.mapper;


import com.jyss.yqy.entity.JRecordZl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface JRecordZlMapper {


	List<JRecordZl> selectJRecordZl(@Param("uId")Integer uId,@Param("zjUid")Integer zjUid);

	int addJRecordZl(JRecordZl jRecordZl);

	int updateJRecordZl(JRecordZl jRecordZl);

	int deleteJRecordZl(@Param("id")Integer id);
    
  
}