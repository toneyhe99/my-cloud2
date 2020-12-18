package my.javabrains.springmicroserviceclient;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * after start, should print out in console:
 *       name: catalog--Cloud--QA; desc: QA welcome to catalog--Cloud--QA
 */
@Data
@SpringBootApplication
public class SpringMicroServiceClientApplication {
    @Value("${app.name}")
    private String appName;
    @Value("${app.desc}")
    private String appDesc;

    @PostConstruct
    public void showProperty(){
        System.out.println("\n\n name: "+appName+"; desc: "+appDesc);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringMicroServiceClientApplication.class, args);
    }

}
