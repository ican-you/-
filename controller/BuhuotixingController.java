
package com.A14.controller;

import java.io.File;
import java.net.URL;

import com.A14.entity.BuhuotixingEntity;
import com.A14.entity.view.BuhuotixingView;
import com.A14.service.BuhuotixingService;
import com.A14.service.YonghuService;
import com.A14.utils.PoiUtil;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;

import com.A14.service.TokenService;
import com.A14.utils.*;

import com.A14.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.A14.entity.*;
import com.A14.entity.view.*;
import com.A14.service.*;
import com.A14.utils.PageUtils;
import com.A14.utils.R;

/**
 * 补货提醒
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/buhuotixing")
public class BuhuotixingController {
    private static final Logger logger = LoggerFactory.getLogger(BuhuotixingController.class);

    @Autowired
    private BuhuotixingService buhuotixingService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private YonghuService yonghuService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("员工".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        params.put("buhuotixingDeleteStart",1);params.put("buhuotixingDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = buhuotixingService.queryPage(params);

        //字典表数据转换
        List<BuhuotixingView> list =(List<BuhuotixingView>)page.getList();
        for(BuhuotixingView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        BuhuotixingEntity buhuotixing = buhuotixingService.selectById(id);
        if(buhuotixing !=null){
            //entity转view
            BuhuotixingView view = new BuhuotixingView();
            BeanUtils.copyProperties( buhuotixing , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody BuhuotixingEntity buhuotixing, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,buhuotixing:{}",this.getClass().getName(),buhuotixing.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<BuhuotixingEntity> queryWrapper = new EntityWrapper<BuhuotixingEntity>()
            .eq("buhuotixing_name", buhuotixing.getBuhuotixingName())
            .eq("buhuotixing_types", buhuotixing.getBuhuotixingTypes())
            .eq("buhuotixing_number", buhuotixing.getBuhuotixingNumber())
            .eq("buhuotixing_stauts_types", buhuotixing.getBuhuotixingStautsTypes())
            .eq("buhuotixing_delete", buhuotixing.getBuhuotixingDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        BuhuotixingEntity buhuotixingEntity = buhuotixingService.selectOne(queryWrapper);
        if(buhuotixingEntity==null){
            buhuotixing.setBuhuotixingDelete(1);
            buhuotixing.setCreateTime(new Date());
            buhuotixingService.insert(buhuotixing);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody BuhuotixingEntity buhuotixing, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,buhuotixing:{}",this.getClass().getName(),buhuotixing.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<BuhuotixingEntity> queryWrapper = new EntityWrapper<BuhuotixingEntity>()
            .notIn("id",buhuotixing.getId())
            .andNew()
            .eq("buhuotixing_name", buhuotixing.getBuhuotixingName())
            .eq("buhuotixing_types", buhuotixing.getBuhuotixingTypes())
            .eq("buhuotixing_number", buhuotixing.getBuhuotixingNumber())
            .eq("buhuotixing_stauts_types", buhuotixing.getBuhuotixingStautsTypes())
            .eq("buhuotixing_delete", buhuotixing.getBuhuotixingDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        BuhuotixingEntity buhuotixingEntity = buhuotixingService.selectOne(queryWrapper);
        if(buhuotixingEntity==null){
            buhuotixingService.updateById(buhuotixing);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        ArrayList<BuhuotixingEntity> list = new ArrayList<>();
        for(Integer id:ids){
            BuhuotixingEntity buhuotixingEntity = new BuhuotixingEntity();
            buhuotixingEntity.setId(id);
            buhuotixingEntity.setBuhuotixingDelete(2);
            list.add(buhuotixingEntity);
        }
        if(list != null && list.size() >0){
            buhuotixingService.updateBatchById(list);
        }
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<BuhuotixingEntity> buhuotixingList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            BuhuotixingEntity buhuotixingEntity = new BuhuotixingEntity();
//                            buhuotixingEntity.setBuhuotixingName(data.get(0));                    //物品名称 要改的
//                            buhuotixingEntity.setBuhuotixingTypes(Integer.valueOf(data.get(0)));   //物品类型 要改的
//                            buhuotixingEntity.setBuhuotixingNumber(Integer.valueOf(data.get(0)));   //补货数量 要改的
//                            buhuotixingEntity.setBuhuotixingStautsTypes(Integer.valueOf(data.get(0)));   //补货状态 要改的
//                            buhuotixingEntity.setBuhuotixingDelete(1);//逻辑删除字段
//                            buhuotixingEntity.setCreateTime(date);//时间
                            buhuotixingList.add(buhuotixingEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        buhuotixingService.insertBatch(buhuotixingList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }






}
