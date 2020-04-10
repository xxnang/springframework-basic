package myspring.di.xml.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import myspring.di.xml.ConsolePrinter;
import myspring.di.xml.Hello;
import myspring.di.xml.Printer;
import myspring.di.xml.StringPrinter;

@Configuration
public class UseBeanHelloConfig {

	@Bean
	public Printer stringPrinter() {
		return new StringPrinter();
	}
	
	@Bean
	public Printer consolePrinter() {
		return new ConsolePrinter();
	}
	
	@Bean
	@Scope("singleton") // default가 singleton (지정하고 싶으면 이런식으로 사용)
	public Hello hello() {
		Hello hello = new Hello();
		hello.setName("설정");
		hello.setPrinter(stringPrinter());
		return hello;
	}
}
