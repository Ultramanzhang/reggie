package com.zhegu.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhegu.reggie.entity.Orders;
import com.zhegu.reggie.mapper.OrdersMapper;
import com.zhegu.reggie.service.OrdersService;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>implements OrdersService {
}
