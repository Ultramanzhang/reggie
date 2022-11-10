package com.zhegu.reggie.dto;


import com.zhegu.reggie.entity.Setmeal;
import com.zhegu.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
