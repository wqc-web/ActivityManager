package com.zhongzhou.common.vo;

import lombok.Data;

import java.util.Objects;
import java.util.TreeSet;

/**
 * @ClassName MenuVO
 * @Description 后台管理菜单VO
 * @Date 2020/6/28 13:57
 * @Author wj
 */
@Data
public class MenuVO implements Comparable {
    /**
     * 初始化serialVersionUID
     */
    private static final long serialVersionUID = -286710733890435673L;

    /**
     * 菜单名称（与视图的文件夹名称和路由路径对应）
     */
    private String name;

    /**
     * 菜单标题
     */
    private String title;

    /**
     * 菜单图标样式
     */
    private String icon;

    /**
     * 菜单图标url
     */
    private String iconUrl;

    /**
     * 自定义菜单路由地址，默认按照 name 解析。一旦设置，将优先按照 jump 设定的路由跳转
     */
    private String jump;

    /**
     * 排序
     */
    private Integer seq;

    /**
     * 是否默认展子菜单
     */
    private Boolean spread;

    /**
     * 子菜单列表
     */
    private TreeSet<MenuVO> list;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuVO menuVO = (MenuVO) o;
        return Objects.equals(name, menuVO.name) &&
                Objects.equals(title, menuVO.title) &&
                Objects.equals(jump, menuVO.jump);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, title, jump);
    }

    @Override
    public int compareTo(Object o) {
        MenuVO menuVO = (MenuVO) o;
        return menuVO.getSeq().compareTo(this.seq);
    }
}
