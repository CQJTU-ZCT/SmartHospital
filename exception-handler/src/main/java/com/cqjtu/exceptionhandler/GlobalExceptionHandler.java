package com.cqjtu.exceptionhandler;

import com.cqjtu.exception.ServiceException;
import com.cqjtu.messages.ExceptionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author zjhfyq
 * @Desc 全局异常处理
 * @date 2017/12/8.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {


    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理缺少请求参数异常
     * @param exception
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ExceptionMessage handleMissingServletRequestParameterException(MissingServletRequestParameterException exception){
        logger.error("缺少请求参数异常", exception.getMessage());
        ExceptionMessage message = ExceptionMessage.getExceptionMessage(400,exception.getMessage());
        return  message;
    }



    /**
     * 处理参数绑定异常
     * @param exception
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ExceptionMessage handleBindException(BindException exception){
        logger.error("参数绑定异常", exception.getMessage());
        BindingResult result = exception.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String msg = String.format("%s:%s", field, code);
        ExceptionMessage message = ExceptionMessage.getExceptionMessage(400,msg);
        return  message;
    }



    /**
     * 处理请求参数解析异常
     * @param exception
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        logger.error("请求参数验证失败", exception.getMessage());
        ExceptionMessage message = ExceptionMessage.getExceptionMessage(400,exception.getMessage());
        return  message;
    }



    /**
     * 处理请求参数验证异常
     * @param exception
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ExceptionMessage handleHttpMessageNotReadableException(HttpMessageNotReadableException exception){
        logger.error("请求参数解析失败", exception.getMessage());
        ExceptionMessage message = ExceptionMessage.getExceptionMessage(400,exception.getMessage());
        return  message;
    }



    /**
     * 处理请求方法不被允许异常
     * @param exception
     * @return
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ExceptionMessage handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception){
        logger.error("请求方法不被允许异常", exception.getMessage());
        ExceptionMessage message = ExceptionMessage.getExceptionMessage(405,exception.getMessage());
        return  message;
    }


    /**
     * 处理请求地址未找到异常
     * @param exception
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ExceptionMessage handleNoHandlerFoundException(NoHandlerFoundException exception){
        logger.error("请求地址未找到异常", exception.getMessage());
        ExceptionMessage message = ExceptionMessage.getExceptionMessage(404,exception.getMessage());
        return  message;
    }


    /**
     * 处理请求地址未找到异常
     * @param exception
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionMessage handleServiceException(Exception exception){
        logger.error("处理逻辑异常", exception.getMessage());
        ExceptionMessage message = ExceptionMessage.getExceptionMessage(500,exception.getMessage());
        return  message;
    }



}
