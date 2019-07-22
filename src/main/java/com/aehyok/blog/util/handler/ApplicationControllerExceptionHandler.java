package com.aehyok.blog.util.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ApplicationControllerExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(ApplicationControllerExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public List<String> handlerError(HttpServletRequest req, Exception e) {
        List<String> list=new ArrayList<>();
        list.add(req.getRequestURI());
        list.add(req.getParameterMap().toString());
        return list;
    }
}
