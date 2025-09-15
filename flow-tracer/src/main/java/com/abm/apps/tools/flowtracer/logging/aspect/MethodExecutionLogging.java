package com.abm.apps.tools.flowtracer.logging.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@Aspect
public class MethodExecutionLogging {



  @Around(
      value =
          "execution(* *(..)) && @annotation(com.abm.apps.tools.flowtracer.logging.MethodLogging))")
  public Object log(ProceedingJoinPoint point) throws Throwable {

    CodeSignature methodSignature = (CodeSignature) point.getSignature();
    String[] parameterNames = methodSignature.getParameterNames();
    Object[] parameterValues = point.getArgs();

    long start = System.currentTimeMillis();
    Object result = null;
    Throwable exception = null;

    try {
      result = point.proceed();
    } catch (Throwable t) {
      exception = t;
    }

    log.debug(
        "className=[{}], methodName=[{}], parameterNames=[{}], arguments=[{}], exceptionThrown=[{}], executionTime=[{}ms]",
        new Object[] {
          MethodSignature.class.cast(point.getSignature()).getDeclaringTypeName(),
          MethodSignature.class.cast(point.getSignature()).getMethod().getName(),
                toList(parameterNames),
                toList(parameterValues),
          exception != null,
          (System.currentTimeMillis() - start)
        });

    if (null != exception) {
      throw exception;
    }
    return result;
  }

  private List<String> toList(Object[] parameterNames) {
    List<String> result = new ArrayList<>();
    if (parameterNames != null) {
      for (Object s : parameterNames) {
        result.add(s.toString());
      }
    }
    return result;
  }

  private List<String> getParameterNames(String[] parameterNames) {
    List<String> result = new ArrayList<>();
    if (parameterNames != null) {
      for (String s : parameterNames) {
        result.add(s);
      }
    }
    return result;
  }

  private List<String> getMethodArguments(Object[] parameterValues) {
    List<String> result = new ArrayList<>();
    if (parameterValues != null) {
      for (Object s : parameterValues) {
        result.add(s.toString());
      }
    }
    return result;
  }
}
