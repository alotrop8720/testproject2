package com.noirix.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger log = Logger.getLogger(LoggingAspect.class);

//    @Before("aroundRepositoryPointcut()")
//    public void logBefore(JoinPoint joinPoint) {
//        log.info("Method " + joinPoint.getSignature().getName() + " start");
//    }
//
//    @AfterReturning(pointcut = "aroundRepositoryPointcut()")
//    public void doAccessCheck(JoinPoint joinPoint) {
//        log.info("Method " + joinPoint.getSignature().getName() + " finished");
//    }

    @Pointcut("execution(* com.noirix.repository.impl.UserRepositoryJdbcTemplateImpl.*(..))")
    public void aroundRepositoryPointcut() {
    }

    static Map<String, Integer> countCallMethod = new HashMap<>();
    private long start;
    private long finish;
    @Around("aroundRepositoryPointcut()")
    public Object logAroundMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        incCallMethod(joinPoint);
        log.info("Method " + joinPoint.getSignature().getName() + " start");
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long finish = System.currentTimeMillis();
        log.info("Method " + joinPoint.getSignature().getName() + " finished. " +
                "Method's process is " + (finish - start));
        return proceed;
    }

    private void incCallMethod(ProceedingJoinPoint joinPoint) throws Throwable{
        //перегруженные методы
        String nameMethod = joinPoint.getSignature().getName();
        if (countCallMethod.entrySet().contains(nameMethod))
            countCallMethod.put(nameMethod, countCallMethod.get(nameMethod) + 1);
        else countCallMethod.put(nameMethod, 1);
    }

    public static void getStatistic(){
        log.info(countCallMethod);
    }

}
