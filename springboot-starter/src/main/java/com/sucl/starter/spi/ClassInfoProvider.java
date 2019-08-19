package com.sucl.starter.spi;

import com.sucl.starter.model.Info;

/**
 * @author sucl
 * @since 2019/8/19
 */
public class ClassInfoProvider implements InfoProvider{
    @Override
    public Info getInfo() {
        Info info = new Info();
        info.setId(this.hashCode()+"");
        info.setName(this.getClass().getName());
        info.setContent(this.toString());
        return info;
    }
}
