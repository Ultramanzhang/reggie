package com.zhegu.reggie.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhegu.reggie.entity.Employee;
import com.zhegu.reggie.mapper.EmployeeMapper;
import com.zhegu.reggie.service.EmployeeServie;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeServie {
}
