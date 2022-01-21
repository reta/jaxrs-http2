package com.example;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.configuration.jsse.TLSParameterJaxBUtils;
import org.apache.cxf.configuration.jsse.TLSServerParameters;
import org.apache.cxf.configuration.security.KeyManagersType;
import org.apache.cxf.configuration.security.KeyStoreType;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.jaxrs.utils.JAXRSServerFactoryCustomizationUtils;
import org.apache.cxf.transport.http.HttpServerEngineSupport;
import org.apache.cxf.transport.http_jetty.JettyHTTPServerEngineFactory;

import com.example.rest.PeopleResource;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class TlsServerStarter {
    public static void main( final String[] args ) throws Exception {
        final Bus bus = BusFactory.getDefaultBus();
        bus.setProperty(HttpServerEngineSupport.ENABLE_HTTP2, true);
        
        final KeyStoreType keystore = new KeyStoreType();
        keystore.setType("JKS");
        keystore.setPassword("strong-passw0rd-here");
        keystore.setResource("certs/server.jks");
        
        final KeyManagersType kmt = new KeyManagersType();
        kmt.setKeyStore(keystore);
        kmt.setKeyPassword("strong-passw0rd-here");
        
        final TLSServerParameters parameters = new TLSServerParameters();
        parameters.setKeyManagers(TLSParameterJaxBUtils.getKeyManagers(kmt));
        final JettyHTTPServerEngineFactory factory = new JettyHTTPServerEngineFactory(bus);
        factory.setTLSServerParametersForPort("localhost", 19091, parameters);
        
        final JAXRSServerFactoryBean bean = new JAXRSServerFactoryBean();
        bean.setResourceClasses(PeopleResource.class);
        bean.setResourceProvider(PeopleResource.class, 
            new SingletonResourceProvider(new PeopleResource()));
        bean.setAddress("https://localhost:19091/services");
        bean.setProvider(new JacksonJsonProvider());
        bean.setBus(bus);
        
        JAXRSServerFactoryCustomizationUtils.customize(bean);
        Server server = bean.create();
        server.start();
    }
}