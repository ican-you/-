
package com.A14.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.A14.entity.TokenEntity;
import com.A14.utils.PageUtils;


/**
 * token
 * @author yangliyuan
 * @date 2019年10月10日 上午9:18:20
 */
public interface TokenService extends IService<TokenEntity> {
 	PageUtils queryPage(Map<String, Object> params);
    
   	List<TokenEntity> selectListView(Wrapper<TokenEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<TokenEntity> wrapper);
	
   	String generateToken(Integer userid,String username,String tableName, String role);
   	
   	TokenEntity getTokenEntity(String token);
}
