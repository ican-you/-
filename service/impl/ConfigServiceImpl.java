
package com.A14.service.impl;


import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.A14.dao.ConfigDao;
import com.A14.entity.ConfigEntity;
import com.A14.service.ConfigService;
import com.A14.utils.PageUtils;
import com.A14.utils.Query;


/**
 * 系统用户
 * @author yangliyuan
 * @date 2019年10月10日 上午9:17:59
 */
@Service("configService")
public class ConfigServiceImpl extends ServiceImpl<ConfigDao, ConfigEntity> implements ConfigService {
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<ConfigEntity> page = this.selectPage(
                new Query<ConfigEntity>(params).getPage(),
                new EntityWrapper<ConfigEntity>()
        );
        return new PageUtils(page);
	}
}
