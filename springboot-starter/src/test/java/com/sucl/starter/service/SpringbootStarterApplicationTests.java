package com.sucl.starter.service;

import com.sucl.simplestarter.service.SimpleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootStarterApplicationTests {
    @Autowired
    private SimpleService simpleService;

    @Test
    public void starter(){
        String info = simpleService.print("hello starter");
        Assert.assertEquals("error","hello starter",info);
    }

}
