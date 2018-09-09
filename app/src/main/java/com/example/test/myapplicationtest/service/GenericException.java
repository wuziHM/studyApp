package com.example.test.myapplicationtest.service;

/**
 * 通用异常类所有类都是它的子类
 */
public class GenericException extends RuntimeException {

    private static final long serialVersionUID = 7637671937724564101L;

    /**
     *
     */
    public GenericException() {
    }

    /**
     * @param detailMessage
     */
    public GenericException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * @param throwable
     */
    public GenericException(Throwable throwable) {
        super(throwable);
    }

    /**
     * @param detailMessage
     * @param throwable
     */
    public GenericException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
