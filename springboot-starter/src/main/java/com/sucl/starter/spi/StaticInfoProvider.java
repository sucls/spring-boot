package com.sucl.starter.spi;

import com.sucl.starter.model.Info;

/**
 * @author sucl
 * @since 2019/8/19
 */
public class StaticInfoProvider implements InfoProvider{

    @Override
    public Info getInfo() {
        Info info = new Info();
        info.setId("1");
        info.setName("spi");
        info.setContent("spi 示例");
        return info;
    }
}
