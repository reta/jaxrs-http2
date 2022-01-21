package com.example;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.utils.JAXRSServerFactoryCustomizationUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.rest.PeopleResource;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@Configuration
public class AppConfig {
    @Bean
    public Server server(Bus bus, PeopleResource service) {
        JAXRSServerFactoryBean bean = new JAXRSServerFactoryBean();
        bean.setBus(bus);
        bean.setServiceBean(service);
        bean.setProvider(new JacksonJsonProvider());
        bean.setAddress("/");
        JAXRSServerFactoryCustomizationUtils.customize(bean);
        return bean.create();
    }
}
