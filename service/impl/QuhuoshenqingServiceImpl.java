package com.A14.service.impl;

import org.springframework.stereotype.Service;

import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.A14.utils.PageUtils;
import com.A14.utils.Query;
import com.A14.dao.QuhuoshenqingDao;
import com.A14.entity.QuhuoshenqingEntity;
import com.A14.service.QuhuoshenqingService;
import com.A14.entity.view.QuhuoshenqingView;

/**
 * 取货申请 服务实现类
 */
@Service("quhuoshenqingService")
@Transactional
public class QuhuoshenqingServiceImpl extends ServiceImpl<QuhuoshenqingDao, QuhuoshenqingEntity> implements QuhuoshenqingService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<QuhuoshenqingView> page =new Query<QuhuoshenqingView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
