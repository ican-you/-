package com.A14.service.impl;

import org.springframework.stereotype.Service;

import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.A14.utils.PageUtils;
import com.A14.utils.Query;
import com.A14.dao.BuhuoshneqingDao;
import com.A14.entity.BuhuoshneqingEntity;
import com.A14.service.BuhuoshneqingService;
import com.A14.entity.view.BuhuoshneqingView;

/**
 * 补货申请 服务实现类
 */
@Service("buhuoshneqingService")
@Transactional
public class BuhuoshneqingServiceImpl extends ServiceImpl<BuhuoshneqingDao, BuhuoshneqingEntity> implements BuhuoshneqingService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<BuhuoshneqingView> page =new Query<BuhuoshneqingView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
