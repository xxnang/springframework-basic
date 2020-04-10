package myspring.di.annotation;

import org.springframework.stereotype.Component;

// <bean id="stringPrinter" class=".." />
// annotation에 따로 아이디 값 안주면 클래스명의 소문자 시작이 아이디명이 된다.
@Component
public class StringPrinter implements Printer {
	private StringBuffer buffer = new StringBuffer();

	public void print(String message) {
		this.buffer.append(message);
	}

	public String toString() {
		return this.buffer.toString();
	}
}
