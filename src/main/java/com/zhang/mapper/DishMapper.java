package com.zhang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhang.entity.Dish;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
