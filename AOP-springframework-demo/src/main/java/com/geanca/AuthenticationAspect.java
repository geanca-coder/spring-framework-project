package com.geanca;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthenticationAspect {
    //Point cuts

    @Pointcut("within(com.geanca..*)")
    public void authenticatingPointCut(){

    }
    @Pointcut("within(com.geanca..*)")
    public void authorizationPointCut(){

    }

    @Before("authenticatingPointCut() && authorizationPointCut()")
    public void authentication(){
        System.out.println("Authenticating the request");
    }
}
