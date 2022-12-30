package com.example.jdbcspringbootapp.exception.handler;

import com.example.jdbcspringbootapp.exception.DatabaseOperationException;
import com.example.jdbcspringbootapp.exception.NotFoundException;
import com.example.jdbcspringbootapp.model.dto.response.BaseResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.example.jdbcspringbootapp.model.dto.response.BaseResponseDto.createSingleFieldDto;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DatabaseOperationException.class)
    public ResponseEntity<BaseResponseDto> handleDatabaseOperationException(DatabaseOperationException ex){
        return new ResponseEntity<>(createSingleFieldDto(ex.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<BaseResponseDto> handleNotFoundException(NotFoundException ex){
        return new ResponseEntity<>(createSingleFieldDto(ex.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(IllegalAccessError.class)
    public ResponseEntity<BaseResponseDto> handleIllegalAccessError(IllegalAccessError ex){
        return new ResponseEntity<>(createSingleFieldDto(ex.getMessage()), HttpStatus.OK);
    }
}
