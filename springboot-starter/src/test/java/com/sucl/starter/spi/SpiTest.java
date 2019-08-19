package com.sucl.starter.spi;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author sucl
 * @since 2019/8/19
 */
public class SpiTest {

    @Test
    public void infoProviderFactory(){
        InfoProviderFactory infoProviderFactory = new InfoProviderFactory();
        List<InfoProvider> infoProviders = infoProviderFactory.getInfoProviders();
        Assert.assertEquals("InfoProvider test error",2,infoProviders.size());
    }

}
