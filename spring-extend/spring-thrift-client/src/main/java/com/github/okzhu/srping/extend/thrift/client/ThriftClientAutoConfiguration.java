package com.github.okzhu.srping.extend.thrift.client;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThriftClientAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ThriftClientBeanScannerConfigurer thriftClientBeanScannerConfigurer() {
        return new ThriftClientBeanScannerConfigurer();
    }

}
