package com.example.readwriteseparationv1.exception;




import com.example.readwriteseparationv1.utils.ResultCode;
import com.example.readwriteseparationv1.utils.ResultResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CommonExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);

	/**
	 * 拦截ParamsException类的异常
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ParamsException.class)
	@ResponseBody
	public ResultResponse exceptionHandler(ParamsException e) {
		log.error("ParamsException错误 {}", e.getMessage());
		return ResultResponse.builderResponse(e.getCode(), e.getMessage(), false);
	}
	/**
	 * 拦截ParamsException类的异常
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public ResultResponse exceptionHandler(ServiceException e) {
		log.error("ServiceException错误 {}", e.getMessage());
		return ResultResponse.builderResponse(e.getCode(), e.getMessage(), false);
	}
	/**
	 * 拦截Exception类的异常
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResultResponse exceptionHandler(Exception e) {
		log.error("Exception错误 {}", e.getMessage());
		return ResultResponse.builderResponse(ResultCode.FAIL.getCode(), e.getMessage(), false);
	}

	/**
	 * 拦截 MethodArgumentNotValidException 的异常
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResultResponse exceptionHandler(MethodArgumentNotValidException e) {
		log.error("MethodArgumentNotValidException错误 {}", e.getMessage());
		return ResultResponse.builderResponse(ResultCode.PARAMS_ERROR.getCode(), e.getMessage(), false);
	}

	/**
	 * 拦截 MethodArgumentNotValidException 的异常
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BindException.class)
	@ResponseBody
	public ResultResponse exceptionHandler(BindException e) {
		log.error("BindException错误 {}", e.getMessage());
		return ResultResponse.builderResponse(ResultCode.PARAMS_ERROR.getCode(), e.getMessage(), false);
	}
	/**
	 * 拦截 ConstraintViolationException 的异常
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public ResultResponse exceptionHandler(ConstraintViolationException e) {
		log.error("ConstraintViolationException错误 {}", e.getMessage());
		return ResultResponse.builderResponse(ResultCode.PARAMS_ERROR.getCode(), e.getMessage(), false);
	}
}
