package com.github.business.log.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 业务日志注解
 * @author chenzhh
 */
@Target(value = {ElementType.METHOD})
public @interface BusinessLog {

}
