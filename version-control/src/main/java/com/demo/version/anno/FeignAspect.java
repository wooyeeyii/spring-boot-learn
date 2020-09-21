package com.demo.version.anno;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
//@Component
public class FeignAspect {

    @Pointcut(value = "execution(public * com.demo.version.web.*Controller.*(..))")
    public void systemClient() {
    }

    @Before("systemClient()")
    public void beforeInvoke() {
        System.out.println("before...");
    }

    @After("systemClient()")
    public void afterInvoke() {
        System.out.println("after...");
    }

    @Around("systemClient()")
    public Object roundInvoke(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around before....");
        Object res = pjp.proceed();
        if (res instanceof String) {
            System.out.print("around during: res = ");
            System.out.println((String) res);
//            throw new Exception("illlllllll...");
            res = "modified result in around.";
        }
        System.out.println("around after....");
        return res;
    }

    @AfterReturning(value = "systemClient()", returning = "returnValue")
    public void afterReturningInvoke(JoinPoint jp, Object returnValue) throws Throwable {
        System.out.println("afterReturning...");
        if (returnValue instanceof String) {
            returnValue = "modified result.";
        }
    }

}
