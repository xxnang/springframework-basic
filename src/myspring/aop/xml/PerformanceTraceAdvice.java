package myspring.aop.xml;
import org.aspectj.lang.ProceedingJoinPoint;

// 어드바이스타입 : Around
public class PerformanceTraceAdvice {
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
		// 타겟 메소드의 signature 정보
//		String signatureString = joinPoint.getSignature().toShortString();	
		String targetMethodName = joinPoint.getSignature().getName();;		

		System.out.println(targetMethodName + "시작");
		// 타겟 메소드의 Argument 정보
		for(Object arg : joinPoint.getArgs()) {
			System.out.println(targetMethodName + " 의 아규먼트 : " + arg);
		}
		
		// 타겟의 메소드가 호출되기 전의 시간 
		long start = System.currentTimeMillis();		
		try {
			//new Object[] {new String("dooly")}
			// intercepted 된 타켓의 메소드를 반드시 호출해줘야 한다.
			// 타겟의 메소드 호출
			Object result = joinPoint.proceed();
			return result;
		} finally {
			// 타켓의 메소드가 호출된 후의 시간
			long finish = System.currentTimeMillis();
			
			System.out.println(targetMethodName + " 종료");
			System.out.println(targetMethodName + " 실행 시간 : " + 
			   (finish - start) + " ms");
		}
	}
}
