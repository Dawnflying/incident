package com.frankxiaofei.incident_managment;


import com.frankxiaofei.incident_managment.exceptions.BizException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExceptionHandlingAspect {

    @Pointcut("execution(* com.frankxiaofei.incident_managment.controller.IncidentController.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (BizException e) {
            log.error("业务异常", e);
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        } catch (Exception e) {
            log.error("系统异常", e);
            throw new RuntimeException(e);
        }
    }
}
