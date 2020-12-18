package my.javabrains.springcloudconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/** Tested OK in window 10 for spring boot 2.3.7.RELEASE, not work for 2.4.1 version!! ****
 Make Microservice configuration CONSISTENCE --> call the same config server (link to a GIT repository) to load properties!

     1). To make a Spring Boot application act as a config server we do the followings:
             add "spring-cloud-config-server" dependencies to pom.xml
             add @EnableConfigServer annotation on main class
             set config server git repository uri property (local or remote):
                     spring.cloud.config.server.git.uri = file:///C:/tmp/my-git-repo
                     #spring.cloud.config.server.git.uri = https://github.com/toneyhe99/my-cloud2.git
             Finally put your config file into local C:/tmp/my-git-repo folder (or into remote repository's ROOT FOLDER!):
                     application.properties
                     catalog-service-dev.properties
                     catalog-service-qa.properties
                     catalog-service.properties

             Now start the server and goto test the syntax (label is repository branch name, optional, default to master branch):
                     http://localhost:8888/<file-name>/<profile-name>/<label>
             Sample for this url:
                     http://localhost:8888/catalog-service/qa
             this will load the general default file "application.properties" first,
             then load default file "catalog-service.properties"
             finally load profile file "catalog-service-qa.properties"
             The later loaded file will overwrite pre loaded property values.

         more see--> https://docs.spring.io/spring-cloud-config/docs/2.2.5.RELEASE/reference/html/  --> "Config Server" section
         or --> https://soshace.com/centralize-the-configuration-of-services-with-spring-cloud-config/

     2). The spring-cloud-config-client setting (to load properties from cloud server that point to a GIT repository):
             a). add "spring-cloud-starter-config" into pom.xml
             b). set following properties (the uri is mandatory, other optional)
                     spring.cloud.config.uri=http://localhost:8888
                     spring.application.name=catalog-service    //default to "application" property file
                     spring.cloud.config.profile=qa             //default to "default" profile
                     spring.cloud.config.label=master           //default to master branch

 Make Microservice configuration dynamically refresh when config server data changes without restart
     1). add "spring-boot-starter-actuator" dependency
         add property "management.endpoints.web.exposure.include=health, info, refresh" to enable actuator's these endpoint.
     2). add @RefreshScope annotation on the Component that need dynamically refresh its external loaded properties (like @Value fields)
    Your are done! To refresh after started Microservice, just call this microservice uri:
        "http://localhost:port/actuator/refresh"  then it will refresh the @RefreshScope noted Component loaded properties at once!

    Note: you must commit your property file change and PUSH to remote repository, as the "refresh" action only load from
          remote repository and auto pull into local, even you linked locally, so your local change WILL BE overridden!

 Note: this window 10 not support add branch in config server, only master branch allow, or it will auto switch to master during deployment!
 */
@SpringBootApplication
@EnableConfigServer
public class SpringCloudConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigServerApplication.class, args);
    }

}