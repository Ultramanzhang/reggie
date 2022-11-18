package com.zhegu.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhegu.reggie.entity.Orders;

public interface OrdersService extends IService<Orders> {

    public void submit(Orders orders);
}
