package com.logging.correlation.infrastructure;

import org.slf4j.MDC;

import java.util.UUID;

import static com.logging.correlation.feign.InternalHeadersContext.X_INTERNAL_CORRELATION_ID_HEADER;
import static com.logging.correlation.feign.InternalHeadersContext.X_INTERNAL_REQUEST_ID_HEADER;

/**
 * Component that stores correlation id and X-INTERNAL-REQUEST-ID using {@link InheritableThreadLocal}.
 */
public final class CorrelationIdAndInternalRequestIdHolder {

  /**
   * Default private constructor.
   */
  private CorrelationIdAndInternalRequestIdHolder() {

  }

  /**
   * Sets the correlation id for the given thread local.
   *
   * @param id the correlationId to set
   */
  public static void setCorrelationId(UUID id) {
    if (id == null) {
      removeCorrelationId();
    } else {
      MDC.put(X_INTERNAL_CORRELATION_ID_HEADER, id.toString());
    }
  }

  /**
   * Returns the correlation id for the given thread local.
   *
   * @return the correlationid
   */
  public static UUID getCorrelationId() {
    return UUID.fromString(MDC.get(X_INTERNAL_CORRELATION_ID_HEADER));
  }

  /**
   * Removes the correlation id for the given thread local.
   */
  public static void removeCorrelationId() {
    MDC.remove(X_INTERNAL_CORRELATION_ID_HEADER);
  }

  /**
   * Sets the X-INTERNAL-REQUEST-ID for the given thread local.
   *
   * @param id the X-INTERNAL-REQUEST-ID to set
   */
  public static void setInternalRequestId(UUID id) {
    if (id == null) {
      removeInternalRequestId();
    } else {
      MDC.put(X_INTERNAL_REQUEST_ID_HEADER, id.toString());
    }
  }

  /**
   * Returns the X-INTERNAL-REQUEST-ID for the given thread local.
   * Generates one if one has not been set.
   *
   * @return the X-INTERNAL-REQUEST-ID
   */
  public static UUID getInternalRequestId()
  {
    return UUID.fromString(MDC.get(X_INTERNAL_REQUEST_ID_HEADER));
  }

  /**
   * Removes the X-INTERNAL-REQUEST-ID for the given thread local.
   */
  public static void removeInternalRequestId()
  {
    MDC.remove(X_INTERNAL_REQUEST_ID_HEADER);
  }

}
