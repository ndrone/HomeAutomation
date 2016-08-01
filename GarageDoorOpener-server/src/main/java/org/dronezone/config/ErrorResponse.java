package org.dronezone.config;

import org.springframework.http.HttpStatus;

/**
 * Simple Error Message Object to pass back to to the client making the request.
 *
 * @author Nicholas Drone
 * @since 1.0
 */
public class ErrorResponse
{
    protected int    httpStatus;
    protected String appErrorCode;
    protected Object detail;
    protected String transactionId;
    protected String correlationId;

    private ErrorResponse()
    {
    }

    /**
     * Create's an Error object to be returned to the user.
     *
     * @param httpStatus
     * @param appErrorCode
     * @param detail
     * @return
     */
    public static ErrorResponse of(int httpStatus, String appErrorCode, Object detail)
    {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.httpStatus = httpStatus;
        errorResponse.appErrorCode = appErrorCode;
        errorResponse.detail = detail;
        return errorResponse;
    }

    /**
     * Create's an Error object to be returned to the user.
     *
     * @param httpStatus
     * @param appErrorCode
     * @param detail
     * @return
     */
    public static ErrorResponse of(HttpStatus httpStatus, String appErrorCode, Object detail)
    {
        return of(httpStatus.value(), appErrorCode, detail);
    }

    /**
     * Create's an Error object to be returned to the user.
     *
     * @param transactionId
     * @return
     */
    public ErrorResponse withTransactionId(String transactionId)
    {
        this.transactionId = transactionId;
        return this;
    }

    /**
     * Create's an Error object to be returned to the user.
     *
     * @param correlationId
     * @return
     */
    public ErrorResponse withCorrelationId(String correlationId)
    {
        this.correlationId = correlationId;
        return this;
    }

    public int getHttpStatus()
    {
        return this.httpStatus;
    }

    public String getAppErrorCode()
    {
        return this.appErrorCode;
    }

    public Object getDetail()
    {
        return this.detail;
    }

    public String getTransactionId()
    {
        return this.transactionId;
    }

    public String getCorrelationId()
    {
        return this.correlationId;
    }
}