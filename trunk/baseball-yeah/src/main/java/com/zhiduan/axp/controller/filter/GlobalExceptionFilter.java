package com.zhiduan.axp.controller.filter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionFilter {

	/*
	 * @ExceptionHandler(CustomGenericException.class) public ModelAndView
	 * handleCustomException(CustomGenericException ex) {
	 * 
	 * ModelAndView model = new ModelAndView("hello/getpage");
	 * model.addObject("errCode", ex.getErrCode()); model.addObject("errMsg",
	 * ex.getErrMsg()); return new ModelAndView("hello").addObject("message",
	 * ex.getErrCode());
	 * 
	 * }
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {

		ModelAndView model = new ModelAndView("error/505");
		model.addObject("errMsg", "this is Exception.class");
		model.addObject("errMsg",ex.getMessage());
		return model;
	}

	/*@ExceptionHandler(BusinessException.class)
	public ModelAndView handleCustomException(BusinessException ex) {

		ModelAndView model = new ModelAndView("error/error");
		model.addObject("errCode", ex.getErrorCode());
		model.addObject("errMsg", ex.getMessage());
		return new ModelAndView("error/exceptionError");

	}*/

	// @ExceptionHandler(CustomGenericException.class)
	// @ResponseBody
	// @ResponseStatus(HttpStatus.NOT_FOUND)
	// public ResultInfo handleCustomException(CustomGenericException ex) {
	//
	// ResultInfo resultInfo = new ResultInfo();
	// resultInfo.setCode(ex.getErrCode());
	// resultInfo.setMessage(ex.getErrMsg());
	// return resultInfo;
	//
	// }
	//
	// // @ExceptionHandler(Exception.class)
	// // @ResponseBody
	// public ResultInfo handleAllException(Exception ex) {
	//
	// ResultInfo resultInfo = new ResultInfo();
	// resultInfo.setCode("-1");
	// resultInfo.setMessage("系统异常");
	//
	// return resultInfo;
	//
	// }

}