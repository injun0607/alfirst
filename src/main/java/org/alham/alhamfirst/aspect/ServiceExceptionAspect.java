package org.alham.alhamfirst.aspect;

import lombok.extern.slf4j.Slf4j;
import org.alham.alhamfirst.common.error.MariaDBCustomError;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ServiceExceptionAspect {

    //투두 서비스는 모두 MariadbCustomError를 반환
    @Around("execution(* org.alham.alhamfirst.service.orchestrator.todo.*.*(..))")
    public Object handleTodoServiceException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            // 메서드 실행
            log.debug("ServiceExceptionAspect handleTodoServiceException");
            String className = joinPoint.getSignature().getDeclaringType().getName();
            String methodName = joinPoint.getSignature().getName();
            log.info("className = {},mehodName = {}", className, methodName);

            return joinPoint.proceed();
        } catch (Exception ex) {
            // 커스텀 예외로 변환
            throw new MariaDBCustomError(ex.getMessage());
        }
    }

    //스탯 서비스는 모두 MongoCustomError를 반환
    @Around("execution(* org.alham.alhamfirst.service.orchestrator.stat.*.*(..))")
    public Object handleStatServiceException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            // 메서드 실행
            log.debug("ServiceExceptionAspect handleStatServiceException");
            return joinPoint.proceed();
        } catch (Exception ex) {
            // 커스텀 예외로 변환
            throw new MariaDBCustomError(ex.getMessage());
        }
    }
}
