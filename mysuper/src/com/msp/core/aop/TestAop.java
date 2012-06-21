package com.msp.core.aop;

import java.util.List;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAop {
	
	/**
	 * 
	 */
	@Pointcut(value="execution(* com.msp..dao.*DAOImpl.*(..))")
	public void firePoint(){
		System.out.println("######in TestAop's testFirst()...");
	}
	
	@Before(value="firePoint()")
	public void doBefore(){
		System.out.println("#################doBefore...####################");
	}
	
	@AfterReturning(value="firePoint()", returning="retVal")
	public void doAfterReturning(Object retVal){
		System.out.println("#################doAfterReturning...####################");
	}
	
}
