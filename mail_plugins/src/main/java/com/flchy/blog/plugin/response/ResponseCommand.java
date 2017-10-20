package com.flchy.blog.plugin.response;

import java.io.Serializable;
/**
 * 返回结果类
 * @author flchy
 *
 */
public class ResponseCommand implements Serializable {
	private static final long serialVersionUID = 6700648664405181335L;
	public final static int STATUS_SUCCESS = 0;
	public final static int STATUS_ERROR = 1;

	private int status;
	private Object result;

	public ResponseCommand(int status, Object result) {
		this.status = status;
		this.result = result;
	}
	

	public ResponseCommand( Object result) {
		this.status = STATUS_ERROR;
		this.result = result;
	}


	public ResponseCommand() {
		this.status = STATUS_SUCCESS;
		this.result = "操作成功";
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
