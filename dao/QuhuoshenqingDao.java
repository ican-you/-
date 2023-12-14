package com.A14.dao;

import com.A14.entity.QuhuoshenqingEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.A14.entity.view.QuhuoshenqingView;

/**
 * 取货申请 Dao 接口
 *
 * @author 
 */
public interface QuhuoshenqingDao extends BaseMapper<QuhuoshenqingEntity> {

   List<QuhuoshenqingView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
