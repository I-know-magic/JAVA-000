package com.example.readwriteseparationv1.exception;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException {
	 	private final int code;
	 	private final String msg;
	 	private final Boolean succsess;

	    public ServiceException(int code,String msg) {
			this.succsess = false;
			this.code = code;
			this.msg = msg;
	    }
	    public ServiceException(int code,String msg,Boolean succsess) {
			this.code = code;
			this.msg = msg;
			this.succsess=succsess;
	    }
	    public String getMessage(){
	        return this.msg;
	    }

}
