package com.example.readwriteseparationv1.utils;


import lombok.Data;

import java.io.Serializable;

@Data
public class ResultResponse<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 返回信息
	 */
	String msg;
	/**
	 * 返回代码
	 */
	int code;
	/**
	 * 是否正确
	 */
	Boolean succsess;
	/**
	 * 返回数据
	 */
	T data;
//	/**
//	 * 分页-总条数
//	 */
//	long totalElements;
//	/**
//	 * 分页-页数
//	 */
//	long totalPages;
	
	String token;
	
	public ResultResponse() {
		super();
		this.msg = "";
		this.token = "";
		this.code = ResultCode.SUCCESS.getCode();
		this.succsess = true;
		this.data = null;
//		this.totalElements = 0;
//		this.totalPages = 0;
	}
	public static <T> ResultResponse<T> builderResponse(String token) {
		return restResult(ResultCode.SUCCESS.getCode(),"",CommonConstants.SUCCESS,token,null);
	}
	public static <T> ResultResponse<T> builderResponse(Boolean succsess,String token) {
		return restResult(ResultCode.SUCCESS.getCode(),"",succsess,token,null);
	}
	public static <T> ResultResponse<T> builderResponse(Boolean succsess,String msg,T data) {
		return restResult(ResultCode.SUCCESS.getCode(),msg,succsess,null,data);
	}
	public static <T> ResultResponse<T> builderResponse(int code,String msg,T data) {
		return restResult(code,msg, CommonConstants.SUCCESS,null,data);
	}
	public static <T> ResultResponse<T> builderResponse(int code,String msg,Boolean succsess,T data) {
		return restResult(code,msg,succsess,null,data);
	}
	public static <T> ResultResponse<T> builderResponse(int code,String msg,Boolean succsess) {
		return restResult(code,msg,succsess,null,null);
	}
	public static <T> ResultResponse<T> builderResponse(int code,String msg,Boolean succsess,String token) {
		return restResult(code,msg,succsess,token,null);
	}
	public static <T> ResultResponse<T> builderResponse(int code,String msg,Boolean succsess,String token,T data) {
		return restResult(code,msg,succsess,token,data);
	}
	private static  <T> ResultResponse<T> restResult(int code,String msg,Boolean succsess,String token,T data){
		ResultResponse<T> res=new ResultResponse<>();
		res.msg = msg;
		res.token = token;
		res.code = code;
		res.succsess = succsess;
		res.data = data;
		return res;
	}


}
