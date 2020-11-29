package com.github.wetest.service.annoation;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

/**
 * @author JZWen
 * @date 2020/11/29
 */
@Configurable
@EnableLoadTimeWeaving
@EnableSpringConfigured
public class PojoConfig {

    @Bean
    public PojoBean getPojoBean() {
        return new PojoBean();
    }

}
