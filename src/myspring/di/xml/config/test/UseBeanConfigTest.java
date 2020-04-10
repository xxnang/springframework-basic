package myspring.di.xml.config.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import myspring.di.xml.Hello;
import myspring.di.xml.config.UseBeanHelloConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UseBeanHelloConfig.class, loader = AnnotationConfigContextLoader.class)
public class UseBeanConfigTest {
	
	@Autowired
	Hello hello;
	
	@Test
	public void hello() {
		System.out.println(hello.sayHello());
	}

}
