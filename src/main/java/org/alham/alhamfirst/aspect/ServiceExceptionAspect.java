package org.alham.alhamfirst.aspect;

import org.alham.alhamfirst.common.error.MariaDBCustomError;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceExceptionAspect {

    //투두 서비스는 모두 MariadbCustomError를 반환
    @Around("execution(* org.alham.alhamfirst.service.orchestrator.todo.*.*(..))")
    public Object handleServiceException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            // 메서드 실행
            return joinPoint.proceed();
        } catch (Exception ex) { // 특정 예외 감지
            // 커스텀 예외로 변환
            throw new MariaDBCustomError(ex.getMessage());
        }
    }
}
