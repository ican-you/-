package com.A14.service;

import com.baomidou.mybatisplus.service.IService;
import com.A14.utils.PageUtils;
import com.A14.entity.DictionaryEntity;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * 字典表 服务类
 */
public interface DictionaryService extends IService<DictionaryEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);
      /**
      * 字典表转换
      * @param obj
      */
     void dictionaryConvert(Object obj, HttpServletRequest request);
}