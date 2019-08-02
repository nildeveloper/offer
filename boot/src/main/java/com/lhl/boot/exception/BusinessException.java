package com.lhl.boot.exception;

/**
 * 所有业务异常，该异常会被controller的拦截器统一拦截，并将msg返回给前端，因此，只有业务问题才抛出该异常，并且msg是友好的。
 * 该类及其子类相关的异常都不应该打到日志文件中
 * 
 * @author chenzhenjiang 2017年1月20日
 */
public class BusinessException extends RuntimeException {

	/**
	 * 默认是-1，与返回前端的普通错误一致
	 */
	private int code = -1;

	/**
	 * 异常返回的数据
	 */
	private Object data = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 814964852033852606L;

	public BusinessException() {
		super();
	}

	public BusinessException(String msg) {
		// super(msg);
		super(msg, null, true, false); // 禁用fillInStackTrace()，以提高性能
	}

	public BusinessException(int code, String msg) {
		// super(msg);
		super(msg, null, true, false); // 禁用fillInStackTrace()，以提高性能
		this.code = code;
	}

	public BusinessException(int code, String msg, Object data) {
		super(msg, null, true, false); // 禁用fillInStackTrace()，以提高性能
		this.code = code;
		this.data = data;
	}

	public BusinessException(int code, String msg, Object data, Throwable cause) {
		super(msg, cause);
		this.code = code;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}