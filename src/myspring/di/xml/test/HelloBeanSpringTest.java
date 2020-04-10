package myspring.di.xml.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import myspring.di.xml.Hello;

@RunWith(SpringJUnit4ClassRunner.class) // 자동 Bean 컨테이너 생성
@ContextConfiguration(locations="classpath:config/applicationContext.xml") // xml 설정파일 위치 지정
public class HelloBeanSpringTest {
	// getBean 메소드 사용 대신에 @Autowired
	@Autowired
	@Qualifier("helloC") // 변수명이 아이디랑 달라도 이걸 먼저 처리해서 이 아이디의 bean을 찾아감
	Hello hello;
	// 아래와 같음
	// Hello hello = (Hello)factory.getBean("hello");
	
	@Test
	public void helloBean() {
		System.out.println(hello.sayHello());
		hello.print(); // ConsolePrinter에 있는 것이 찍히게 됨 (현재 설정파일에서 cPrinter를 참조하고 있으니까)
		
		List<String> names = hello.getNames();
		// List니까 foreach 이용
		for (String name : names) {
			System.out.println(name);
		}
		
		Map<String, Integer> ages = hello.getAges(); //블럭 지정 후 Alt + Shift + L : 타입과 변수명 자동 생성
		System.out.println("------- keySet() -------");
		// 1. Map의 keySet() 사용
		// ages.keySet()은 Set<String> 타입인 것
		for (String key : ages.keySet()) { // key 값을 Set에다가 하나씩 넣어줌 왜냐면 중복되면 안되니까
			System.out.println(key + " : " + ages.get(key));
		}
		
		System.out.println("------- entrySet() -------");
		// 2. Map의 entrySet() 사용
		// ages.entrySet()은 Map.Entry<String, Integer> 타입인 것
		for(Map.Entry entry : ages.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
}
