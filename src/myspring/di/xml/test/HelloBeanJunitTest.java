package myspring.di.xml.test;

import static org.junit.Assert.*; // Assert 밑에 있는 모든 static 메소드 import 하겠다.

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import myspring.di.xml.Hello;
import myspring.di.xml.Printer;

public class HelloBeanJunitTest {
	BeanFactory factory;
	
	// 테스트 전 먼저 수행
	@Before
	public void init() { // 생성 코드를 테스트 전으로 빼주면 더 효율적
		// 1. Spring Bean Container 생성
		factory = new GenericXmlApplicationContext("config/applicationContext.xml");
	}
	
	/**
		<TestCase 메소드 선언 시 규칙>
		2. @Test 어노테이션 반드시 선언
		3. 메소드의 접근 제한자는 반드시 `public void`
		4. 메소드 이름은 마음대로
	 */
	
	@Test
	public void hello() {
		// 1. Spring Bean Container 생성
		// classpath : config/applicationContext.xml
//		factory = new GenericXmlApplicationContext("config/applicationContext.xml"); // Bean 설정 파일 지정해주기
		
		// 2. container에게 Bean을 요청
		Hello hello = (Hello)factory.getBean("hello"); // id 값
		Hello hello2 = factory.getBean("hello", Hello.class); // id와 같이 인자쓰는 방법을 더 많이 사용
		System.out.println(hello == hello2); // true
		
		// 2.1 Assert.assertSame() 메소드를 사용하여 주소 비교 (syso 안하는 방법)
//		Assert.assertSame(hello, hello2); // 2개의 주소 비교 - 주소가 같으면 테스트 성공(연두색 막대기)
		// static import 하면 클래스 이름 안써도 됨
		assertSame(hello, hello2);
		
		// 2.2 Hello.java에 `스프링`이 잘 주입되었나 확인하기 위해 Hello.java 의 sayHello() 메소드를 호출해본다.
		// Assert.assertEquals() 메소드 사용해서 값 비교
		assertEquals("Hello 스프링", hello.sayHello());
		hello.print();
		
		// 3. container에서 StringPrinter Bean을 요청
		Printer printer = factory.getBean("sPrinter", Printer.class); // class와 타입 모두 상위로 받음(인터페이스 및 부모)
		assertEquals("Hello 스프링", printer.toString());
	}

}
