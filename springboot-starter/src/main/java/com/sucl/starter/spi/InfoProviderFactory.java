package com.sucl.starter.spi;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * SPI实现
 * @author sucl
 * @since 2019/8/19
 */
public class InfoProviderFactory {

    public List<InfoProvider> getInfoProviders(){
        ServiceLoader<InfoProvider> serviceLoader = ServiceLoader.load(InfoProvider.class);
        List<InfoProvider> infoProviders = new ArrayList<>();
        serviceLoader.forEach(c->infoProviders.add(c));
        return infoProviders;
    }
}
