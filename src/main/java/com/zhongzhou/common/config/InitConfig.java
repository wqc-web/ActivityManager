package com.zhongzhou.common.config;

import com.zhongzhou.api.service.impl.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @ClassName InitConfig
 * @Description 系统初始化
 * @Date 2020/6/28 13:59
 * @Author wj
 */
@Component
@Slf4j
public class InitConfig implements ApplicationRunner {
    @Resource
    private DepartmentServiceImpl departmentService;
    @Resource
    private RoleServiceImpl roleService;
    @Resource
    private PermissionServiceImpl permissionService;
    @Resource
    private DictionaryTypeServiceImpl dictionaryTypeService;
    @Resource
    private AttachmentServiceImpl attachmentService;

    @PostConstruct
    public void construct() {
        log.info("系统启动中............");
    }

    @PreDestroy
    public void dostory() {
        log.info("系统关闭中............");
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("**************初始化部门列表**************");
        departmentService.initList();
        log.info("**************初始化角色列表**************");
        roleService.initList();
        log.info("**************初始化权限菜单列表**************");
        permissionService.initList();
        log.info("**************初始化权限菜单图标列表**************");
        attachmentService.initListForMenu();
        log.info("**************初始化数据字典类型列表**************");
        dictionaryTypeService.initList();
        log.info("**************系统初始化完成**************");
    }
}
