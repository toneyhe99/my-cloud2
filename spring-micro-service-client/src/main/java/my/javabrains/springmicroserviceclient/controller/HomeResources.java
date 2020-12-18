package my.javabrains.springmicroserviceclient.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * after start, should print out in console:
 *       name: catalog--Cloud--QA; desc: QA welcome to catalog--Cloud--QA
 * should show loaded properties if call uri :  "localhost:8088/properties/show",
 */
@Data
@RefreshScope
@RestController
@RequestMapping("/properties")
public class HomeResources {
    @Value("${app.name}")
    private String appName;
    @Value("${app.desc}")
    private String appDesc;

    @PostConstruct
    public void showProperty(){
        System.out.println("\n\n name: "+appName+"; desc: "+appDesc);
    }

    @GetMapping("/show")
    public String showLoadedProperteis(){
        return "Properties loaded:  app.name="+appName+", app.desc="+appDesc;
    }
}