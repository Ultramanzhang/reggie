package com.zhegu.test;

import com.zhegu.reggie.entity.Dish;
import com.zhegu.reggie.service.DishService;
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
