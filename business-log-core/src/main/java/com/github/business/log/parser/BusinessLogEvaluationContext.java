package com.github.business.log.parser;

import com.github.business.log.BusinessLogContext;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.ParameterNameDiscoverer;

/**
 * 业务日志上下文参数解析
 *
 * @author chenzhh
 */
public class BusinessLogEvaluationContext extends MethodBasedEvaluationContext {
    /**
     * @param rootObject
     * @param method                  方法
     * @param arguments               参数
     * @param parameterNameDiscoverer
     * @param ret                     返回值
     * @param failMsg                 错误提示信息
     */
    public BusinessLogEvaluationContext(Object rootObject, Method method, Object[] arguments,
        ParameterNameDiscoverer parameterNameDiscoverer, Object ret, Object failMsg) {
        super(rootObject, method, arguments, parameterNameDiscoverer);
        Map<String, Object> logContext = BusinessLogContext.get();
        //参数放到rootObject中
        if (Objects.nonNull(logContext) && !logContext.isEmpty()) {
            for (String key : logContext.keySet()) {
                setVariable(key,logContext.get(key));
            }
        }
        //返回值放入rootObject中
        setVariable("ret",ret);
        setVariable("failMsg",failMsg);
    }
}
