package com.logging.correlation.feign;

import java.util.Map;

public final class InternalHeadersContextOld {

    public static final String X_INTERNAL_CORRELATION_ID_HEADER = "X-INTERNAL-CORRELATION-ID";
    public static final String X_INTERNAL_REQUEST_ID_HEADER = "X-INTERNAL-REQUEST-ID";
    public static final String X_INTERNAL_USER_ID_HEADER = "X-INTERNAL-USER-ID";

    public static void clear() {
        // TODO - implement this
    }

    public static void initContext() {
        // TODO - implement this
    }

    public static void setInternalHeaders(Map<String, String> internalHeaders) {
        // TODO - implement this
    }

    public static Map<String, String> getInternalHeaders() {
        // TODO - implement this
        return Map.of();
    }
}

