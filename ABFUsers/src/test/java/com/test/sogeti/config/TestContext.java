package com.test.sogeti.config;


import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.sogeti.service.BandService;
import com.sogeti.serviceImpl.BandServiceImpl;


/**
 * @author Mohan
 */
@Configuration
@ImportResource({"classpath*:jpaConext.xml"})
public class TestContext {

    private static final String MESSAGE_SOURCE_BASE_NAME = "i18n/messages";

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        messageSource.setBasename(MESSAGE_SOURCE_BASE_NAME);
        messageSource.setUseCodeAsDefaultMessage(true);

        return messageSource;
    }

   /* @Bean
    public BandService todoService() {
        return Mockito.mock(BandService.class);
    } 
    
    @Bean
    public ContractManager contractManager() {
        return Mockito.mock(ContractManager.class);
    }*/
    
    @Bean
    public BandService bandService() {
        return Mockito.mock(BandServiceImpl.class);
    }
}
