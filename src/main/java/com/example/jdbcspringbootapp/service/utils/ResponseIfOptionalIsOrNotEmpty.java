package com.example.jdbcspringbootapp.service.utils;

import com.example.jdbcspringbootapp.model.dto.response.ErrorDto;
import com.example.jdbcspringbootapp.model.dto.response.ResponseDto;
import com.example.jdbcspringbootapp.model.enums.Status;

import java.util.List;
import java.util.Optional;

public interface ResponseIfOptionalIsOrNotEmpty {
    default <R> ResponseDto<R> setResponseIfOptionalIsOrNotEmpty(
            Optional<R> optional,
            String errMessageIfOptIsEmpty) {

        ResponseDto<R> answer = new ResponseDto<>();

        if (optional.isEmpty()) {
            answer.setStatus(Status.FAILED);
            answer.setErrors(List.of(new ErrorDto(errMessageIfOptIsEmpty)));
        } else {
            answer.setStatus(Status.OK);
            answer.setData(optional.get());
        }

        return answer;
    }
}
