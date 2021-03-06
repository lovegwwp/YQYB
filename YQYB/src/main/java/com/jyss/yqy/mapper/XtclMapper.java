package com.jyss.yqy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyss.yqy.entity.BaseArea;
import com.jyss.yqy.entity.Xtcl;
import org.springframework.stereotype.Repository;

@Repository
public interface XtclMapper {
	/**
	 * 根据标识符取得维护常量
	 * 
	 * @return
	 */
	List<Xtcl> getCls();

	/**
	 * 根据标识符搜索
	 * 
	 * @param bz_type
	 * @param bz_value
	 * @return
	 */
	List<Xtcl> getClsBy(@Param("bz_type") String bz_type,
			@Param("bz_value") String bz_value);

	/**
	 * 获取常量下拉 area
	 * 
	 * @param bz_type
	 * @param pid
	 * @return
	 */
	List<Xtcl> getClsCombox(@Param("bz_type") String bz_type,
			@Param("pid") String pid);

	/**
	 * 获取常量下拉 cl
	 * 
	 * @param bz_type
	 * @param pid
	 * @return
	 */
	List<Xtcl> getClsCl(@Param("bz_type") String bz_type,
			@Param("pid") String pid);


	//根据标识符取得标志对应常量值
	Xtcl getClsValue(@Param("bz_type") String bz_type,@Param("bz_id") String bz_id);


	/**
	 * 新增
	 * 
	 * @param cl
	 * @return
	 */
	int addCl(Xtcl cl);

	/**
	 * 修改
	 */
	int updateCl(Xtcl cl);

	/**
	 * 删除 批量
	 * 
	 * @param ids
	 * @return
	 */
	int deleteCl(@Param("ids") List<Long> ids);

	// //////////////系统地域表////////////////////////////

	/**
	 * 地域表area=2省份 3城市 4区域'
	 * 
	 * @param status
	 * @param area
	 * @param fid
	 * @return
	 */
	List<BaseArea> getBaseAreas(@Param("status") String status,
			@Param("area") String area, @Param("fid") String fid,
			@Param("id") String id);

}
