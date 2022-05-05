package com.github.business.log.parser;

import com.google.common.collect.Maps;
import java.lang.reflect.Method;
import java.util.Map;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.context.expression.CachedExpressionEvaluator;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;

/**
 * 业务日志解析器
 * @author chenzhh
 */
public class BusinessLogExpressionEvaluator extends CachedExpressionEvaluator {
    /**
     *
     */
    private Map<ExpressionKey, Expression> expressionCache = Maps.newConcurrentMap();
    /**
     *
     */
    private final Map<AnnotatedElementKey, Method> targetMethodCache = Maps.newConcurrentMap();

    /**
     *
     * @param conditionExpression
     * @param methodKey
     * @param evalContext
     * @return
     */
    public String parseExpression(String conditionExpression, AnnotatedElementKey methodKey, EvaluationContext evalContext) {
        return getExpression(this.expressionCache, methodKey, conditionExpression).getValue(evalContext, String.class);
    }
}
