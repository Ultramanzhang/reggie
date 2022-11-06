package com.zhang.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.entity.Employee;
import com.zhang.mapper.EmployeeMapper;
import com.zhang.service.EmployeeServie;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeServie {
}
