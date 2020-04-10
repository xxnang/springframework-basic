package myspring.di.xml;

import java.util.List;
import java.util.Map;

public class Hello {
	String name;
	Printer printer;
	List<String> names;
	Map<String, Integer> ages;

	public Hello() {
		System.out.println("Hello Default Constructor called..");
	}

	public Hello(String name, Printer printer) {
		System.out.println("OverLoading Hello Constructor called..");
		this.name = name;
		this.printer = printer;
	}

	public List<String> getNames() {
		return this.names;
	}

	public void setNames(List<String> list) {
		this.names = list;
	}

	public void setName(String name) {
		System.out.println("Hello setName() called.." + name);
		this.name = name;
	}

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
