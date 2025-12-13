package com.ruoyi.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestTask {

    private final StuSys stuSys;

    public TestTask(StuSys sys){
        this.stuSys = sys;

    }
}