package com.zhongzhou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan("com.zhongzhou.*.mapper")
public class ExampleApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ExampleApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);

//        /******************************生成JApiDocs*****************************/
//        DocsConfig config = new DocsConfig();
//        //项目根目录
//        config.setProjectPath("E:/workspace/zhongzhou/example");
//        //项目名称
//        config.setProjectName("example");
//        //声明该API的版本
//        config.setApiVersion("V1.0");
//        //生成API 文档所在目录
//        config.setDocsPath("E:/api/zhongzhou/example");
//        //配置自动生成
//        config.setAutoGenerate(Boolean.TRUE);
//        //执行生成文档
//        Docs.buildHtmlDocs(config);
    }

}
