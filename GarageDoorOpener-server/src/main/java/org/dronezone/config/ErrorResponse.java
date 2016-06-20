package org.dronezone.config;

import org.springframework.http.HttpStatus;

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

    public static ErrorResponse of(int httpStatus, String appErrorCode, Object detail)
    {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.httpStatus = httpStatus;
        errorResponse.appErrorCode = appErrorCode;
        errorResponse.detail = detail;
        return errorResponse;
    }

    public static ErrorResponse of(HttpStatus httpStatus, String appErrorCode, Object detail)
    {
        return of(httpStatus.value(), appErrorCode, detail);
    }

    public ErrorResponse withTransactionId(String transactionId)
    {
        this.transactionId = transactionId;
        return this;
    }

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