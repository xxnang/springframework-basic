package myspring.di.annotation.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import myspring.di.annotation.Hello;
import myspring.di.annotation.Printer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
public class HelloAnntatedBeanSpringTest {
	
	@Autowired
	Hello hello;
	
	@Autowired // 여기는 @Qualifier가 있어야 함
	@Qualifier("stringPrinter")
	Printer printer;
	
	@Test
	public void hello() {
		System.out.println("뭐가 나오나? " + hello.sayHello());
		hello.print();
		System.out.println(printer.toString());
	}
}
