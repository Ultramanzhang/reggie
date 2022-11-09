package com.zhang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhang.dto.DishDto;
import com.zhang.entity.Dish;

public interface DishService extends IService<Dish> {

    //新增菜品，同时插入菜品对应的口味数据，需要操作两张表: dish，dish_flavvor
    public void saveWithFlavor(DishDto dishDto);
}
