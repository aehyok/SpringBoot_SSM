package com.aehyok.blog.aop;

import com.aehyok.blog.util.OperationResult;
import com.aehyok.blog.util.handler.GlobalExceptionHandler;
import eu.bitwalker.useragentutils.UserAgent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


@Aspect
@Component

public class ResponseAop {
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    private final Logger logger = LoggerFactory.getLogger(ResponseAop.class);
    @Autowired
    private GlobalExceptionHandler exceptionHandler;

    //action执行前的日志
    @Before("httpResponse()")
    public void doBefore(JoinPoint joinPoint){
        startTime.set(System.currentTimeMillis());
        //接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        System.out.println(request.getServerName());
        System.out.println(request.getServerPort());
        //记录请求的内容
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));

        logger.info("接口路径：{}" , request.getRequestURL().toString());
        logger.info("浏览器：{}", userAgent.getBrowser().toString());
        logger.info("浏览器版本：{}",userAgent.getBrowserVersion());
        logger.info("操作系统: {}", userAgent.getOperatingSystem().toString());
        logger.info("IP : {}" , request.getRemoteAddr());
        logger.info("请求类型：{}", request.getMethod());
        logger.info("类方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("请求参数 : {} " + Arrays.toString(joinPoint.getArgs()));

    }

    /**
     * 切点
     */
    @Pointcut("execution(public * com.aehyok.blog.controller..*(..))")
    public void httpResponse() {
    }
    /**
     * 环切
     */
    @Around("httpResponse()")
    public Object handlerController(ProceedingJoinPoint proceedingJoinPoint) {
        OperationResult operationResult = new OperationResult();
        try {
            startTime.set(System.currentTimeMillis());
            Object proceed = proceedingJoinPoint.proceed();
            if (proceed instanceof OperationResult) {
                operationResult = (OperationResult) proceed;
            } else {
                operationResult.setData(proceed);
            }
        }  catch (Throwable throwable) {
            // 这里直接调用刚刚我们在handler中编写的方法
           operationResult=exceptionHandler.handlerException(throwable);
        }
        return operationResult;
    }


    //action执行后的日志
    @AfterReturning(returning = "ret" , pointcut = "httpResponse()")
    public void doAfterReturning(Object ret){
        //处理完请求后，返回内容
         logger.info("方法返回值：{}" , ret);
         logger.info("方法执行时间：{}毫秒", (System.currentTimeMillis() - startTime.get()));
    }
}
