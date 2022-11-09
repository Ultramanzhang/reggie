package com.zhang.contorller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhang.common.R;
import com.zhang.entity.Category;
import com.zhang.entity.Setmeal;
import com.zhang.service.SetmealService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;


    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize){
        //分页构造器
        Page<Setmeal> pageInfo = new Page<>(page,pageSize);
        //条件构造器
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        //添加排序条件
        queryWrapper.orderByAsc(Setmeal::getUpdateTime);
        //进行分页查询
        setmealService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }


    /**
     * 批量状态更改
     * @param status
     * @param ids
     * @return
     */
    @PostMapping("/status/{status}")
    public R<String> updateStatus(@PathVariable int status,String ids){
        String[] list=ids.split(",");
        for (String id : list) {
            Setmeal setmeal=new Setmeal();
            setmeal.setId(Long.parseLong(id));
            setmeal.setStatus(status);
            setmealService.updateById(setmeal);
        }
        return R.success("更新成功");
    }


}
