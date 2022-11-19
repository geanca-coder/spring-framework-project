package com.geanca;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.geanca.ShoppingCart.checkout(..))")
    public void beforelogger(JoinPoint jp){
//        System.out.println( jp.getSignature());
        System.out.println(jp.getArgs()[0].toString());

        System.out.println("Loggers");
    }
    @After("execution(* *.*.*.checkout(..))")
    public void afterLogger(){
        System.out.println("After logger");
    }

    @Pointcut("execution(* com.geanca.ShoppingCart.quantity(..))")
    public void afterReturningPointCut(){

    }
    @AfterReturning(pointcut = "afterReturningPointCut()", returning = "returnVal")
    public void afterReturning(String returnVal){
        System.out.println("After returning: "+ returnVal);
    }
}
