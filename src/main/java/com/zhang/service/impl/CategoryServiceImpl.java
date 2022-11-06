package com.zhang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.common.CustomException;
import com.zhang.entity.Category;
import com.zhang.entity.Dish;
import com.zhang.entity.Setmeal;
import com.zhang.mapper.CategoryMapper;
import com.zhang.service.CategoryService;
import com.zhang.service.DishService;
import com.zhang.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;


    /**
     * 根据id删除分类,删除之前需要进行判断
     * @param id
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int count = dishService.count(dishLambdaQueryWrapper);

        //查询当前分类是否关联了菜品,如果已经关联,抛出业务异常
        if (count>0){
            //已经关联菜品,抛出业务异常
            throw new CustomException("当前分类下关联了菜品,不能删除");
        }

        //查询当前分类是否关联了套餐,如果已经关联,抛出业务异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count1 = setmealService.count(setmealLambdaQueryWrapper);

        if (count1>0){
            //已经关联套餐,抛出业务异常
        }

        //正常删除

        super.removeById(id);
    }
}
