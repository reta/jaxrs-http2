Apache CXF, Jetty + HTTP/2, Spring Boot and Project Reactor
==============

 - Prerequisite: JDK 17
         
        https://adoptium.net/archive.html?variant=openjdk17&jvmVariant=hotspot
 
 - Build: 
  
        mvn package

 - HTTP/2 over clear text (h2c):
     - Run 

           java -jar target/jaxrs-spring-boot-jetty-http2-0.0.1-SNAPSHOT.jar

     - Call the API using HTTP/2:

           curl http://localhost:19091/services/people --http2

           curl http://localhost:19091/services/people --http2-prior-knowledge

 - HTTP/2 over TLS (h2):
     - Run 

           java -jar target/jaxrs-spring-boot-jetty-http2-0.0.1-SNAPSHOT.jar --spring.profiles.active=h2

     - Call the API using HTTP/2:

           curl https://localhost:19091/services/people --http2 -k

 - References: 
    
        https://cxf.apache.org/docs/http2-support.html
        https://cxf.apache.org/docs/jax-rs-project-reactor-support.html
        https://docs.spring.io/spring-boot/docs/2.6.x/reference/html/howto.html#howto.webserver.configure-http2