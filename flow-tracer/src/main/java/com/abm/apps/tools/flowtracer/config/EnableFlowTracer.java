package com.abm.apps.tools.flowtracer.config;

import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(FlowTracerConfig.class)
public @interface EnableFlowTracer {
    @AliasFor(annotation = Import.class, attribute = "value")
    Class<?>[] value() default { FlowTracerConfig.class };
 }
