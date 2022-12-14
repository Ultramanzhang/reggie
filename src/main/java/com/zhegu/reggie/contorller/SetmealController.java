package com.zhegu.reggie.contorller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhegu.reggie.common.R;
import com.zhegu.reggie.dto.SetmealDto;
import com.zhegu.reggie.entity.Category;
import com.zhegu.reggie.entity.Setmeal;
import com.zhegu.reggie.service.CategoryService;
import com.zhegu.reggie.service.SetmealService;
import com.zhegu.reggie.service.SetmealDishService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    @Autowired
    private SetmealDishService setmealDishService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        //分页构造器
        Page<Setmeal> pageInfo = new Page<>(page,pageSize);
        Page<SetmealDto>setmealDtoPage=new Page<>();
        //条件构造器
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        //添加一个过滤条件
        queryWrapper.like(name!=null,Setmeal::getName,name);
        //添加排序条件
        queryWrapper.orderByAsc(Setmeal::getUpdateTime);
        //进行分页查询
        setmealService.page(pageInfo,queryWrapper);
        //对象拷贝
        BeanUtils.copyProperties(pageInfo,setmealDtoPage,"records");

        List<Setmeal> records=pageInfo.getRecords();
        List<SetmealDto> list=records.stream().map((item)->{
           SetmealDto setmealDto=new SetmealDto();
           //对象拷贝
           BeanUtils.copyProperties(item,setmealDto);
           //分类id
           Long categoryId=item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if (category!=null){
                //分类名称
                String categoryName = category.getName();
                setmealDto.setCategoryName(categoryName);
            }
            return setmealDto;
        }).collect(Collectors.toList());
        setmealDtoPage.setRecords(list);
        return R.success(setmealDtoPage);
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


    /**
     * 新增套餐
     * @param setmealDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto){

        setmealService.saveWithDish(setmealDto);
        return R.success("新增成功");
    }


    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids){
        log.info("{}",ids);
        setmealService.removeWithDish(ids);
        return R.success("删除成功");
    }



    @GetMapping("/list")
    public R<List<Setmeal>> list(@RequestBody Setmeal setmeal){
        LambdaQueryWrapper<Setmeal> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(setmeal.getCategoryId()!=null,Setmeal::getCategoryId,setmeal.getCategoryId());
        lambdaQueryWrapper.eq(setmeal.getStatus()!=null,Setmeal::getStatus,setmeal.getStatus());
        lambdaQueryWrapper.orderByDesc(Setmeal::getUpdateTime);

        List<Setmeal> list = setmealService.list(lambdaQueryWrapper);
        return R.success(list);

    }

}
