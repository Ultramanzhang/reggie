package com.zhang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.common.CustomException;
import com.zhang.dto.SetmealDto;
import com.zhang.entity.Setmeal;
import com.zhang.entity.SetmealDish;
import com.zhang.mapper.SetmealMapper;
import com.zhang.service.SetmealDishService;
import com.zhang.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal>implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;
    /**
     * 新增套餐，同时保存套餐是和菜品关系
     * @param setmealDto
     */
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        //保存套餐的基本信息,操作setmeal，执行insert操作
        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();

        setmealDishes.stream().map((item)->{
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        //保存套餐和菜品的关联信息，操作setmeal_dish，执行insert操作
        setmealDishService.saveBatch(setmealDishes);
    }


    /**
     * 删除套餐的方法同时需要删除套餐和菜品的关联数据
     * @param ids
     */
    @Transactional
    public void removeWithDish(List<Long> ids) {
        //查询套餐的状态，确定是否删除
        LambdaQueryWrapper<Setmeal> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId,ids);
        queryWrapper.eq(Setmeal::getStatus,1);

        int count=this.count(queryWrapper);
        if (count>0){
            //如果不能删除抛出一个业务异常
            throw new CustomException("套餐正在售卖中，不可删除");
        }

        //如果可以删除，先删除套餐表中的数据
        this.removeByIds(ids);

        LambdaQueryWrapper<SetmealDish> queryWrapper1=new LambdaQueryWrapper<>();
        queryWrapper1.in(SetmealDish::getSetmealId,ids);
        //删除关系表中的数据----setmeal_dish
        setmealDishService.remove(queryWrapper1);
    }
}
