package com.A14.service.impl;

import org.springframework.stereotype.Service;

import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.A14.utils.PageUtils;
import com.A14.utils.Query;
import com.A14.dao.BuhuotixingDao;
import com.A14.entity.BuhuotixingEntity;
import com.A14.service.BuhuotixingService;
import com.A14.entity.view.BuhuotixingView;

/**
 * 补货提醒 服务实现类
 */
@Service("buhuotixingService")
@Transactional
public class BuhuotixingServiceImpl extends ServiceImpl<BuhuotixingDao, BuhuotixingEntity> implements BuhuotixingService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<BuhuotixingView> page =new Query<BuhuotixingView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
