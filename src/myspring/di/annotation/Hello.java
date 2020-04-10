package myspring.di.annotation;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("helloA")
public class Hello {

// 1. setter injection 방법
// <property name="name" value="스프링" />
//	@Value("어노테이션")
	
// 3. @Resource 사용 방법
//	@Value("어노테이션")
	
// 4. .properties 사용 방법
	@Value("${name}")
	String name;

// 1. setter injection 방법
	@Autowired
	@Qualifier("stringPrinter")
	Printer printer;
// <property name="printer" ref="stringPrinter" />
	
// 3. @Resource 사용 방법
//	@Resource(name = "stringPrinter")
	
// 4. .properties 사용방법
//	@Resource(name = "${myprinter}")
//	Printer printer;
	List<String> names;
	Map<String, Integer> ages;

	public Hello() {
		System.out.println("Hello Default Constructor called..");
	}

// 2. construction inject 방법
//	@Autowired
//	public Hello(@Value("어노테이션")String name, @Qualifier("stringPrinter")Printer printer) {
	public Hello(String name, Printer printer) {	
		System.out.println("OverLoading Hello Constructor called..");
		this.name = name;
		this.printer = printer;
	}

	public List<String> getNames() {
		return this.names;
	}

// Annotation 방식에서는 Setter 필요 없음
//	public void setNames(List<String> list) {
//		this.names = list;
//	}
//
//	public void setName(String name) {
//		System.out.println("Hello setName() called.." + name);
//		this.name = name;
//	}

	public Map<String, Integer> getAges() {
		return ages;
	}

	public void setAges(Map<String, Integer> ages) {
		this.ages = ages;
	}

	public void setPrinter(Printer printer) {
		// 인자로 들어온 Printer 객체의 클래스 이름을 찍어보기(인터페이스니까 어떤 클래스에 의존하는지 알기 위해)
		System.out.println("Hello setPrinter() called.." + printer.getClass().getName());
		this.printer = printer;
	}

	public String sayHello() {
		return "Hello " + name;
	}

	public void print() {
		this.printer.print(sayHello());
	}

}
