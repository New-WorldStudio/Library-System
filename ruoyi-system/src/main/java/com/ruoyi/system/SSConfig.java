package com.ruoyi.system;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SSConfig {


    @Bean
    public StuSys getStuSysBean(){
        StuSys stuSys = new StuSys("1", "001");
        System.out.println(stuSys.getClass().getSimpleName() + "对象已经创建，由Spring来创建管理这个对象");

        return stuSys;
    }
}