package com.zhongzhou.common.base;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: BaseEntity
 * @Description: 实体基类
 * @Author: wj
 * @Date: 2020-06-19 11:13:39
 **/
public class BaseEntity<T extends BaseEntity<?>> extends Model<T> {
    private static final long serialVersionUID = 3845395217352252644L;

    /**
     * 设置主键
     */
    @Override
    protected Serializable pkVal() {
        Long value = null;
        List<Field> list = Arrays.asList(this.getClass().getDeclaredFields());
        for (int i = 0; i < list.size(); i++) {
            Field field = list.get(i);
            //是否使用TableId注解
            if (field.isAnnotationPresent(TableId.class)) {
                //System.out.println("实体类存在" + list.size() + "个变量，字段名" + field.getName() + "有" + field.getDeclaredAnnotations().length + "个注解（包括tableId）");
                try {
                    String id = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                    // 父类对象调用子类方法(反射原理)
                    Method method = this.getClass().getMethod("get" + id);
                    Object o = method.invoke(this);
                    value = Long.valueOf(o.toString());
                } catch (Exception e) {
                    //System.out.println("pkval()有异常");
                }
            }
        }
        return value;
    }
}
