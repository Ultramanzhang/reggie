package com.zhang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.entity.Dish;
import com.zhang.mapper.DishMapper;
import com.zhang.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class DishServicelmpl extends ServiceImpl<DishMapper, Dish>implements DishService {
}
