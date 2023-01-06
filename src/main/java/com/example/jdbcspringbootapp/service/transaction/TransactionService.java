package com.example.jdbcspringbootapp.service.transaction;

import com.example.jdbcspringbootapp.model.dto.request.transaction.*;
import com.example.jdbcspringbootapp.model.dto.response.ResponseDto;
import com.example.jdbcspringbootapp.model.dto.response.transaction.*;
import com.example.jdbcspringbootapp.service.utils.ResponseIfOptionalIsOrNotEmpty;

public interface TransactionService extends ResponseIfOptionalIsOrNotEmpty {

    ResponseDto<CreateTransactionRespDto> createTransaction(CreateTransactionReqDto createTransactionReqDto);
    ResponseDto<GetTransactionRespDto> getTransactionById(Long id);
    ResponseDto<GetFirstTransactionRespDto> getFirstTransaction();
    ResponseDto<DeleteTransactionRespDto> deleteTransactionById(Long id) throws IllegalAccessException;
    ResponseDto<UpdateTransactionRespDto> updateTransactionById(Long id, UpdateTransactionReqDto updateTransactionReqDto) throws IllegalAccessException;
}
