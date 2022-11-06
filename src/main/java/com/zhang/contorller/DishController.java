package com.zhang.contorller;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhang.common.R;
import com.zhang.entity.Dish;

import com.zhang.service.DishService;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;


//    @PostMapping
//    public R<String> save(@RequestBody Dish dish){
//        dishService.save(dish);
//        return R.success("新增菜品成功");
//    }
//
//
//
//    @PostMapping
//    public R<String> update(@RequestBody Dish dish){
//        log.info("修改分类信息");
//
//
//        return null;
//    }


    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("page")
    public R<Page> page(int page,int pageSize,String name){
        log.info("page = {},pageSize = {},name={}",page,pageSize,name);
        //构造分页构造器
        Page<Dish> pageInfo = new Page<>(page,pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        //添加一个过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Dish::getName,name);
        //添加排序条件
        queryWrapper.orderByDesc(Dish::getUpdateTime);
        //执行查询
        dishService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }
}
