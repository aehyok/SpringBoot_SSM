package com.aehyok.blog.util.handler;

import com.aehyok.blog.util.CommonUrl;
import com.aehyok.blog.util.OperationResult;
import com.aehyok.blog.util.ReadPropertiesUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Properties;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static Properties properties = ReadPropertiesUtil.getProperties(System.getProperty("user.dir") + CommonUrl.RESPONSE_PROP_URL);

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(handlerException(ex), HttpStatus.OK);
    }

    /**
     * 进入controller之后的异常捕获
     * @param e 捕获的异常
     * @return 封装的返回对象
     **/
    @ExceptionHandler(Exception.class)
    public OperationResult handlerException(Throwable e) {
        OperationResult operationResult = new OperationResult();
        String errorName = e.getClass().getName();
        errorName = errorName.substring(errorName.lastIndexOf(".") + 1);
        /**
        if (e.getClass() == RuntimeException.class) {
         operationResult.setMessage(properties.getProperty(valueOf("RuntimeException").msg()) +": "+ e.getMessage());
         operationResult.setCode(properties.getProperty(valueOf("RuntimeException").val()));
        } else {
         operationResult.setMessage(properties.getProperty(valueOf(errorName).msg()));
         operationResult.setCode(properties.getProperty(valueOf(errorName).val()));
        }
         **/
        return operationResult;
    }
}
