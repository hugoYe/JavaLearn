package com.system.common.utils;

import java.util.HashMap;
import java.util.Map;


/**
 * 线程上下文
 */
public class ThreadContext {

    /**
     * 保存当前访问用户thread相关信息
     **/
    private static final ThreadLocal<Map<String, Object>> CTX_HOLDER
            = new ThreadLocal<Map<String, Object>>();

    /**
     * traceID,客户端
     */
    private final static String TRACE_ID_KEY = "traceId";

    /**
     * 请求ID,自己生成
     */
    private final static String REQUEST_ID_KEY = "requestId";

    /**
     * 客户端IP
     */
    private static final String CLIENT_IP_KEY = "clientIp";

    /**
     * 获取指定名称的属性值
     *
     * @param attributeName 属性名称
     */
    public static <T extends Object> T getAttribute(String attributeName) {
        Map<String, Object> ctx = CTX_HOLDER.get();
        if (ctx == null) {
            return null;
        }
        return (T) ctx.get(attributeName);
    }

    /**
     * 保存指定名称的属性值
     *
     * @param attributeName 属性名称
     * @param value         属性值
     */
    public static void setAttribute(String attributeName, Object value) {
        Map<String, Object> ctx = CTX_HOLDER.get();
        if (ctx == null) {
            ctx = new HashMap<String, Object>();
            CTX_HOLDER.set(ctx);
        }
        ctx.put(attributeName, value);
    }

    /**
     * 清除用户相关所有信息
     */
    public static void removeAll() {
        CTX_HOLDER.remove();
    }

    /**
     * 设置traceId数据
     */
    public final static void putTraceId(String traceId) {
        setAttribute(TRACE_ID_KEY, traceId);
    }

    /**
     * 获取traceId数据
     */
    public final static String getTraceId() {
        return getAttribute(TRACE_ID_KEY);
    }

    /**
     * 设置requestId数据
     */
    public final static void putRequestId(String requestId) {
        setAttribute(REQUEST_ID_KEY, requestId);
    }

    /**
     * 获取requestId数据
     */
    public final static String getRequestId() {
        return getAttribute(REQUEST_ID_KEY);
    }

    /**
     * 设置Ip
     */
    public static void putClientIp(String clientIp) {
        setAttribute(CLIENT_IP_KEY, clientIp);
    }

    /**
     * 获取客户端IP
     */
    public static String getClientIp() {
        return getAttribute(CLIENT_IP_KEY);
    }
}
