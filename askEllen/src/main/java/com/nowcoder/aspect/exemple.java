package com.nowcoder.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class exemple {
    private static final Logger logger = LoggerFactory.getLogger(exemple.class);

    @Before("execution(* com.nowcoder.controller.Example.*(..))")
    public void beforeMethd(){
        logger.info("before method exemple");
    }
}
