package com.github.okzhu.srping.extend.thrift.client.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface OKThriftMethod {

    String method();

    String controller();

    double version() default 1.0;
}
