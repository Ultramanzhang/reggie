package com.zhegu.test;

import com.zhang.entity.Dish;
import com.zhang.service.DishService;
import org.junit.jupiter.api.Test;

public class SQLtest {
    private DishService dishService;
    @Test
    public void test2(){
        String ids="1590164496431575041";
        int status=0;
        Dish dish=new Dish();
        dish.setId(Long.parseLong(ids));
        dish.setStatus(status);
        dishService.updateById(dish);

    }
}