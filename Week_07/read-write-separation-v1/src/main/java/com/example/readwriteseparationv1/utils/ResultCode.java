package com.example.readwriteseparationv1.utils;

/**
 * 200 成功； 400 失败； 401 未经许可； 404 未找到 ；500 服务器错误； 501 查询错误； 502 保存错误； 503 删除错误；
 * 100-token不存在； 101-userId解析错误 ； 102-token解析错误 ；2-分页参数错误 ；3-提交参数验证错误；
 * 
 * @author lvpeng
 *
 */
public enum ResultCode {
	SUCCESS(200), FAIL(400), UNAUTHORIZED(401), NOT_FOUND(404), TOKEN_ERROR(100), PAGE_ERROR(2), PARAMS_ERROR(3),
	INTERNAL_SERVER_ERROR(500), INTERNAL_SERVER_QUERY_ERROR(501), INTERNAL_SERVER_SAVE_ERROR(502),
	INTERNAL_SERVER_DELETE_ERROR(503), TOKEN_USERID_ERROR(101), TOKEN_REQUIRE_ERROR(102), TOKEN_SECRET_ERROR(103);

	private final int code;

	ResultCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
