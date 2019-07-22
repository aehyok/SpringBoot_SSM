package com.aehyok.blog.util.handler;

import com.aehyok.blog.util.OperationResult;
import com.aehyok.blog.util.ReturnCode;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestExceptionHandler implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public OperationResult errorPage(){
        return new OperationResult(ReturnCode.UrlError);
    }
}
