package com.zhongzhou.common.utils;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author wj
 * @ClassName MpGenerator
 * @Description 自动生成代码工具类
 * @date 2020-03-08 18:22:48
 **/
public class MpGenerator {
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        try {
            // 代码生成器
            AutoGenerator mpg = new AutoGenerator();

            // 全局配置
            GlobalConfig gc = new GlobalConfig();
            String projectPath = System.getProperty("user.dir");
            gc.setOutputDir(projectPath + "/src/main/java");
            gc.setAuthor("wqc");
            gc.setOpen(false);
            gc.setSwagger2(true);
            mpg.setGlobalConfig(gc);

            // 数据源配置
            DataSourceConfig dsc = new DataSourceConfig();
            // dsc.setSchemaName("public");
            dsc.setDriverName("com.mysql.jdbc.Driver");
            dsc.setUrl("jdbc:mysql://192.168.0.20:3306/db_activity_manager?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=GMT%2B8");
            dsc.setUsername("root");
            dsc.setPassword("root");
            mpg.setDataSource(dsc);

            // 包配置
            PackageConfig pc = new PackageConfig();
            pc.setModuleName(scanner("模块名"));
            pc.setParent("com.zhongzhou");
            pc.setXml("Mapper.xml");
            mpg.setPackageInfo(pc);

            // 自定义配置
            InjectionConfig cfg = new InjectionConfig() {
                @Override
                public void initMap() {
                    // to do nothing
                }
            };
            List<FileOutConfig> focList = new ArrayList<>();
            focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return projectPath + "/src/main/resources/mapper/"
                            + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }
            });
            cfg.setFileOutConfigList(focList);
            mpg.setCfg(cfg);
            TemplateConfig tc = new TemplateConfig();
            tc.setXml(null);
            tc.setController("/generate/myController.java");
            mpg.setTemplate(tc);

            // 策略配置
            StrategyConfig strategy = new StrategyConfig();
            strategy.setNaming(NamingStrategy.underline_to_camel);
            strategy.setColumnNaming(NamingStrategy.underline_to_camel);
            strategy.setEntityTableFieldAnnotationEnable(true);
            // 自定义实体父类
//            strategy.setSuperEntityClass("com.zhongzhou.common.base.BaseService");
            strategy.setSuperEntityClass(com.zhongzhou.common.base.BaseEntity.class);
            //entity启用lombok风格
            strategy.setEntityLombokModel(true);
            //设置为Restful风格
            strategy.setRestControllerStyle(true);
            // 自定义 controller 父类
//            strategy.setSuperControllerClass("com.zhongzhou.common.base.BaseController");
            strategy.setSuperControllerClass(com.zhongzhou.common.base.BaseController.class);
            // 自定义 service 父类
//            strategy.setSuperServiceClass("com.zhongzhou.common.base.BaseService");
            strategy.setSuperServiceClass(com.zhongzhou.common.base.BaseService.class);
            // 自定义 mapper 父类
            strategy.setSuperMapperClass("com.zhongzhou.common.base.BaseDao");
            strategy.setInclude(scanner("表名"));
//        自定义基础的Entity类，公共字段
//        strategy.setSuperEntityColumns("id");
            // 逻辑删除属性名称
            strategy.setLogicDeleteFieldName("delete_flag");
//        驼峰转连字符
            strategy.setControllerMappingHyphenStyle(true);
//        设置version属性名称
            strategy.setVersionFieldName("version");
            //设置表前缀
            strategy.setTablePrefix("t_");
            mpg.setStrategy(strategy);
            // 切换为 freemarker 模板引擎
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
            mpg.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
