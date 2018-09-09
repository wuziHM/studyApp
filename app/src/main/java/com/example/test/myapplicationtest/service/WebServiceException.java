package com.example.test.myapplicationtest.service;

/**
 * 通用异常类所有类都是它的子类
 */
public class WebServiceException  extends GenericException {
    private static final long serialVersionUID = 8209963012799373903L;

    /**
     *
     */
    public WebServiceException() {
    }

    /**
     * @param detailMessage
     */
    public WebServiceException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * @param throwable
     */
    public WebServiceException(Throwable throwable) {
        super(throwable);
    }

    /**
     * @param detailMessage
     * @param throwable
     */
    public WebServiceException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
