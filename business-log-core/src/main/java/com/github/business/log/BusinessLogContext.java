package com.github.business.log;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

/**
 * 业务日志上下文
 *
 * @author chenzhh
 */
public final class BusinessLogContext {

    private BusinessLogContext() {
    }

    /**
     * 保存线程变量
     */
    private static final TransmittableThreadLocal<Stack<Map<String, Object>>> businessLogContext = new TransmittableThreadLocal<Stack<Map<String, Object>>>();

    /**
     * 储存变量值
     *
     * @param key
     * @param value
     */
    public static final synchronized void put(String key, Object value) {
        Stack<Map<String, Object>> stack = businessLogContext.get();
        if (Objects.nonNull(stack) && !stack.isEmpty()) {
            Map<String, Object> result = stack.pop();
            if (Objects.nonNull(result) && !result.isEmpty()) {
                result.put(key, value);
            } else {
                Map<String, Object> record = Maps.newHashMap();
                record.put(key, value);
                stack.push(record);

            }
        }
    }

    /**
     * 获取值
     * @return
     */
    public static Map<String, Object> get() {
        Stack<Map<String, Object>> stack = businessLogContext.get();
        if (Objects.nonNull(stack) && !stack.isEmpty()) {
             return stack.peek();
        }
        return Maps.newHashMap();
    }

}
