package com.springbootacademy.batch16.pos.advisor;

import com.springbootacademy.batch16.pos.exception.NotFoundException;
import com.springbootacademy.batch16.pos.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(NotFoundException e){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404,"Error Coming",e.getMessage() + "dinusha"),
                HttpStatus.NOT_FOUND
        );

    }
}
