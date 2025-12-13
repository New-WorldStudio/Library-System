package com.ruoyi.system;


import org.springframework.stereotype.Service;

@Service
public class TestHJHH {

    private final StuSys stuSys;

    public TestHJHH(StuSys stuSys){
        this.stuSys = stuSys;

        System.out.println("这个对象的值为:" +stuSys.getName() + " " + stuSys.getTest() + "  这是在TestHJHH中被调用");
    }
}