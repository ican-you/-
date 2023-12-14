package com.A14.dao;

import com.A14.entity.DictionaryEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.A14.entity.view.DictionaryView;

/**
 * 字典表 Dao 接口
 *
 * @author 
 */
public interface DictionaryDao extends BaseMapper<DictionaryEntity> {

   List<DictionaryView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
