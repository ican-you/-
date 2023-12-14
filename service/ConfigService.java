
package com.A14.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.A14.entity.ConfigEntity;
import com.A14.utils.PageUtils;


/**
 * 系统用户
 * @author yangliyuan
 * @date 2019年10月10日 上午9:18:20
 */
public interface ConfigService extends IService<ConfigEntity> {
	PageUtils queryPage(Map<String, Object> params);
}
