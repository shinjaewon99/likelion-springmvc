package com.example.likelion.week11day2.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect // 이 클래스가 관점임을 드러내는 어노테이션
@Component // Bean 객체로 등록
public class LoggingAspect {

    // 컨트롤러 클래스
    // @Before : Advice, 실제로 실행될 코드를 나타냄
    // @Before.value : Pointcut Designator, 어느 조인포인터에서
    // 실행될것인지
//    @Before("this(com.example.likelion.week11day2.controller.AopController)")
    @Before("@annotation(com.example.likelion.week11day2.aspect.LogArguments)")
    public void logParameters(JoinPoint joinPoint) {
//        log.info("hello aop !");

        // Signature : JoinPoint 의 정보를 담은 객체
        Signature methodSignature = joinPoint.getSignature();

        // 메소드 이름 로그
        log.info(methodSignature.getName());

        // 메소드 인자를 확인
        Object[] arguments = joinPoint.getArgs();

        // 인자가 없을경우
        if (arguments.length == 0) {
            log.info("no args");
        }

        for (Object argument : arguments) {
            log.info("[{}]", argument);
        }
    }
}
