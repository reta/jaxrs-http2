Apache CXF, Jetty + HTTP/2 and Project Reactor
==============

 - Prerequisite: JDK 17
         
        https://adoptium.net/archive.html?variant=openjdk17&jvmVariant=hotspot
 
 - Build: 
  
        mvn clean package

 - HTTP/2 over clear text (h2c):
     - Run 

           java -jar target/jaxrs-standalone-jetty-http2-0.0.1-SNAPSHOT-h2c.jar

     - Call the API using HTTP/2:

           curl http://localhost:19091/services/people --http2

           curl http://localhost:19091/services/people --http2-prior-knowledge

 - HTTP/2 over TLS (h2):
     - Run 

           java -jar target/jaxrs-standalone-jetty-http2-0.0.1-SNAPSHOT-h2.jar

     - Call the API using HTTP/2:

           curl https://localhost:19091/services/people --http2 -k

  - References: 
    
        https://cxf.apache.org/docs/http2-support.html
        https://cxf.apache.org/docs/jax-rs-project-reactor-support.html
        https://cxf.apache.org/docs/springboot.html