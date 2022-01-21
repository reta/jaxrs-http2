package com.example;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.jaxrs.utils.JAXRSServerFactoryCustomizationUtils;
import org.apache.cxf.transport.http.HttpServerEngineSupport;

import com.example.rest.PeopleResource;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class ServerStarter {
    public static void main( final String[] args ) throws Exception {
        final Bus bus = BusFactory.getDefaultBus();
        bus.setProperty(HttpServerEngineSupport.ENABLE_HTTP2, true);

        final JAXRSServerFactoryBean bean = new JAXRSServerFactoryBean();
        bean.setResourceClasses(PeopleResource.class);
        bean.setResourceProvider(PeopleResource.class, 
            new SingletonResourceProvider(new PeopleResource()));
        bean.setAddress("http://localhost:19091/services");
        bean.setProvider(new JacksonJsonProvider());
        bean.setBus(bus);
        
        JAXRSServerFactoryCustomizationUtils.customize(bean);
        Server server = bean.create();
        server.start();
    }
}