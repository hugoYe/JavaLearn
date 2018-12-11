package com.system.handler;

import com.system.common.vo.ResponseVO;
import com.system.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice("com.system")
public class WebExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseVO<Object> handleSystemException(BizException e) {
        ResponseVO<Object> vo = new ResponseVO<>();
        String message = e.getMessage();
        if (null != message) {
            try {
                message = messageSource.getMessage(message, e.getArgs(), LocaleContextHolder.getLocale());

            } catch (NoSuchMessageException error) {
                error.printStackTrace();
            }
            vo.setMsg(message);
        }
        vo.setErrorCode(e.getErrorCode() == null ? "500" : String.valueOf(e.getErrorCode()));

        return vo;
    }
}
