package org.alham.alhamfirst.aspect

import org.alham.alhamfirst.common.logger
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class LoggingAspect {

    val log = logger()

    @Around("execution(* org.alham.alhamfirst.controller..*(..))")
    fun controllerLogging(joinPoint: ProceedingJoinPoint): Any? {

        val methodName = joinPoint.signature.name
        val args = joinPoint.args.joinToString()
        val start = System.currentTimeMillis()
        log.info("[START-CONTROLLER] Method: {} | Args: {}", methodName, args)

        return try{
            val result = joinPoint.proceed()
            val duration = System.currentTimeMillis() - start
            log.info("[END-CONTROLLER] Method: {} | Result: {} | Duration: {} ms",methodName, result, duration)
            result
        } catch (e: Exception){
            log.error("[ERROR-CONTROLLER] Method: {} | Exception: {}", methodName, e.message)
            throw e
        }
    }

    @Around("execution(* org.alham.alhamfirst.service..*(..))")
    fun serviceLogging(joinPoint: ProceedingJoinPoint): Any?{

        val methodName = joinPoint.signature.name
        val args = joinPoint.args.joinToString()
        val start = System.currentTimeMillis()
        log.info("[START-SERVICE] Method: {} | Args: {}", methodName, args)

        return try{
            val result = joinPoint.proceed()
            val duration = System.currentTimeMillis() - start
            log.info("[END-SERVICE] Method: {} | Result: {} | Duration: {} ms", methodName, result, duration)
            result
        } catch (e: Exception){
            log.error("[ERROR-SERVICE] Method: {} | Exception: {}", methodName, e.message)
            throw e
        }
    }


}