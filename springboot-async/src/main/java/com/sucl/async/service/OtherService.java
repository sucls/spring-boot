package com.sucl.async.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sucl
 * @since 2019/10/12
 */
@Service
public class OtherService {
    @Autowired
    private AsyncService asyncService;

    public void run(){
        asyncService.run();
    }
}
