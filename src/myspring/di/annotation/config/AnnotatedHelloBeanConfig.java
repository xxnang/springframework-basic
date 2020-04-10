package myspring.di.annotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration 
@ComponentScan(basePackages = {"myspring.di.annotation"}) // <context:component-scan /> 과 같음
@PropertySource("classpath:config/values.properties")
public class AnnotatedHelloBeanConfig {

}
