package com.example.datajpa.exception;


import com.example.datajpa.entity.ErrorObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//    결론은 ResponseEntity 클래스를 사용하면,
//    결과값, 상태코드, 헤더값을 모두 프론트에 넘겨줄 수 있고,
//    에러코드 또한 섬세하게 설정해서 보내줄 수 있다는 장점이 있다!
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorObject> handleExpenseNotFoundException(
                ResourceNotFoundException ex, WebRequest request)
        {
            ErrorObject errorObject = new ErrorObject();
            errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
            errorObject.setMessage(ex.getMessage());
            errorObject.setTimeStamp(new Date());
            return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
        }
        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
        public ResponseEntity<ErrorObject> handleMethodArgumentMismatchException(
                MethodArgumentTypeMismatchException ex, WebRequest request)
        {
            ErrorObject errorObject = new ErrorObject();
            errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
            errorObject.setMessage(ex.getMessage());
            errorObject.setTimeStamp(new Date());
            return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
        }
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorObject> handleGeneralException(Exception ex, WebRequest request)
        {
            ErrorObject errorObject = new ErrorObject();
            errorObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorObject.setMessage(ex.getMessage());
            errorObject.setTimeStamp(new Date());
            return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    @Override
    protected ResponseEntity<Object>
    handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
    HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, Object> body = new HashMap<String, Object>();
        body.put("timestamp", new Date());
        body.put("statusCode", HttpStatus.BAD_REQUEST.value());

        List<String> errors =  ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(x -> x.getDefaultMessage())
                        .collect(Collectors.toList());

        body.put("messages", errors);

        return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
    }
}