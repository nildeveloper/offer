package com.lhl.boot.exception;

/**
 * 所有业务异常，该异常会被controller的拦截器统一拦截，并将msg返回给前端，因此，只有业务问题才抛出该异常，并且msg是友好的。 该类及其子类相关的异常会被打到日志文件中
 * 
 * @author chenzhenjiang 2017年11月17日
 */
public class LoggedBusinessException extends BusinessException {

    /**
     * 
     */
    private static final long serialVersionUID = 492269212303791477L;


    public LoggedBusinessException() {
        super();
    }

    public LoggedBusinessException(String msg) {
        super(msg);
    }

    public LoggedBusinessException(int code, String msg) {
        super(code, msg);
    }

    public LoggedBusinessException(int code, String msg, Throwable cause) {
        super(code, msg, cause);
    }

}
