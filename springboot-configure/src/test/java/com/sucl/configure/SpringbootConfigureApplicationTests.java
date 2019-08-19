package com.sucl.configure;

import com.sucl.configure.model.Account;
import com.sucl.configure.service.AccountService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootConfigureApplicationTests {
    @Autowired
    private AccountService accountService;

    @Test
    public void contextLoads() {
        Account account = accountService.getAccount("");
        Assert.assertNull("",account);
    }

}
