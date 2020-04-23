package myspring.aop.annot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	
	protected static final Logger logger = LogManager.getLogger();
	
	// 전처리 Advice
    @Before("execution(public * myspring..*(..))")
	public void before(JoinPoint joinPoint) {
		String signatureString = joinPoint.getSignature().getName();	
		if( logger.isDebugEnabled() ) {
			logger.debug(">>>> @Before [ " + signatureString + " ] 메소드 실행 전처리 수행");
		}
		for (Object arg : joinPoint.getArgs()) {
			logger.debug("@Before [ " + signatureString + " ] 아규먼트" + arg);			
		}		
	}
    
    // 후처리 Advice : target의 메소드가 정장 종료 되었을 때만 적용됨
    @AfterReturning(pointcut="execution(public * myspring.user.service.*.*(..))", returning="ret")
	public void afterReturning(JoinPoint joinPoint, Object ret) {
		String signatureString = joinPoint.getSignature().getName();		
		logger.debug("@AfterReturing [ " + signatureString + " ] 메소드 실행 후처리 수행");
		logger.debug("@AfterReturing [ " + signatureString + " ] 리턴값="+ ret);

	}
    
    // 후처리 Advice : target의 메소드가 에러가 발생 되었을때만 적용됨
    @AfterThrowing(pointcut="execution(* *..UserService*.*(..))", 
    		throwing="ex")
	public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
		String signatureString = joinPoint.getSignature().getName();	
		logger.debug("@AfterThrowing [ " + signatureString + " ] 메소드 실행 중 예외 발생");
		logger.debug("@AfterThrowing [ " + signatureString + " ] 예외=" + ex.getMessage());
	}
    
    // 후처리 Advice : target의 메소드의 정상/에러 에 관계없이 무조건 적용됨
    @After("execution(* *..*.*User(..))")
	public void afterFinally(JoinPoint joinPoint) {
		String signatureString = joinPoint.getSignature().getName();
		logger.debug("@After [ " + signatureString + " ] 메소드 실행 완료");
	}
}
