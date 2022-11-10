package com.zhang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhang.dto.SetmealDto;
import com.zhang.entity.Setmeal;
import com.zhang.mapper.SetmealMapper;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐，同时保存套餐是和菜品关系
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐的方法同时需要删除套餐和菜品的关联数据
     * @param ids
     */
    public void removeWithDish(List<Long> ids);
}
