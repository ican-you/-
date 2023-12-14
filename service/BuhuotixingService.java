package com.A14.service;

import com.baomidou.mybatisplus.service.IService;
import com.A14.utils.PageUtils;
import com.A14.entity.BuhuotixingEntity;
import java.util.Map;

/**
 * 补货提醒 服务类
 */
public interface BuhuotixingService extends IService<BuhuotixingEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);
}