package com.jmspoc.hexarchitecture;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Component
public @interface Consumer {
    @AliasFor(annotation = Component.class)
    String value() default "";
}
