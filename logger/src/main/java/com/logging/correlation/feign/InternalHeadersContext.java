package com.logging.correlation.feign;

import java.util.HashMap;
import java.util.Map;

public final class InternalHeadersContext {

    public static final String X_INTERNAL_CORRELATION_ID_HEADER = "X-INTERNAL-CORRELATION-ID";
    public static final String X_INTERNAL_REQUEST_ID_HEADER = "X-INTERNAL-REQUEST-ID";
    public static final String X_INTERNAL_USER_ID_HEADER = "X-INTERNAL-USER-ID";

    // Initialize ThreadLocal with a map containing all keys (values default to null)
    private static final ThreadLocal<Map<String, String>> context = ThreadLocal.withInitial(() -> {
        Map<String, String> initialMap = new HashMap<>();
        initialMap.put(X_INTERNAL_CORRELATION_ID_HEADER, null);
        initialMap.put(X_INTERNAL_REQUEST_ID_HEADER, null);
        initialMap.put(X_INTERNAL_USER_ID_HEADER, null);
        return initialMap;
    });

    public static void clear() {
        context.remove();
    }

    public static void initContext() {
        // No-op: ThreadLocal already initializes with all keys
    }

    public static void setInternalHeaders(Map<String, String> internalHeaders) {
        if (internalHeaders == null) {
            clear();
        } else {
            // Create a new map with all required keys, merging with incoming headers
            Map<String, String> newMap = new HashMap<>();
            newMap.put(X_INTERNAL_CORRELATION_ID_HEADER, internalHeaders.getOrDefault(X_INTERNAL_CORRELATION_ID_HEADER, null));
            newMap.put(X_INTERNAL_REQUEST_ID_HEADER, internalHeaders.getOrDefault(X_INTERNAL_REQUEST_ID_HEADER, null));
            newMap.put(X_INTERNAL_USER_ID_HEADER, internalHeaders.getOrDefault(X_INTERNAL_USER_ID_HEADER, null));
            context.set(newMap);
        }
    }

    public static Map<String, String> getInternalHeaders() {
        return context.get();
    }
}
