package myspring.di.annotation.config.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import myspring.di.annotation.Hello;
import myspring.di.annotation.config.AnnotatedHelloBeanConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AnnotatedHelloBeanConfig.class, loader = AnnotationConfigContextLoader.class) // xml 사용할 때와의 차이점
public class HelloConfigTest {
	
	@Autowired
	Hello hello;
	
	@Test
	public void hello() {
		System.out.println(hello.sayHello());
	}
}
